package smart.Algorithms;

import smart.Entities.DonneeAthmospherique;
import smart.Entities.TimeFrame;
import smart.Entities.WeatherData;

import static java.lang.Math.exp;
import static java.lang.Math.pow;

public class TimeFrameEvaluation
{
    private final double muTemperature = 20;
    private final double sigmaTemperature = 5.5;

    private final double kWind = 0.015;
    private final double kRain = 0.5;

    private final double cPollution = 1.0;
    private final double cTemperature = 1.0;
    private final double cWeather = 1.0;
    private final double cWind = 1.0;
    private final double cRain = 1.0;

    public double calculate(TimeFrame timeFrame)
    {
        DonneeAthmospherique donneeAthmospherique = timeFrame.getDonneeAthmospherique();
        WeatherData weatherData = timeFrame.getWeatherData();

        double pollution = 0.5;
        if ( donneeAthmospherique != null)
        {
            pollution= donneeAthmospherique.getIndice();
            pollution = 1 - pollution / 100.0;
        }

        double temperature = 0.5;
        double weather = 0.5;
        double wind = 0.5;
        double rain = 0.5;

        if ( weatherData != null)
        {
            temperature = weatherData.getTemp();
            temperature = exp(-1.0/2.0*(pow((temperature - muTemperature)/sigmaTemperature, 2)));

            int weatherCode = weatherData.getWeatherConditionCode(0);
            if (weatherCode >= 200 && weatherCode < 300) { // ThunderStorm
                weather = 0.2;
            } else if (weatherCode >= 300 && weatherCode < 400) { // Drizzle
                weather = 0.3;
            } else if (weatherCode >= 500 && weatherCode < 600) { // Rain
                weather = 0.4;
            } else if (weatherCode >= 600 && weatherCode < 700) { // Snow
                weather = 0.3;
            } else if (weatherCode >= 700 && weatherCode < 800) { // Atmosphere
                weather = 0.5;
            } else if (weatherCode == 800) { // Clear
                weather = 1;
            } else if (weatherCode == 801) { // Few Clouds
                weather = 0.9;
            } else if (weatherCode == 802) { // Scattered Clouds
                weather = 0.8;
            } else if (weatherCode == 803) { // Broken Clouds
                weather = 0.7;
            } else if (weatherCode == 804) { // Overcast Clouds
                weather = 0.6;
            } else {
                weather = 0.5;
            }

            wind = weatherData.getSpeed();
            wind = exp(-wind*kWind);

            rain = weatherData.getPrecipitation();
            rain = exp(-rain*kRain);
        }

        double evaluation = (cPollution*pollution + cTemperature*temperature + cWeather*weather + cWind*wind + cRain*rain) / 5.0;
        return evaluation;
    }

    private TimeFrameEvaluation()
    {}

    /** Instance unique pré-initialisée */
    private static TimeFrameEvaluation INSTANCE = new TimeFrameEvaluation();

    /** Point d'accès pour l'instance unique du singleton */
    public static TimeFrameEvaluation getInstance()
    {   return INSTANCE;
    }
}
