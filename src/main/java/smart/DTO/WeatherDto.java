package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import smart.Entities.WeatherData;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherDto {
    private int cod;
    private String message;
    private List<WeatherElementDto> list;

    public int getCod() {
        return cod;
    }

    public void setCod(int cod) {
        this.cod = cod;
    }

    public List<WeatherElementDto> getList() {
        return list;
    }

    public void setList(List<WeatherElementDto> list) {
        this.list = list;
    }

    public WeatherDto(){
    }

    public WeatherDto(int cod, List<WeatherElementDto> list) {
        this.cod = cod;
        this.list = list;
    }

    public WeatherDto(int cod, String message, List<WeatherElementDto> list) {
        this.cod = cod;
        this.message = message;
        this.list = list;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
