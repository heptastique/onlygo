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
import smart.Entities.CentreInteret;
import smart.Repositories.CentreInteretRepository;
import smart.Services.CentreInteretService;

import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class CentreInteretServiceTest {
    private MockMvc mvc;
    @Autowired
    private WebApplicationContext context;

    @Autowired
    CentreInteretService centreInteretService;

    @Autowired
    CentreInteretRepository centreInteretRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void DataIsCorrectlyFinded() {
        //
        Iterator<CentreInteret> iterator = centreInteretService.getCentreInteretAll().iterator();
        CentreInteret centreInteret = iterator.next();
        assertEquals("Parc de la Tête d'Or", centreInteret.getName());
        assertTrue(centreInteretRepository.existsById((long)10000));
    }

}
