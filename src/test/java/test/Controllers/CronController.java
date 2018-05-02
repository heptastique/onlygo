package test.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import smart.Application;
import smart.Jwt.JwtTokenUtil;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CronController {

    private MockMvc mvc;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

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
    public void shouldGetNextActivity() throws Exception{

        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("user30");

        mvc.perform(get("/importStravaPoints")
            .header("Authorization", "Bearer anyToken"))
            .andExpect(status().isOk());

        mvc.perform(get("/activity/itinary?activite=10245")
            .header("Authorization", "Bearer anyToken"))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.pointCentreInteretList").isNotEmpty())
            .andExpect(jsonPath("$.polyline").isNotEmpty());
    }

}
