package test.Services;

import javassist.NotFoundException;
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
import smart.DTO.RealisationDTO;
import smart.DTO.UserDto;
import smart.Entities.Activity;
import smart.Entities.Programme;
import smart.Entities.Realisation;
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
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
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
        User sedentary = userService.addUser(sedentaryDto);
        Iterable<Realisation> realisations = realisationService.getUserRealisations(sedentary);
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
        Programme programme = programmeService.getActiveProgrammeOfUser("user");
        Activity activity = activityService.getNextActivity(programme);
        Realisation realisationAdded = realisationService.addRealisation(realisationDTO,programme, activity);
        assertEquals((int)realisationAdded.getDistance(), 2);
        assertEquals((long)realisationAdded.getCentreInteret().getId(), (long)10000);
    }

    @Test(expected = ProgrammeException.class)
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
        Activity activity = activityService.getNextActivity(programme);
        realisationService.addRealisation(realisationDTO, programme, activity);
    }
}
