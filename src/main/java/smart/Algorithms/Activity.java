package smart.Algorithms;

import smart.Entities.CentreInteret;
import smart.Entities.Point;

import java.util.List;

public class Activity {

    public static List<Point> findActivityItinerary (Point userLocalisation, CentreInteret centreInteret)
    {
        List<Point> listCIPoints = centreInteret.getAllPoint(); // notImplementedYet
        double min = Double.MAX_VALUE;
        int position;
        int i = 0;
        for ( Point point : listCIPoints){
            i++;
            double distance = distanceBetween( userLocalisation, point);
            if ( distance < min){
                min = distance;
                position = i;
            }
        }
    }

    public static double distanceBetween ( Point point1, Point point2){
        return (  Math.pow(point1.getX()-point2.getX(), 2) +  Math.pow(point1.getY()-point2.getY(), 2) );
    }
}
