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
import smart.DTO.WeatherDto;
import smart.Entities.WeatherData;
import smart.Repositories.WeatherDataRepository;
import smart.Services.WeatherDataService;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class WeatherDataServiceTest {
    private MockMvc mvc;

    @Autowired
    private WebApplicationContext context;

    @Autowired
    WeatherDataService weatherDataService;

    @Autowired
    private WeatherDataRepository weatherDataRepository;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void DataIsCorrectlyFetched() {
        WeatherDto data = weatherDataService.UpdateWeatherData();
        assertEquals(200, data.getCod());
        assertNotNull(data.getList());
        WeatherData readFromPersistence =  weatherDataRepository.findById(data.getList().get(0).getId()).get();
        assertEquals(readFromPersistence.toString(),data.getList().get(0).toString());
    }

    @Test
    public void DataContainsWeatherCondition(){
        WeatherDto datadto = weatherDataService.UpdateWeatherData();
        WeatherData data = datadto.getList().get(1);
        assert(data.getWeatherConditionCode(0)>100);
    }
}
