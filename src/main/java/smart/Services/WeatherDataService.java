package smart.Services;

import org.aspectj.weaver.tools.WeavingAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smart.Algorithms.FindByJour;
import smart.DTO.WeatherDto;
import smart.Entities.Jour;
import smart.Entities.WeatherData;
import smart.Repositories.WeatherDataRepository;

import java.util.Calendar;
import java.util.Date;

@Service
public class WeatherDataService {
    @Autowired
    private WeatherDataRepository weatherDataRepository;
    @Value("${weatherApiKey}")
    private String weatherApiKey;

    public WeatherDto UpdateWeatherData(){
        Date currentDate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.set(Calendar.HOUR, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);
        currentDate = calendar.getTime();
        int currentDay = calendar.get(Calendar.DAY_OF_YEAR);
        WeatherData wd = null;

        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.openweathermap.org/data/2.5/forecast?id=2996944&units=metric&appid="+weatherApiKey;
        //WeatherDto wdto = restTemplate.getForObject(url,WeatherDto.class);
        WeatherDto wdto = restTemplate.getForObject(url,
            WeatherDto.class);
        for(int i = 0 ; i<wdto.getList().size();i++){
            wd = wdto.getList().get(i);
            wd.generateDate();
            weatherDataRepository.save(wd);
            if ( i == wdto.getList().size()-1) {
                calendar.setTime(wd.getDate());
            }
        }
        calendar.add(Calendar.HOUR_OF_DAY,3);
        while ( calendar.get(Calendar.DAY_OF_YEAR) != (currentDay + 8)) {
            WeatherData wdNew = new WeatherData(wd, calendar.getTime());
            weatherDataRepository.save(wdNew);
            calendar.add(Calendar.HOUR_OF_DAY,3);
        }
        return wdto;
    }
    public Iterable <WeatherData> getWeatherDataAll() {
        return weatherDataRepository.findAll();
    }
}
