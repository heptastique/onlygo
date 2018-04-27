package smart.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import smart.Entities.*;
import smart.Services.CentreInteretService;
import smart.Services.SportService;
import smart.Services.TimeFrameService;

import javax.swing.*;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class ProgramActivities
{
    @Autowired
    private TimeFrameService timeFrameService;

    @Autowired
    private SportService sportService;

    @Autowired
    private CentreInteretService centreInteretService;

    final double kDistanceUserToCentreInteretEvaluation = 0.0002;
    final double cDistanceUserToCentreInteretEvaluation = 1.0;

    class TimeFrameCentreInteret
    {
        private TimeFrame timeFrame;
        private CentreInteret centreInteret;
        private double evaluation;
    }

    public Programme calculate(User user)
    {
        // @TODO For each Sport
        // @TODO Try to split

        double objectifHebdo = user.getObjectifHebdo();
        Point userLocation = user.getLocation();

        double distanceUserToCentreInteret;
        double distanceUserToCentreInteretEvaluation;
        double centreInteretEvaluation;
        TimeFrameCentreInteret timeFrameCentreInteret;
        List <TimeFrameCentreInteret> timeFrameCentreInterets = new ArrayList <TimeFrameCentreInteret> ();

        // @TODO Generalize
        Iterable <Sport> sports = sportService.getAllSports();
        Sport course = new Sport();
        for (Sport sport : sports)
        {
            course = sport;
        }

        // For each TimeFrame
        Iterable <TimeFrame> timeFrames = timeFrameService.getTimeFrameAll();
        for (TimeFrame timeFrame : timeFrames)
        {
            // For each CentreInteret
            Iterable<CentreInteret> centreInterets = centreInteretService.getCentreInteretAll();
            for (CentreInteret centreInteret : centreInterets)
            {
                timeFrameCentreInteret = new TimeFrameCentreInteret();
                timeFrameCentreInteret.timeFrame = timeFrame;
                timeFrameCentreInteret.centreInteret = centreInteret;

                // Calculate Distance from User to CentreInteret
                distanceUserToCentreInteret = sqrt(pow(userLocation.getX() - centreInteret.getPoint().getX(), 2) +
                    pow(userLocation.getY() - centreInteret.getPoint().getY(), 2));

                // Calculate Evaluation of Distance from User to CentreInteret
                distanceUserToCentreInteretEvaluation = exp(-kDistanceUserToCentreInteretEvaluation * distanceUserToCentreInteret);

                // Calculate CentreInteret Evaluation
                centreInteretEvaluation = cDistanceUserToCentreInteretEvaluation * distanceUserToCentreInteretEvaluation / 1.0;

                // Calculate TimeFrameCentreInteret Evaluation
                timeFrameCentreInteret.evaluation = timeFrame.getEvaluation() * centreInteretEvaluation;

                // Insert TimeFrameCentreInteret in List, sorted by Evaluation
                int index = 0;
                while (index < timeFrameCentreInterets.size() && timeFrameCentreInterets.get(index).evaluation > timeFrameCentreInteret.evaluation)
                {
                    index = index + 1;
                }
                timeFrameCentreInterets.add(index, timeFrameCentreInteret);
            }
        }

        

        // Create Activity
        activity = new Activity();
        // @TODO
        // activity.setDate(timeFrame.getDate());
        activity.setDate(new Date());
        activity.setDistance((float)objectifHebdo);
        activity.setSport(course);
        activity.setEstRealisee(false);
        //activity.setProgramme();

        // Create Activities
        activities = new ArrayList <Activity>();
        activities.add(activity);

        // Create Program
        programme = new Programme();
        programme.setUser(user);
        programme.setActivites(activities);
        activity.setProgramme(programme);

        return programme;
    }

    private ProgramActivities()
    {}

    /** Instance unique pré-initialisée */
    private static ProgramActivities INSTANCE = new ProgramActivities();

    /** Point d'accès pour l'instance unique du singleton */
    public static ProgramActivities getInstance()
    {
        return INSTANCE;
    }
}
