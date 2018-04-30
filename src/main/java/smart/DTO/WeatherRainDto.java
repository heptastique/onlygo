package smart.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public class WeatherRainDto {
    @JsonProperty(value = "3h")
    private double precipitation_3h;

    public WeatherRainDto() {
    }

    public WeatherRainDto(double precipitation_3h) {
        this.precipitation_3h = precipitation_3h;
    }

    public double getPrecipitation_3h() {
        return precipitation_3h;
    }

    public void setPrecipitation_3h(double precipitation_3h) {
        this.precipitation_3h = precipitation_3h;
    }
}
