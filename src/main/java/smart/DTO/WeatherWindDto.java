package smart.DTO;

public class WeatherWindDto {

    private double speed;
    private double deg;

    public WeatherWindDto(){
    }

    public WeatherWindDto(double speed, double degree) {
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

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public void setDeg(double deg) {
        this.deg = deg;
    }
}
