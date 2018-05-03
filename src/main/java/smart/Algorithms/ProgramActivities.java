package smart.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.DTO.ActivityDTO;
import smart.Entities.*;
import smart.Repositories.ActivityRepository;
import smart.Repositories.ObjectifRepository;
import smart.Services.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static java.lang.Math.*;
import static java.lang.StrictMath.sqrt;

@Service
public class ProgramActivities
{
    private class TimeFrameCentreInteret
    {
        private TimeFrame timeFrame;
        private CentreInteret centreInteret;
        private double evaluation;
    }

    private void addTimeFrameCentreInteret(TimeFrameCentreInteret timeFrameCentreInteret)
    {
        int index = 0;
        while (index < timeFrameCentreInterets.size() && timeFrameCentreInterets.get(index).evaluation > timeFrameCentreInteret.evaluation)
        {
            index = index + 1;
        }
        timeFrameCentreInterets.add(index, timeFrameCentreInteret);
    }

    private Date prevMondayMidnight()
    {
        Date prevMondayMidnight = new Date();
        while (FindByJour.findDay(prevMondayMidnight) != Jour.LUNDI)
        {
            prevMondayMidnight.setDate(prevMondayMidnight.getDate() - 1);
        }
        prevMondayMidnight.setHours(0);
        prevMondayMidnight.setMinutes(0);
        prevMondayMidnight.setSeconds(0);
        return prevMondayMidnight;
    }

    private Date nextMonday()
    {
        Date nextMonday = new Date();
        nextMonday.setDate(nextMonday.getDate() + 1);
        while (FindByJour.findDay(nextMonday) != Jour.LUNDI)
        {
            nextMonday.setDate(nextMonday.getDate() + 1);
        }
        return nextMonday;
    }

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
    @Autowired
    private ObjectifRepository objectifRepository;

    private List <TimeFrameCentreInteret> timeFrameCentreInterets;

    public Programme calculate(User user)
    {
        List <Sport> sports = new ArrayList <> ();
        List <Objectif> objectifs = user.getObjectifs();
        List <List <Float> > objectifsSportsDistance = new ArrayList <> ();
        int index = 0;
        for (Sport sport : sportService.getAllSports())
        {
            sports.add(sport);
            objectifsSportsDistance.add(new ArrayList <> ());
            if (user.getObjectifs().get(index).getObjectif() > 0)
            {
                int split = 2;
                for (int seanceIndex = 0; seanceIndex < user.getNbSessions() - 1; seanceIndex = seanceIndex + 1) {
                    objectifsSportsDistance.get(index).add((float) objectifs.get(index).getObjectif() / split);
                    split = split * 2;
                }
                split = split / 2;
                objectifsSportsDistance.get(index).add((float) objectifs.get(index).getObjectif() / split);
            }
            index = index + 1;
        }
        Point userLocation = user.getLocation();

        double distanceUserToCentreInteret;
        double distanceUserToCentreInteret1;
        double distanceUserToCentreInteretEvaluation;
        double distanceInCentreInteret;
        double distanceInCentreInteretEvaluation;
        double distanceInCentreInteret1;
        double distanceInCentreInteretEvaluation1;
        double centreInteretEvaluation;
        TimeFrameCentreInteret timeFrameCentreInteret;
        TimeFrameCentreInteret timeFrameCentreInteret1;
        timeFrameCentreInterets = new ArrayList <> ();
        List <TimeFrameCentreInteret> tempTimeFrameCentreInterets;
        boolean timeFrameCentreInteretToUpdate;

        final double kDistanceUserToCentreInteretEvaluation = 0.0002;
        final double cDistanceUserToCentreInteretEvaluation = 1.0;
        final double cDistanceInCentreInteretEvaluation = 3.0;
        final double cTimeFrameEvaluation = 1.2;
        final double cCentreInteretEvaluation = 0.8;
        final double cDecreaseSameTimeFrameDay = 0.8;
        final double cDecreaseSameCentreInteret = 0.7;
        final Date prevMondayMidnight = prevMondayMidnight();
        final Date nextMonday = nextMonday();

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
                    timeFrameCentreInteret.evaluation = (cTimeFrameEvaluation * timeFrame.getEvaluation() + cCentreInteretEvaluation * centreInteretEvaluation) / 2.0;

                    // Insert TimeFrameCentreInteret in List, sorted by Evaluation
                    addTimeFrameCentreInteret(timeFrameCentreInteret);
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
            List<Objectif> objectifsEnregistres = new ArrayList<>();

            for(Objectif objectif : user.getObjectifs()) {
                Objectif objectifProgramme = new Objectif(objectif.getObjectif(), objectif.getSport());
                objectifsEnregistres.add(objectifProgramme);
            }

            programme.setObjectifs(objectifsEnregistres);
            programme.setDateDebut(prevMondayMidnight);
        }
        // If Active Program, some Activities already realized
        else
        {
            // Add already realized Activities to Activities List and remove non realized Activities
            for (Activity activity : programme.getActivites())
            {
                if (activity.isEstRealisee())
                {
                    activities.add(activity);
                    objectifsSportsDistance.get((int) (long) activity.getSport().getId() - 1).remove(activity.getDistancePrevue());
                }
                else
                {
                    activityRepository.delete(activity);
                }
            }
            programme.setActivites(activities);
        }
        programme = programmeService.saveProgram(programme);

        // While Week Objective is not Completed
        int sportIndex = 0;
        index = 0;
        while (sportIndex < objectifsSportsDistance.size() && index < timeFrameCentreInterets.size())
        {
            while (objectifsSportsDistance.get(sportIndex).size() > 0 && index < timeFrameCentreInterets.size())
            {
                timeFrameCentreInteret = timeFrameCentreInterets.get(index);
                distanceUserToCentreInteret = sqrt(pow((userLocation.getX() - timeFrameCentreInteret.centreInteret.getPoint().getX()) * 111000, 2) +
                    pow((userLocation.getY() - timeFrameCentreInteret.centreInteret.getPoint().getY()) * 111000 * cos(userLocation.getX() - timeFrameCentreInteret.centreInteret.getPoint().getX()), 2));
                distanceInCentreInteret = 2*distanceUserToCentreInteret + timeFrameCentreInteret.centreInteret.getLongueurCourse()*1000;
                distanceInCentreInteretEvaluation = (1 - abs(distanceInCentreInteret - objectifsSportsDistance.get(sportIndex).get(0))/objectifsSportsDistance.get(sportIndex).get(0));

                // For the 3 best TimeFrameCentreInteret
                for (int i = 1; i < 15; i = i + 1)
                {
                    timeFrameCentreInteret1 = timeFrameCentreInterets.get(index + i);
                    distanceUserToCentreInteret1 = sqrt(pow((userLocation.getX() - timeFrameCentreInteret1.centreInteret.getPoint().getX()) * 111000, 2) +
                        pow((userLocation.getY() - timeFrameCentreInteret1.centreInteret.getPoint().getY()) * 111000 * cos(userLocation.getX() - timeFrameCentreInteret1.centreInteret.getPoint().getX()), 2));
                    distanceInCentreInteret1 = 2*distanceUserToCentreInteret1 + timeFrameCentreInteret1.centreInteret.getLongueurCourse()*1000;
                    distanceInCentreInteretEvaluation1 = (1 - abs(distanceInCentreInteret1 - objectifsSportsDistance.get(sportIndex).get(0))/objectifsSportsDistance.get(sportIndex).get(0));

                    // If Evaluation when adding Distance in CentreInteret is better
                    if ((timeFrameCentreInteret1.evaluation + cDistanceInCentreInteretEvaluation * distanceInCentreInteretEvaluation1) >
                        (timeFrameCentreInteret.evaluation + cDistanceInCentreInteretEvaluation * distanceInCentreInteretEvaluation))
                    {
                        // New best TimeFrameCentreInteret
                        timeFrameCentreInteret = timeFrameCentreInteret1;
                        distanceInCentreInteret = distanceInCentreInteret1;
                        distanceInCentreInteretEvaluation = distanceInCentreInteretEvaluation1;
                    }
                }

                // Create Activity
                ActivityDTO activity = new ActivityDTO();
                activity.setDate(timeFrameCentreInteret.timeFrame.getDate());
                activity.setTimeFrameId(timeFrameCentreInteret.timeFrame.getId());
                activity.setCentreinteretId(timeFrameCentreInteret.centreInteret.getId());
                activity.setSportName(sports.get(sportIndex).getNom());

                activity.setDistance(objectifsSportsDistance.get(sportIndex).get(0));
                objectifsSportsDistance.get(sportIndex).remove(0);

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
                        timeFrameCentreInteret1.evaluation = timeFrameCentreInteret1.evaluation * cDecreaseSameTimeFrameDay;
                        timeFrameCentreInteretToUpdate = true;
                    }

                    // Decrease Evaluation if same CentreInteret
                    if (timeFrameCentreInteret1.centreInteret == timeFrameCentreInteret.centreInteret)
                    {
                        timeFrameCentreInteret1.evaluation = timeFrameCentreInteret1.evaluation * cDecreaseSameCentreInteret;
                        timeFrameCentreInteretToUpdate = true;
                    }

                    // If TimeFrameCentreInteret Evaluation updated
                    if (timeFrameCentreInteretToUpdate)
                    {
                        // Add TimeFrameCentreInteret to update to temp List
                        tempTimeFrameCentreInterets.add(timeFrameCentreInteret1);
                        // Remove TimeFrameCentreInteret to update from List
                        timeFrameCentreInterets.remove(timeFrameCentreInteret1);
                    }

                    index1 = index1 + 1;
                }

                // Add all updated TimeFrameCentreInteret to List sorted by Evaluation
                for (TimeFrameCentreInteret timeFrameCentreInteret2 : tempTimeFrameCentreInterets)
                {
                    addTimeFrameCentreInteret(timeFrameCentreInteret2);
                }

                // Add Activity to Activities List
                savedActivity = activityService.addActivity(activity, false);
                activities.add(savedActivity);

                index = index + 1;
            }

            sportIndex = sportIndex + 1;
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
