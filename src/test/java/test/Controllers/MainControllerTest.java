package test.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.test.annotation.DirtiesContext;

import smart.Application;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class MainControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void shouldGetUnauthorizedWithoutRole() throws Exception{
        this.mvc
            .perform(get("/protected"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldGetForbiddenWithUserRole() throws Exception{
        this.mvc
            .perform(get("/protected"))
            .andExpect(status().isForbidden());
    }

    @Test
    @WithMockUser(roles = "ADMIN")
    public void shouldGetOkWithAdminRole() throws Exception{
        this.mvc
            .perform(get("/protected"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(content().string("Greetings from admin protected method!"));
    }

    @Test
    @WithMockUser(roles = "USER")
    public void shouldGetOkWithUserRole() throws Exception{
        this.mvc
            .perform(get("/hello"))
            .andExpect(status().is2xxSuccessful());
    }

}
