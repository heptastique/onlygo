package test.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import smart.Application;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class TimeFrameControllerTest {

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
    @WithMockUser(roles = "USER")
    public void shouldGetAllTimeFrame() throws Exception{
        this.mvc
            .perform(get("/timeFrame/all").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$[0].id").value("1"));
    }
    @Test
    @WithMockUser(roles = "USER")
    public void shouldGetTimeFrameNow() throws Exception{
        this.mvc
            .perform(get("/timeFrame/now").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }
    @Test
    @WithMockUser(roles = "USER")
    public void shouldGetTimeFrameTime() throws Exception{
        this.mvc
            .perform(get("/timeFrame/time?time=2018-04-28-09").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }
    @Test
    @WithMockUser(roles = "USER")
    public void shouldGetEvaluationNow() throws Exception{
        this.mvc
            .perform(get("/timeFrame/evaluation/now").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("note").value("-1.0"));
    }
    @Test
    @WithMockUser(roles = "USER")
    public void shouldGetEvaluationTime() throws Exception{
        this.mvc
            .perform(get("/timeFrame/evaluation/time?time=2018-04-28-09").accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());
    }


}
