package test.Services;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;
import smart.Application;
import smart.DTO.*;
import smart.Entities.WeatherData;
import smart.Repositories.WeatherDataRepository;
import smart.Services.WeatherDataService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

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

    @Value("${weatherApiKey}")
    private String weatherApiKey;

    @Before
    public void setup() {
        mvc = MockMvcBuilders
            .webAppContextSetup(context)
            .apply(springSecurity())
            .build();
    }

    @Test
    public void DataTransferStructure(){

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.openweathermap.org/data/2.5/forecast?id=2996944&units=metric&appid="+weatherApiKey;
        //WeatherDto wdto = restTemplate.getForObject(url,WeatherDto.class);
        WeatherDto wdto = restTemplate.getForObject(url,
            WeatherDto.class);
        assertNotNull(wdto);
        assert(wdto.getList().size()>0);
        assert(wdto.getList().get(0).getWeather().size()>0);
        assert(wdto.getList().get(0).getWeather().get(0).getId()>100);
    }

    @Test
    public void dtoToEntity(){
        WeatherWindDto wwd = new WeatherWindDto(5.41,197.5);
        WeatherConditionDto wcd = new WeatherConditionDto(802,"Clouds","scattered clouds","03d");
        WeatherMainInformationDto wmid = new WeatherMainInformationDto(16.24,14.69,16.24);
        ArrayList<WeatherConditionDto> weatherConditionDtos = new ArrayList<>();
        weatherConditionDtos.add(wcd);
        WeatherRainDto wrd = new WeatherRainDto(0.0038000000000018);
        WeatherElementDto weatherElementDto = new WeatherElementDto("2018-04-30 21:00:00",wmid,wwd,weatherConditionDtos,wrd);
        WeatherData wd = weatherDataService.dtoToEntity(weatherElementDto);
        assertEquals(wd.getWeather().getId(),weatherElementDto.getWeather().get(0).getId());
    }

    @Test
    public void DataIsCorrectlyFetched() {
        WeatherDto data = weatherDataService.fetchWeatherData();
        assertEquals(200, data.getCod());
        assertNotNull(data.getList());
    }

    @Test
    public void DataContainsWeatherCondition(){
        WeatherDto datadto = weatherDataService.fetchWeatherData();
        WeatherElementDto data = datadto.getList().get(1);
        assert(data.getWeather().get(0).getId()>100);
    }


    @Test
    public void DataIsUpdated(){
        List<WeatherData> wds = weatherDataService.UpdateWeatherData();
        WeatherData readFromPersistence;
        readFromPersistence=  weatherDataRepository.findById(wds.get(0).getId()).get();
        assertEquals(readFromPersistence.toString(),wds.get(0).toString());
    }

    @Test
    public void WeatherConditionPersistence(){
        List<WeatherData> wds = weatherDataService.UpdateWeatherData();
        for (WeatherData fetchedData:wds) {
            WeatherData readFromPersistence;
            readFromPersistence=  weatherDataRepository.findById(fetchedData.getId()).get();
            assertNotNull(readFromPersistence.getWeather());
            assertNotNull(readFromPersistence.getWeather());
            assertEquals(readFromPersistence.getWeather().getId(),fetchedData.getWeather().getId());

        }
    }
}
