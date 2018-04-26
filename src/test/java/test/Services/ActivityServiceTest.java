package test.Services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import smart.Application;
import smart.DTO.ActivityDTO;
import smart.Entities.Activity;
import smart.Services.ActivityService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ActivityServiceTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ActivityService activityService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void DataIsCorrectlyFetched() {
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
        Activity addedActivity = activityService.addActivity(activityDTO);
        assertEquals("Activity{sport=Sport{id=1, nom='Course', kmH=12.0, "
            + "kcalH=880.0, kcalkm=73.333336}, distance=400.0, date=2018-04-22}", addedActivity.toString());
    }
}
