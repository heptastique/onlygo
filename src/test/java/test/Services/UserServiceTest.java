package test.Services;

import javassist.NotFoundException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import smart.Application;
import smart.DTO.PollutionDataDto;
import smart.DTO.UserDto;
import smart.Entities.DonneeAthmospherique;
import smart.Entities.User;
import smart.Exceptions.EmailExistsException;
import smart.Exceptions.UsernameExistsException;
import smart.Jwt.JwtUser;
import smart.Jwt.JwtUserDetailsService;
import smart.Repositories.DonneeAthmospheriqueRepository;
import smart.Services.DonneeAthmospheriqueService;
import smart.Services.UserService;

import javax.validation.*;
import java.util.List;
import java.util.Set;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class UserServiceTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    UserService userService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void AdminIsFound() {
        JwtUser admin = (JwtUser) jwtUserDetailsService.loadUserByUsername("admin");
        assertEquals("admin", admin.getUsername());
    }

    @Test
    public void UsersFound() {
        Iterable <User> users = userService.getAllUsers();
        assertNotNull(users);
    }

    @Test
    public void UserAdded() throws NotFoundException {
        //create a user object
        UserDto userDto = new UserDto();
        userDto.setEmail("paul.martin@pi.com");
        userDto.setFirstname("Hugo");
        userDto.setLastname("Martin");
        userDto.setUsername("pmartin");
        userDto.setPassword("password");
        //call adduser service
        userService.addUser(userDto);
        //test if user has been added
        assertNotNull(jwtUserDetailsService.loadUserByUsername("pmartin"));
        assertNotNull(userDto.toString());
    }

    @Test
    public void UserWrongEmail() throws NotFoundException {
        //create a user object
        UserDto userDto = new UserDto();
        userDto.setEmail("hugo.martinpi.com");
        userDto.setFirstname("Hugo");
        userDto.setLastname("Martin");
        userDto.setUsername("hmartin");
        userDto.setPassword("password");
        //call adduser service
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        Validator validator = factory.getValidator();
        Set<ConstraintViolation<UserDto>> constraintViolations = validator.validate(userDto);
        assertFalse(constraintViolations.isEmpty());
    }

    @Test(expected = EmailExistsException.class)
    public void UserEmail() throws NotFoundException {
        //create a user
        UserDto userDto = new UserDto();
        userDto.setEmail("h.martin@pi.com");
        userDto.setFirstname("Hugo");
        userDto.setLastname("Martin");
        userDto.setUsername("martin");
        userDto.setPassword("password");
        userService.addUser(userDto);
        //create a user with the same email and different username
        UserDto copycat = new UserDto();
        copycat.setEmail("h.martin@pi.com");
        copycat.setFirstname("Hugo");
        copycat.setLastname("Martin");
        copycat.setUsername("hugomartin");
        copycat.setPassword("password");
        userService.addUser(copycat);
    }

    @Test(expected = UsernameExistsException.class)
    public void Username()  throws NotFoundException {
        //create a user
        UserDto userDto = new UserDto();
        userDto.setEmail("hugomartin@pi.com");
        userDto.setFirstname("Hugo");
        userDto.setLastname("Martin");
        userDto.setUsername("hmartin");
        userDto.setPassword("password");
        userService.addUser(userDto);
        //create a user with the same username and different email
        UserDto copycat = new UserDto();
        copycat.setEmail("huguesmartin@pi.com");
        copycat.setFirstname("Hugues");
        copycat.setLastname("Martin");
        copycat.setUsername("hmartin");
        copycat.setPassword("password");
        userService.addUser(copycat);
    }
}
