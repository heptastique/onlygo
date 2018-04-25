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
import smart.DTO.PollutionDataDto;
import smart.DTO.UserDto;
import smart.Entities.DonneeAthmospherique;
import smart.Entities.User;
import smart.Jwt.JwtUser;
import smart.Jwt.JwtUserDetailsService;
import smart.Repositories.DonneeAthmospheriqueRepository;
import smart.Services.DonneeAthmospheriqueService;
import smart.Services.UserService;

import java.util.List;

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

}
