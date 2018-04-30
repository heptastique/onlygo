package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherMainInformationDto {

    private double temp;
    private double temp_min;
    private double temp_max;

    public WeatherMainInformationDto(){
    }

    public WeatherMainInformationDto(double temp, double temp_min, double temp_max) {
        this.temp = temp;
        this.temp_min = temp_min;
        this.temp_max = temp_max;
    }



    @Override
    public String toString() {
        return "Weather{" +
            ", temp=" + temp +
            ", temp_min=" + temp_min +
            ", temp_max=" + temp_max +
            '}';
    }


    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTemp_min() {
        return temp_min;
    }

    public void setTemp_min(double temp_min) {
        this.temp_min = temp_min;
    }

    public double getTemp_max() {
        return temp_max;
    }

    public void setTemp_max(double temp_max) {
        this.temp_max = temp_max;
    }
}
