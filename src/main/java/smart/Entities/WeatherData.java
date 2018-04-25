package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;



@Entity
@Table(name="Weather")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @Id
    @Column(name="Weather_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private long dt;
    @Embedded
    private Weather main;
    @Embedded
    private  WeatherWind wind;
    @Column(name= "precipitation")
    private double precipitation;

    public WeatherData(){
    }

    public WeatherData(long dt, Weather main, WeatherWind wind, double precipitation) {
        this.dt = dt;
        this.main = main;
        this.wind = wind;
        this.precipitation = precipitation;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }


    public void setWind(WeatherWind wind) {
        this.wind = wind;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public Weather getMain() {
        return main;
    }

    public void setMain(Weather main) {
        this.main = main;
    }

    public String toString(){
        return "dt = " +dt+ main+ wind;
    }
}

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
class Weather{

    private double temp;
    private double temp_min;
    private double temp_max;

    public Weather(){
    }

    public Weather(double temp, double temp_min, double temp_max) {
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


@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherWind{

    public double speed;
    public double degree;

    public WeatherWind(){
    }

    public WeatherWind(double speed, double degree) {
        this.speed = speed;
        this.degree = degree;
    }

    @Override
    public String toString() {
        return "WeatherWind{" +
            ", speed=" + speed +
            ", degree=" + degree +
            '}';
    }
}

