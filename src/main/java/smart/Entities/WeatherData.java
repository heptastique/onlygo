package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@Entity
@Table(name="Weather")
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherData {
    @Id
    @Column(name="Weather_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String dt_txt;
    private Date date;
    @Embedded
    private MainInformation main;
    @Embedded
    private WindInformation wind;
    @Column(name= "precipitation")
    private double precipitation;

    public WeatherData(){
    }

    public WeatherData(String dt_txt, MainInformation main, WindInformation wind, double precipitation) {
        this.dt_txt = dt_txt;
        this.generateDate();
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

    public String getDt_txt() {
        return dt_txt;
    }

    public void setDt_txt(String dt_txt) {
        this.dt_txt = dt_txt;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void generateDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        try {
            this.date=sdf.parse(dt_txt);

        } catch (ParseException e) {
            System.err.println("What have you done ???");
        };
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
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(this.date);
        return "WeatherData{" +
            "id=" + id +
            ", date=" + strDate +
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

    public Date getDate() {
        return date;
    }

    public WindInformation getWind() {
        return wind;
    }

    public double getPrecipitation() {
        return precipitation;
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

