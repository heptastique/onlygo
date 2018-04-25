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
import smart.Entities.Sport;
import smart.Services.SportService;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class SportServiceTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    SportService sportService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void DataIsCorrectlyFetched() {
        Iterable<Sport> sports = sportService.getAllSports();
        Iterator<Sport> iter = sports.iterator();
        assertEquals("Sport{id=1, nom=\'Course\', kmH=12.0, kcalH=880.0, kcalkm=73.333336}", iter.next().toString());
        assertEquals("Sport{id=2, nom=\'Marche\', kmH=4.0, kcalH=245.0, kcalkm=61.25}", iter.next().toString());
        assertEquals("Sport{id=3, nom=\'Cyclisme\', kmH=20.0, kcalH=690.0, kcalkm=34.5}", iter.next().toString());
    }
}
