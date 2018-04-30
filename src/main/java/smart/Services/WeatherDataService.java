package smart.Services;

import org.aspectj.weaver.tools.WeavingAdaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smart.Algorithms.FindByJour;
import smart.DTO.*;
import smart.Entities.*;
import smart.Repositories.WeatherDataRepository;

import javax.validation.constraints.Null;
import java.util.*;

@Service
public class WeatherDataService {
    @Autowired
    private WeatherDataRepository weatherDataRepository;
    @Value("${weatherApiKey}")
    private String weatherApiKey;

    public List<WeatherData> UpdateWeatherData(){
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
        ArrayList<WeatherData> wds = new ArrayList<>();
        WeatherDto wdto = fetchWeatherData();
        //add
        for(int i = 0 ; i<wdto.getList().size();i++){
            //wd = wdto.getList().get(i);
            wd = dtoToEntity(wdto.getList().get(i),true);
            wd.generateDate();
            weatherDataRepository.save(wd);
            if ( i == wdto.getList().size()-1) {
                calendar.setTime(wd.getDate());
            }
            wds.add(wd);
        }
        //add our forecast data
        calendar.add(Calendar.HOUR_OF_DAY,3);
        while ( calendar.get(Calendar.DAY_OF_YEAR) != (currentDay + 8)) {
            WeatherData wdNew = new WeatherData(wd, calendar.getTime());
            weatherDataRepository.save(wdNew);
            wds.add(wdNew);
            calendar.add(Calendar.HOUR_OF_DAY,3);
        }
        return wds;
    }

    public WeatherData dtoToEntity(WeatherElementDto wed){
        return dtoToEntity(wed,false);
    }
    public WeatherData dtoToEntity(WeatherElementDto weatherElementDto,boolean fetched){

        WeatherWindDto wwdto = weatherElementDto.getWind();
        WeatherWind ww = new WeatherWind(wwdto.getSpeed(),wwdto.getDeg());
        WeatherMainInformationDto wmiDto= weatherElementDto.getMain();
        WeatherMainInformation weatherMainInformation;
        weatherMainInformation = new WeatherMainInformation(wmiDto.getTemp()
            ,wmiDto.getTemp_min(),wmiDto.getTemp_max());

        WeatherCondition wc = new WeatherCondition();
        WeatherConditionDto wcdto=weatherElementDto.getWeather().get(0);

        wc.setId(wcdto.getId());
        wc.setDescription(wcdto.getDescription());
        wc.setMain(wcdto.getMain());
        wc.setIcon(wcdto.getIcon());

        WeatherData weatherData;
        try {
            WeatherRainDto wrd = weatherElementDto.getRain();
             weatherData= new WeatherData(weatherElementDto.getDt_txt(),
                weatherMainInformation, ww, wrd.getPrecipitation_3h(),true);
        }catch (NullPointerException npe){
            //sets precipitation to 0.0 if no rain
            weatherData= new WeatherData(weatherElementDto.getDt_txt(),
                weatherMainInformation, ww,0.0,true);
        }
        weatherData.setWeather(wc);
        return weatherData;
    }


    public WeatherDto fetchWeatherData(){
        RestTemplate restTemplate = new RestTemplate();
        String url = "http://api.openweathermap.org/data/2.5/forecast?id=2996944&units=metric&appid="+weatherApiKey;
        //WeatherDto wdto = restTemplate.getForObject(url,WeatherDto.class);
        WeatherDto wdto = restTemplate.getForObject(url,
            WeatherDto.class);
        return wdto;
    }
    public Iterable <WeatherData> getWeatherDataAll() {
        return weatherDataRepository.findAll();
    }
}
