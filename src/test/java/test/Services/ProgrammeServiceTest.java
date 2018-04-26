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
import smart.Entities.Programme;
import smart.Entities.User;
import smart.Repositories.UserRepository;
import smart.Services.ProgrammeService;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class ProgrammeServiceTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    ProgrammeService programmeService;

    @Autowired
    UserRepository userRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void shouldGetActiveProgramOfUser() {
        User user = userRepository.findByUsername("admin");
        Programme programme = programmeService.getActiveProgrammeOfUser(user);
        assertEquals("Programme{id=200, user=null, dateDebut=2018-04-23, " +
            "activites=[Activity{sport=Sport{id=1, nom='Course', kmH=12.0, kcalH=880.0, kcalkm=73.333336}, distance=300.0, date=2018-04-23, estRealisee=false, programme=200}, " +
            "Activity{sport=Sport{id=1, nom='Course', kmH=12.0, kcalH=880.0, kcalkm=73.333336}, distance=200.0, date=2018-04-26, estRealisee=true, programme=200}], " +
            "realisations=[Realisation{id=100, distance=300.0, date=2018-04-23, " +
            "activite=Activity{sport=Sport{id=1, nom='Course', kmH=12.0, kcalH=880.0, kcalkm=73.333336}, distance=200.0, date=2018-04-26, estRealisee=true, programme=200}}]}"
            , programme.toString());
    }
}
