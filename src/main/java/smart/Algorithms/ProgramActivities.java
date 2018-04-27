package smart.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import smart.Entities.CentreInteret;
import smart.Entities.Point;
import smart.Entities.TimeFrame;
import smart.Entities.User;
import smart.Services.TimeFrameService;

import java.sql.Time;

import static java.lang.Math.exp;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class ProgramActivities
{
    @Autowired
    private TimeFrameService timeFrameService;

    final double kDistanceUserToCentreInteretEvaluation = 0.5;

    public void calculate(User user)
    {
        // @TODO For each Sport
        // @TODO Try to split

        double objectifHebdo = user.getObjectifHebdo();
        Point userLocation = user.getLocation();
        Point centreInteretCentre;
        double note;
        double distanceUserToCentreInteret;
        double distanceUserToCentreInteretEvaluation;

        // For each TimeFrame
        Iterable <TimeFrame> timeFrames = timeFrameService.getTimeFrameAll();
        for (TimeFrame timeFrame : timeFrames)
        {
            // For each CentreInteret
            Iterable <CentreInteret> centreInterets = centreInteretService.getCentreInteretAll();
            for (CentreInteret centreInteret : centreInterets)
            {
                centreInteretCentre = centreInteret.getCercle().getCentre();

                distanceUserToCentreInteret = sqrt(pow(userLocation.getX() - centreInteretCentre.getX(), 2) +
                    pow(userLocation.getY() - centreInteretCentre.getY(), 2));

                distanceUserToCentreInteretEvaluation = exp(-kDistanceUserToCentreInteretEvaluation * distanceUserToCentreInteret);

                note = timeFrame.getEvaluation() * distanceUserToCentreInteretEvaluation;
            }
        }

        return;
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
