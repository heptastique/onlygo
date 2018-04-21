package test;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.util.NestedServletException;
import smart.Application;
import smart.Controller.MainController;
import smart.Exceptions.AuthenticationException;

import java.nio.charset.Charset;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@ContextConfiguration
@WebAppConfiguration
public class ControllerTest {
    @Autowired private WebApplicationContext ctx;

    private MockMvc mockMvc;

    @Before public void setUp() {
        this.mockMvc = webAppContextSetup(ctx).build();
    }

    @Test
    public void helloWorld() throws Exception {
        mockMvc.perform(get("/hello").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().string("Ok"));
    }

    @Test(expected = NestedServletException.class)
    public void protectedEndpoint() throws Exception {
        mockMvc.perform(get("/protected").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isUnauthorized());
    }

    @Configuration
    public static class TestConfiguration {

        @Bean
        public MainController mainController() {
            return new MainController();
        }

    }
}
