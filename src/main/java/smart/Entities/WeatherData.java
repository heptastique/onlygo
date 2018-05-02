package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name="Weather")
public class WeatherData {
    @Id
    @Column(name="Weather_Id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String dt_txt;
    private Date date;
    @Embedded
    private WeatherMainInformation mainStation;
    @Embedded
    private WeatherWind wind;
    @Embedded
    private WeatherCondition weather;
    @Column(name= "precipitation")
    private double precipitation;

    //was this data fetched
    private boolean fetched;

    public WeatherData(){
    }

    public WeatherData(String dt_txt, WeatherMainInformation main,
                       WeatherWind wind, double precipitation,boolean fetched) {
        this.dt_txt = dt_txt;
        this.generateDate();
        this.wind = wind;
        this.precipitation = precipitation;
        this.mainStation = main;
        this.fetched = fetched;
    }

    public WeatherData(String dt_txt, WeatherMainInformation main,
                       WeatherWind wind, double precipitation) {
        this.dt_txt = dt_txt;
        this.generateDate();
        this.wind = wind;
        this.precipitation = precipitation;
        this.mainStation = main;
        this.fetched=false;
    }
    public WeatherData( WeatherData weatherData, Date date){
        this.date = date;
        this.mainStation = weatherData.getMain();
        this.wind = weatherData.getWind();
        this.precipitation = weatherData.getPrecipitation();
        this.weather = weatherData.getWeather();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dt_txt = sdf.format(date);
        this.fetched=false;

    }
    public WeatherData( WeatherData weatherData, Date date,boolean fetched){
        this.date = date;
        this.mainStation = weatherData.getMain();
        this.wind = weatherData.getWind();
        this.precipitation = weatherData.getPrecipitation();
        this.weather = weatherData.getWeather();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.dt_txt = sdf.format(date);
        this.fetched = fetched;

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
        }
    }

    public void setWind(WeatherWind wind) {
        this.wind = wind;
    }

    public void setPrecipitation(double precipitation) { this.precipitation = precipitation; }

    public WeatherMainInformation getMain() {
        return mainStation;
    }

    public void setMain(WeatherMainInformation main) {
        this.mainStation = main;
    }

    public void setWeather(WeatherCondition weather) {
        this.weather = weather;
    }

    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(this.date);
        return "WeatherData{" +
            "id=" + id +
            ", date=" + strDate +
            ", main=" + mainStation +
            ", wind=" + wind +
            ", precipitation=" + precipitation +
            '}';
    }

    //external reading for MainInformation attributes
    public double getTemp() {
        return mainStation.getTemp();
    }


    public double getTemp_min() {
        return mainStation.getTemp_min();
    }

    public Date getDate() {
        return date;
    }

    public WeatherWind getWind() {
        return wind;
    }

    public double getPrecipitation() {
        return precipitation;
    }

    public double getTemp_max() {
        return mainStation.getTemp_max();
    }
    //External reading for WindInformation
    public double getSpeed() {
        return wind.getSpeed();
    }

    public double getDeg() {
        return wind.getDeg();
    }

    //External reading for weather conditions
    public int getWeatherConditionCode(int index){
        return weather.getId();
    }

    public WeatherCondition getWeather() {
        return weather;
    }

    public WeatherMainInformation getMainStation() {
        return mainStation;
    }

    public void setMainStation(WeatherMainInformation mainStation) {
        this.mainStation = mainStation;
    }

    public boolean isFetched() {
        return fetched;
    }

    public void setFetched(boolean fetched) {
        this.fetched = fetched;
    }
}





