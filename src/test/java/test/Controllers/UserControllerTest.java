package test.Controllers;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.context.WebApplicationContext;
import smart.Application;
import smart.DTO.PointDto;
import smart.DTO.DistanceDto;
import smart.DTO.UserDto;
import smart.Entities.Authority;
import smart.Entities.AuthorityName;
import smart.Entities.User;
import smart.Jwt.JwtTokenUtil;
import smart.Jwt.JwtUser;
import smart.Jwt.JwtUserDetailsService;
import smart.Jwt.JwtUserFactory;
import smart.Repositories.UserRepository;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest (classes = Application.class)
public class UserControllerTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @MockBean
    private JwtTokenUtil jwtTokenUtil;

    @MockBean
    private JwtUserDetailsService jwtUserDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void shouldGetUnauthorizedWithoutRole() throws Exception {

        mvc.perform(get("/user"))
            .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(roles = "USER")
    public void getUserSuccessfullyWithUserRole() throws Exception {

        Authority authority = new Authority();
        authority.setId(1L);
        authority.setName(AuthorityName.ROLE_ADMIN);
        List<Authority> authorities = Arrays.asList(authority);

        User user = new User();
        user.setUsername("username");
        user.setAuthorities(authorities);
        user.setEnabled(Boolean.TRUE);
        user.setLastPasswordResetDate(new Date(System.currentTimeMillis() + 1000 * 1000));

        JwtUser jwtUser = JwtUserFactory.create(user);

        // Always retrieve the username when getting username from token
        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn(user.getUsername());

        // Always find jwtUser when loadUserByUsername
        when(jwtUserDetailsService.loadUserByUsername(eq(user.getUsername()))).thenReturn(jwtUser);

        mvc.perform(get("/user").header("Authorization", "Bearer anyToken"))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void getUsersSuccessfully() throws Exception{
        mvc.perform(get("/user/all"))
            .andExpect(status().is2xxSuccessful());
    }

    @Test
    public void addUserSuccessfully() throws  Exception{
        UserDto userDto = new UserDto();
        userDto.setEmail("add.user@pi.com");
        userDto.setFirstname("Addded");
        userDto.setLastname("User");
        userDto.setUsername("adduser");
        userDto.setPassword("password");

        Gson gson = new Gson();
        String json = gson.toJson(userDto, UserDto.class);

        mvc.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.username").value("adduser"));
    }

    @Test
    public void addUserBadUsername() throws  Exception{
        UserDto userDto = new UserDto();
        userDto.setEmail("hugo.magfgfrtin@pi.com");
        userDto.setFirstname("Hugoll");
        userDto.setLastname("Martin");
        userDto.setUsername("hma");
        userDto.setPassword("password");

        Gson gson = new Gson();
        String json = gson.toJson(userDto, UserDto.class);

        mvc.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void addUserBadEmail() throws  Exception{
        UserDto userDto = new UserDto();
        userDto.setEmail("hugo.martfggfinpi.com");
        userDto.setFirstname("Hugoll");
        userDto.setLastname("Martin");
        userDto.setUsername("hmffffa");
        userDto.setPassword("password");

        Gson gson = new Gson();
        String json = gson.toJson(userDto, UserDto.class);

        mvc.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }

    @Test
    public void addUserExistingEmail() throws  Exception{
        UserDto userDto = new UserDto();
        userDto.setEmail("admin@admin.com");
        userDto.setFirstname("Hugoll");
        userDto.setLastname("Martin");
        userDto.setUsername("hma");
        userDto.setPassword("password");

        Gson gson = new Gson();
        String json = gson.toJson(userDto, UserDto.class);

        mvc.perform(post("/user/add").contentType(MediaType.APPLICATION_JSON).content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is4xxClientError());
    }

    @Test
    @WithMockUser(username = "user")
    public void addUserLocation() throws Exception{
        PointDto locationDto = new PointDto();
        locationDto.setX(4.8467);
        locationDto.setY(45.7485);
        Gson gson = new Gson();
        String json = gson.toJson(locationDto, PointDto.class);

        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("user");

        mvc.perform(put("/user/location").header("Authorization","Bearer anyToken")
            .contentType(MediaType.APPLICATION_JSON).content(json)
            .accept(MediaType.APPLICATION_JSON))
            .andExpect(status().is2xxSuccessful());

        assertEquals(userRepository.findByUsername("user").getLocation().getX(),locationDto.getX(),0);
        assertEquals(userRepository.findByUsername("user").getLocation().getY(),locationDto.getY(),0);

        mvc.perform(get("/user/location").header("Authorization","Bearer anyToken"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.x").value("4.8467"))
            .andExpect(jsonPath("$.y").value("45.7485"));
    }

    @Test
    @WithMockUser(username = "user")
    public void correctlySetDistanceGoal() throws Exception{
        DistanceDto distanceDto = new DistanceDto();
        distanceDto.setDistance((float)10);

        Gson gson = new Gson();
        String json = gson.toJson(distanceDto, DistanceDto.class);

        when(jwtTokenUtil.getUsernameFromToken(any())).thenReturn("admin");

        mvc.perform(put("/user/objectif").contentType(MediaType.APPLICATION_JSON).header("Authorization", "Bearer anyToken").content(json).accept(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk());

        assertEquals(10, userRepository.findByUsername("admin").getObjectifHebdo(), 0);

        mvc.perform(get("/user/objectif").header("Authorization","Bearer anyToken"))
            .andExpect(status().is2xxSuccessful())
            .andExpect(jsonPath("$.objectif").value("10.0"));
    }
}
