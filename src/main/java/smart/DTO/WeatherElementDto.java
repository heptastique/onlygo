package smart.DTO;

import java.util.List;

public class WeatherElementDto {

    private String dt_txt;

    private WeatherMainInformationDto main;
    private WeatherWindDto wind;
    private List<WeatherConditionDto> weather;
    private double precipitation;

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

    public double getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }
}
