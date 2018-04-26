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
import smart.Entities.AuthorityName;
import smart.Exceptions.EmailExistsException;
import smart.Services.AuthorityService;
import smart.Entities.Authority;

import static org.junit.Assert.assertEquals;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class AuthorityServiceTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    AuthorityService authorityService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void AuthorityUserIsFound() throws NotFoundException {
        Authority authoriy = authorityService.getAuthority(1);
        assertEquals(authoriy.getName(), AuthorityName.ROLE_USER);
    }

    @Test
    public void AuthorityAdminIsFound() throws NotFoundException {
        Authority authoriy = authorityService.getAuthority(2);
        assertEquals(authoriy.getName(), AuthorityName.ROLE_ADMIN);
    }

    @Test(expected = NotFoundException.class)
    public void AuthorityNotFound() throws NotFoundException {
      authorityService.getAuthority(3);
    }
}
