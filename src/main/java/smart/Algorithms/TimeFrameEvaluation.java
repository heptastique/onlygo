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

        double pollution;
        if ( donneeAthmospherique != null) {
            pollution= donneeAthmospherique.getIndice();
            pollution = 1 - pollution / 100.0;
        } else {
            pollution = 0.5;
        }

        double temperature;
        double weather;
        double wind;
        double rain;

        if ( weatherData != null) {
            temperature = weatherData.getTemp();
            temperature = exp(-1.0/2.0*(pow((temperature - muTemperature)/sigmaTemperature, 2)));

            // @TODO Acces au temps : Dégagé, Nuageux, Pluvieux, Orageux, ...
            // double weather = weatherData.getWeatherLike();
            weather = 2;
            weather = 1 - weather / 3.0;

            wind = weatherData.getSpeed();
            wind = exp(-wind*kWind);

            rain = weatherData.getPrecipitation();
            rain = exp(-rain*kRain);
        }
        else
        {
            temperature = 0.5;
            weather = 0.5;
            wind = 0.5;
            rain = 0.5;
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
