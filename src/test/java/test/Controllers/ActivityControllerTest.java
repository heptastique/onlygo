package test.Controllers;

import com.google.gson.Gson;
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
import smart.DTO.ActivityDTO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ActivityControllerTest {

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
    public void shouldAddActivity() throws Exception{

        String json ="{\"distance\": 300.0, \"date\": \"2018-04-22\",\"sportName\": \"Course\"}";

        this.mvc.perform(post("/activity/add").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.distance").value("300.0"));
    }

    @Test
    public void shouldGetUnauthorizedWithoutRole() throws Exception{

        ActivityDTO activityDTO = new ActivityDTO();
        activityDTO.setDistance(400);
        Date date;
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        try {
            date = dateFormater.parse("2018-04-22");
            activityDTO.setDate(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        activityDTO.setSportName("Course");

        Gson gson = new Gson();
        String json = gson.toJson(activityDTO, ActivityDTO.class);

        this.mvc.perform(post("/activity/add").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }
}
