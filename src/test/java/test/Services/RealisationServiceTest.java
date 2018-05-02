package test.Services;

import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import smart.Application;
import smart.DTO.PointDto;
import smart.DTO.RealisationDTO;
import smart.DTO.UserDto;
import smart.Entities.Activity;
import smart.Entities.Programme;
import smart.Entities.User;
import smart.Exceptions.ProgrammeException;
import smart.Repositories.UserRepository;
import smart.Services.ActivityService;
import smart.Services.ProgrammeService;
import smart.Services.RealisationService;
import smart.Services.UserService;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class RealisationServiceTest {

    MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    private RealisationService realisationService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserService userService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void newUserNoRealisations() throws NotFoundException{
        UserDto sedentaryDto = new UserDto();
        sedentaryDto.setFirstname("Jared");
        sedentaryDto.setLastname("Foggle");
        sedentaryDto.setEmail("jf@subway.com");
        sedentaryDto.setUsername("subwayguy");
        sedentaryDto.setPassword("eatfood");

        sedentaryDto.setDistanceMax(5);

        PointDto localisation = new PointDto();
        localisation.setX(1.0);
        localisation.setY(1.0);
        sedentaryDto.setLocation(localisation);

        User sedentary = userService.addUser(sedentaryDto);
        Iterable<Activity> realisations = realisationService.getUserRealisations(sedentary);
    }

    @Test
    public void realisationAddedCorrecly() {
        RealisationDTO realisationDTO = new RealisationDTO();
        realisationDTO.setDistance(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            realisationDTO.setDate(dateFormat.parse("2018-05-01 16:04:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Programme programme = programmeService.getActiveProgrammeOfUser("user11");
        Activity activity = activityService.getNextActivity(programme);
        activity = realisationService.addRealisation(realisationDTO,programme, activity);
        assertEquals((int)activity.getDistanceRealisee(), 2);
        assertEquals((long)activity.getCentreInteret().getId(), (long)10000);
    }

    @Test
    public void realisationAddedProgException() throws ProgrammeException {
        RealisationDTO realisationDTO = new RealisationDTO();
        realisationDTO.setDistance(2);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            realisationDTO.setDate(dateFormat.parse("2018-05-01 16:04:12"));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Programme programme = programmeService.getActiveProgrammeOfUser("admin");
        assertNull(programme);
    }
}
