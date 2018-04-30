package smart.DTO;

import java.util.List;

public class WeatherElementDto {

    private String dt_txt;

    private WeatherMainInformationDto main;
    private WeatherWindDto wind;
    private List<WeatherConditionDto> weather;
    private WeatherRainDto rain;

    public WeatherElementDto(String dt_txt, WeatherMainInformationDto main,
                             WeatherWindDto wind, List<WeatherConditionDto> weather, WeatherRainDto rain) {
        this.dt_txt = dt_txt;
        this.main = main;
        this.wind = wind;
        this.weather = weather;
        this.rain =rain;
    }

    public WeatherElementDto() {
    }

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public WeatherMainInformationDto getMain() {
        return main;
    }

    public void setMain(WeatherMainInformationDto main) {
        this.main = main;
    }

    public WeatherWindDto getWind() {
        return wind;
    }

    public void setWind(WeatherWindDto wind) {
        this.wind = wind;
    }

    public List<WeatherConditionDto> getWeather() {
        return weather;
    }

    public void setWeather(List<WeatherConditionDto> weather) {
        this.weather = weather;
    }

    public WeatherRainDto getRain() {
        return rain;
    }

    public void setRain(WeatherRainDto rain) {
        this.rain = rain;
    }
}
