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
import smart.Entities.DonneeAthmospherique;
import smart.Repositories.DonneeAthmospheriqueRepository;
import smart.Services.DonneeAthmospheriqueService;

import static org.junit.Assert.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class DonneeAthmospheriqueServiceTest {

    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    DonneeAthmospheriqueService donneeAthmospheriqueService;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void DataIsCorrectlyFetched() {
        PollutionDataDto data = donneeAthmospheriqueService.UpdateDonneeAthmospheriqueData();
        assertEquals("Lyon", data.getCommune());
        assertNotNull(data.getIndices());
    }

}
