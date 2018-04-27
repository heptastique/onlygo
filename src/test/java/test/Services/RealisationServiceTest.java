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
import smart.DTO.UserDto;
import smart.Entities.Realisation;
import smart.Entities.User;
import smart.Repositories.UserRepository;
import smart.Services.RealisationService;
import smart.Services.UserService;

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
}
