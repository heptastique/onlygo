package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.Embeddable;

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherWind {

    private double speed;
    private double deg;

    public WeatherWind(){
    }

    public WeatherWind(double speed, double degree) {
        this.speed = speed;
        this.deg = degree;
    }

    @Override
    public String toString() {
        return "WeatherWind{" +
            ", speed=" + speed +
            ", deg=" + deg +
            '}';
    }

    public double getSpeed() {
        return speed;
    }

    public double getDeg() {
        return deg;
    }
}
