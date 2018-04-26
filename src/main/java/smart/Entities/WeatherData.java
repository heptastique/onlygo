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
    private MainInformation main;
    @Embedded
    private WindInformation wind;
    @Column(name= "precipitation")
    private double precipitation;

    public WeatherData(){
    }

    public WeatherData(long dt, MainInformation main, WindInformation wind, double precipitation) {
        this.dt = dt;
        this.main = main;
        this.wind = wind;
        this.precipitation = precipitation;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDt(long dt) {
        this.dt = dt;
    }


    public void setWind(WindInformation wind) {
        this.wind = wind;
    }

    public void setPrecipitation(double precipitation) {
        this.precipitation = precipitation;
    }

    public MainInformation getMain() {
        return main;
    }

    public void setMain(MainInformation main) {
        this.main = main;
    }

    @Override
    public String toString() {
        return "WeatherData{" +
            "id=" + id +
            ", dt=" + dt +
            ", main=" + main +
            ", wind=" + wind +
            ", precipitation=" + precipitation +
            '}';
    }

    //external reading for MainInformation attributes
    public double getTemp() {
        return main.getTemp();
    }


    public double getTemp_min() {
        return main.getTemp_min();
    }



    public double getTemp_max() {
        return main.getTemp_max();
    }
    //External reading for WindInformation
    public double getSpeed() {
        return wind.getSpeed();
    }

    public double getDeg() {
        return wind.getDeg();
    }
}

@Embeddable
@JsonIgnoreProperties(ignoreUnknown = true)
class MainInformation {

    private double temp;
    private double temp_min;
    private double temp_max;

    public MainInformation(){
    }

    public MainInformation(double temp, double temp_min, double temp_max) {
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
class WindInformation {

    public double speed;
    public double deg;

    public WindInformation(){
    }

    public WindInformation(double speed, double degree) {
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

