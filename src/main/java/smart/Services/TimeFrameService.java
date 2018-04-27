package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.FindByJour;
import smart.Algorithms.TimeFrameEvaluation;
import smart.Entities.*;
import smart.Repositories.TimeFrameRepository;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;


@Service
public class TimeFrameService {

    @Autowired
    private TimeFrameRepository timeFrameRepository;

    public Iterable <TimeFrame> getTimeFrameAll() {
        return timeFrameRepository.findAll();
    }

    public TimeFrame getTimeFrame(String time) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date date;
        int startHour;
        if (time.equals("now")) {
            date = new Date();
            startHour = date.getHours();
        } else {
            String day = time.substring(0, 10);
            try {
                date = sdf.parse(day);
            } catch (Exception e) {
                date = null;
            }
            startHour = Integer.parseInt(time.substring(11, 13));
        }
        Jour jour = FindByJour.findDay(date);
        TimeFrame timeFrame = timeFrameRepository.findByJourHour(jour,startHour);
        return timeFrame;
    }

    public double getEvaluation(String time){
        TimeFrame timeFrame = getTimeFrame(time);
        return timeFrame.getEvaluation();
    }

    public void updateEvaluation(Iterable<WeatherData> listWeatherDatas, Iterable<DonneeAthmospherique> listDonneeAthmospheriques){
        Date currentDate = new Date();
        Calendar currentDateCalendar = Calendar.getInstance();
        currentDateCalendar.setTime(currentDate);
        currentDateCalendar.set(Calendar.HOUR,0);
        currentDateCalendar.set(Calendar.MINUTE,0);
        currentDateCalendar.set(Calendar.SECOND,0);
        currentDateCalendar.set(Calendar.MILLISECOND,0);
        Date dateFiltered = currentDateCalendar.getTime();

        Iterable<TimeFrame> listTimeFrames = getTimeFrameAll();
        for ( WeatherData weatherData : listWeatherDatas){

            if ( dateFiltered.before(weatherData.getDate()) || dateFiltered.compareTo(weatherData.getDate()) == 0)
            {
                Jour jourMeteo = FindByJour.findDay(weatherData.getDate());
                for ( TimeFrame timeFrame : listTimeFrames){
                    if ( jourMeteo.compareTo(timeFrame.getJour()) == 0 && timeFrame.getHeureDebut()== weatherData.getDate().getHours()){
                        timeFrame.setWeatherData(weatherData);
                    }
                }
            }
        }
        // set the date too because all dataframes can be set here
        for ( DonneeAthmospherique donneeAthmospherique : listDonneeAthmospheriques ){
            if ( dateFiltered.before(donneeAthmospherique.getDate()) )
            {
                Jour jourAthmospherique = FindByJour.findDay(donneeAthmospherique.getDate());
                for ( TimeFrame timeFrame : listTimeFrames){
                    if ( jourAthmospherique.compareTo(timeFrame.getJour()) == 0){
                        timeFrame.setDonneeAthmospherique(donneeAthmospherique);
                        timeFrame.generateDate(donneeAthmospherique.getDate());
                    }
                }
            }
            if ( dateFiltered.compareTo(donneeAthmospherique.getDate()) == 0 ){
                Jour jourAthmospherique = FindByJour.findDay(donneeAthmospherique.getDate());
                for ( TimeFrame timeFrame : listTimeFrames){
                    if ( jourAthmospherique.compareTo(timeFrame.getJour()) == 0 && currentDate.getHours()<= timeFrame.getHeureDebut()){
                        timeFrame.setDonneeAthmospherique(donneeAthmospherique);
                        timeFrame.generateDate(donneeAthmospherique.getDate());
                    }
                }
            }
        }
        for ( TimeFrame timeFrame : listTimeFrames){
            double evaluation = TimeFrameEvaluation.getInstance().calculate(timeFrame);
            timeFrame.setEvaluation(evaluation);
            timeFrameRepository.save(timeFrame);
        }
    }
}


