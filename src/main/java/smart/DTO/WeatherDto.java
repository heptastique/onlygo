package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import smart.Entities.WeatherData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    public int cod;
    public String message;
    public List<WeatherData> list;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<WeatherData> getList() {
        return list;
    }

    public void setList(List<WeatherData> list) {
        this.list = list;
    }

    public WeatherDto(){
    }

    public WeatherDto(int cod, List<WeatherData> list) {
        this.cod = cod;
        this.list = list;
    }

    public WeatherDto(int cod, String message, List<WeatherData> list) {
        this.cod = cod;
        this.message = message;
        this.list = list;
    }
}
