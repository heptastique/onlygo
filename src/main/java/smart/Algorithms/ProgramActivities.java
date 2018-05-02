package smart.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.DTO.ActivityDTO;
import smart.Entities.*;
import smart.Repositories.ActivityRepository;
import smart.Services.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

@Service
public class ProgramActivities
{
    @Autowired
    private SportService sportService;

    @Autowired
    private TimeFrameService timeFrameService;

    @Autowired
    private CentreInteretService centreInteretService;

    @Autowired
    private ActivityService activityService;

    @Autowired
    private ProgrammeService programmeService;

    @Autowired
    private ActivityRepository activityRepository;

    private class TimeFrameCentreInteret
    {
        private TimeFrame timeFrame;
        private CentreInteret centreInteret;
        private double evaluation;
    }

    public Programme calculate(User user)
    {
        // @TODO HardCoded replaces distanceCourseMax
        // double objectifRemaining = user.getObjectifHebdo();
        double objectifHebdo = user.getObjectifHebdo();
        List <Float> objectifsDistance = new ArrayList <> ();
        objectifsDistance.add((float) objectifHebdo / 2);
        objectifsDistance.add((float) objectifHebdo / 4);
        objectifsDistance.add((float) objectifHebdo / 4);
        //double distanceCourseMax= user.getDistanceMax();
        Point userLocation = user.getLocation();

        double distanceUserToCentreInteret;
        double distanceUserToCentreInteretEvaluation;
        final double kDistanceUserToCentreInteretEvaluation = 0.0002;
        final double cDistanceUserToCentreInteretEvaluation = 1.0;
        double centreInteretEvaluation;
        TimeFrameCentreInteret timeFrameCentreInteret;
        TimeFrameCentreInteret timeFrameCentreInteret1;
        List <TimeFrameCentreInteret> timeFrameCentreInterets = new ArrayList <> ();
        List <TimeFrameCentreInteret> tempTimeFrameCentreInterets;
        boolean timeFrameCentreInteretToUpdate;

        Sport course = sportService.getSport("Course");

        Date currentMondayMidnight = new Date();
        while (FindByJour.findDay(currentMondayMidnight) != Jour.LUNDI)
        {
            currentMondayMidnight.setDate(currentMondayMidnight.getDate() - 1);
        }
        currentMondayMidnight.setHours(0);
        currentMondayMidnight.setMinutes(0);
        currentMondayMidnight.setSeconds(0);

        Date nextMonday = new Date();
        nextMonday.setDate(nextMonday.getDate() + 1);
        while (FindByJour.findDay(nextMonday) != Jour.LUNDI)
        {
            nextMonday.setDate(nextMonday.getDate() + 1);
        }

        // For each TimeFrame of the current Week
        for (TimeFrame timeFrame : timeFrameService.getTimeFrameAll())
        {
            // If TimeFrame is in the current Week
            if (timeFrame.getDate().getMonth() < nextMonday.getMonth() || timeFrame.getDate().getDate() < nextMonday.getDate())
            {
                // For each CentreInteret
                for (CentreInteret centreInteret : centreInteretService.getCentreInteretAll())
                {
                    // Create TimeFrameCentreInteret
                    timeFrameCentreInteret = new TimeFrameCentreInteret();
                    timeFrameCentreInteret.timeFrame = timeFrame;
                    timeFrameCentreInteret.centreInteret = centreInteret;

                    // Calculate Distance from User to CentreInteret
                    distanceUserToCentreInteret = sqrt(pow((userLocation.getX() - centreInteret.getPoint().getX()) * 111000, 2) +
                        pow((userLocation.getY() - centreInteret.getPoint().getY()) * 111000 * cos(userLocation.getX() - centreInteret.getPoint().getX()), 2));

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
        }

        // Create Activities List
        List <Activity> activities = new ArrayList <> ();
        Activity savedActivity;

        Programme programme = programmeService.getActiveProgrammeOfUser(user.getUsername());
        // If no Active Program, no activities realized
        if (programme == null)
        {
            // Create Program
            programme = new Programme();
            programme.setUser(user);
            programme.setObjectifDistance(objectifHebdo);
            programme.setDateDebut(currentMondayMidnight);
        }
        // If Active Program, some Activities already realized
        else
        {
            // Update remaining Objectif
            for (Realisation realisation : programme.getRealisations())
            {
                objectifHebdo = objectifHebdo - realisation.getDistance();
            }

            // Add already realized Activities to Activities List and remove non realized Activities
            for (Activity activity : programme.getActivites())
            {
                if (activity.isEstRealisee())
                {
                    activities.add(activity);
                    objectifsDistance.remove(activity.getDistance());
                }
                                                                                                                    else
                {
                    activityRepository.delete(activity);
                }
            }
            programme.setActivites(activities);
            programme = programmeService.saveProgram(programme);
        }

        // @TODO For each Sport

        // While Week Objective is not Completed
        int index = 0;
        int timeFrameCentreInteretIndex;
        while (objectifsDistance.size() > 0 && index < timeFrameCentreInterets.size())
        {
            timeFrameCentreInteret = timeFrameCentreInterets.get(index);

            // Create Activity
            ActivityDTO activity = new ActivityDTO();
            activity.setDate(timeFrameCentreInteret.timeFrame.getDate());
            activity.setTimeFrameId(timeFrameCentreInteret.timeFrame.getId());
            activity.setCentreinteretId(timeFrameCentreInteret.centreInteret.getId());
            activity.setSportName(course.getNom());


                activity.setDistance(objectifsDistance.get(0));
                objectifsDistance.remove(0);

                tempTimeFrameCentreInterets = new ArrayList <> ();

                // For each TimeFrameCentreInteret
                int index1 = 0;
                while (index1 < timeFrameCentreInterets.size())
                {
                    timeFrameCentreInteret1 = timeFrameCentreInterets.get(index1);

                    timeFrameCentreInteretToUpdate = false;

                    // Decrease Evaluation if same TimeFrame Day
                    if (timeFrameCentreInteret1.timeFrame.getJour() == timeFrameCentreInteret.timeFrame.getJour())
                    {
                        timeFrameCentreInteret1.evaluation = timeFrameCentreInteret1.evaluation * 0.9;
                        timeFrameCentreInteretToUpdate = true;
                    }

                    // Decrease Evaluation if same CentreInteret
                    if (timeFrameCentreInteret1.centreInteret == timeFrameCentreInteret.centreInteret)
                    {
                        timeFrameCentreInteret1.evaluation = timeFrameCentreInteret1.evaluation * 0.9;
                        timeFrameCentreInteretToUpdate = true;
                    }

                    // If TimeFrameCentreInteret Evaluation updated
                    if (timeFrameCentreInteretToUpdate)
                    {
                        // Add TimeFrameCentreInteret to update to temp List
                        int index2 = 0;
                        while (index2 < tempTimeFrameCentreInterets.size() && tempTimeFrameCentreInterets.get(index2).evaluation > timeFrameCentreInteret1.evaluation)
                        {
                            index2 = index2 + 1;
                        }
                        tempTimeFrameCentreInterets.add(index2, timeFrameCentreInteret1);
                        // Remove TimeFrameCentreInteret to update from List
                        timeFrameCentreInterets.remove(timeFrameCentreInteret1);
                        index1 = index1 - 1;
                    }

                    index1 = index1 + 1;
                }

                // Add all updated TimeFrameCentreInteret to List sorted by Evaluation
                for (TimeFrameCentreInteret timeFrameCentreInteret2 : tempTimeFrameCentreInterets)
                {
                    index1 = 0;
                    while (index1 < timeFrameCentreInterets.size() && timeFrameCentreInterets.get(index1).evaluation > timeFrameCentreInteret2.evaluation)
                    {
                        index1 = index1 + 1;
                    }
                    timeFrameCentreInterets.add(index1, timeFrameCentreInteret2);
                }

            // Add Activity to Activities List
            savedActivity = activityService.addActivity(activity, false);
            activities.add(savedActivity);

            index = index + 1;
        }

        programme = programmeService.saveProgram(programme);

        // Set Activities Program
        for (Activity activityTemp : activities)
        {
            activityTemp.setProgramme(programme);
            activityRepository.save(activityTemp);
        }

        // Set Program Activities
        programme.setActivites(activities);

        programme = programmeService.saveProgram(programme);

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
