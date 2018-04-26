package smart.Services;

import org.aspectj.weaver.tools.WeavingAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smart.DTO.WeatherDto;
import smart.Entities.WeatherData;
import smart.Repositories.WeatherDataRepository;

@Service
public class WeatherDataService {
    @Autowired
    private WeatherDataRepository weatherDataRepository;
    @Value("${weatherApiKey}")
    private String weatherApiKey;

    public WeatherDto UpdateWeatherData(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.openweathermap.org/data/2.5/forecast?id=2996944&units=metric&appid="+weatherApiKey;
        //WeatherDto wdto = restTemplate.getForObject(url,WeatherDto.class);
        WeatherDto wdto = restTemplate.getForObject(url,
            WeatherDto.class);
        for(int i = 0 ; i<wdto.getList().size();i++){
            WeatherData wd = wdto.getList().get(i);
            wd.generateDate();
            weatherDataRepository.save(wd);
        }
        return wdto;
    }
    public Iterable <WeatherData> getWeatherDataAll() {
        return weatherDataRepository.findAll();
    }
}
