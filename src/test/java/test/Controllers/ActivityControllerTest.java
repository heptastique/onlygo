package test.Controllers;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import smart.Application;
import smart.Jwt.JwtTokenUtil;
import smart.Services.DonneeAthmospheriqueService;
import smart.Services.TimeFrameService;
import smart.Services.WeatherDataService;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ActivityControllerTest {

    private MockMvc mvc;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private WeatherDataService weatherDataService;

    @Autowired
    private DonneeAthmospheriqueService donneeAthmospheriqueService;

    @Autowired
    private TimeFrameService timeFrameService;

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

        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("user10");

        mvc.perform(get("/update")
            .header("Authorization", "Bearer anyToken"))
            .andExpect(status().isOk());

        mvc.perform(get("/programme/generate")
            .header("Authorization", "Bearer anyToken"))
            .andExpect(status().isOk());

        mvc.perform(get("/activity/nextPlanned")
            .header("Authorization", "Bearer anyToken"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.estRealisee").value(false));
    }
}
