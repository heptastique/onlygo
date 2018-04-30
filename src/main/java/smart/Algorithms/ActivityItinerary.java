package smart.Algorithms;

import smart.Entities.CentreInteret;
import smart.Entities.Point;
import smart.Entities.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityItinerary {

    public static List<Point> findActivityItinerary (User user, CentreInteret centreInteret)
    {
        List<Point> itinerary = new ArrayList<>();
        Point userLocalisation = user.getLocation();
        itinerary.add(userLocalisation);
        List<Point> listCIPoints = centreInteret.getListPoint(); // notImplementedYet
        double min = Double.MAX_VALUE;
        int position = -1;
        int i = 0;
        for ( Point point : listCIPoints){
            double distance = distanceBetween( userLocalisation, point);
            if ( distance < min){
                min = distance;
                position = i;
            }
            i++;
        }
        // calcul avec Api google distance entre user et point centre interet
        double distance = 100;
        double objectif = user.getObjectifHebdo();
        objectif -= 2 * distance;
        itinerary.add(listCIPoints.get(position));

        if ( objectif > 0){
            double [][] distancesInterPoints = new double[listCIPoints.size()][2];
            int [][] StatutPoints = new int[listCIPoints.size()][listCIPoints.size()];
            // generate distance
            i = 0;
            for ( Point point : listCIPoints ){
                int j =0;
                for ( Point point2 : listCIPoints) {
                    distancesInterPoints[i][j] = distanceBetween(point,point2);
                    j++;
                }
                i++;
            }
            for (int a =0; a < StatutPoints.length; a++){
                for ( int b =0; b< StatutPoints[0].length; b++){
                    StatutPoints[a][b] = 0;
                }
            }
            int pointActuel = position;
            while ( objectif > 0){
                boolean found = false;
                double distanceMin = Double.MAX_VALUE;
                int pointChoisi = -1;
                for ( int j = 0; j < distancesInterPoints[pointActuel].length; j++){
                    if ( StatutPoints[pointActuel][j] == 0){
                        if ( distancesInterPoints[pointActuel][j]< distanceMin ){
                            distanceMin = distancesInterPoints[pointActuel][j];
                            found = true;
                            pointChoisi = j;
                        }
                    }
                }
                if ( found ){
                    objectif -= distanceMin;
                    StatutPoints[pointActuel][pointChoisi] = 1;
                    StatutPoints[pointChoisi][pointActuel] = 1;
                    pointActuel = pointChoisi;
                    itinerary.add(listCIPoints.get(pointActuel));
                    if ( objectif - distancesInterPoints[pointActuel][position] < 0)
                    {
                        break;
                    }
                }
                else {
                    for (int a =0; a < StatutPoints.length; a++){
                        for ( int b =0; b< StatutPoints[0].length; b++){
                            StatutPoints[a][b] = 0;
                        }
                    }
                    found = false;
                    distanceMin = Double.MAX_VALUE;
                    pointChoisi = -1;
                    for ( int j = 0; j < distancesInterPoints[pointActuel].length; j++){
                        if ( StatutPoints[pointActuel][j] == 0){
                            if ( distancesInterPoints[pointActuel][j]< distanceMin ){
                                distanceMin = distancesInterPoints[pointActuel][j];
                                found = true;
                                pointChoisi = j;
                            }
                        }
                    }
                }
            }
        }
        itinerary.add(listCIPoints.get(position));
        itinerary.add(userLocalisation);
        // appel api google
        return itinerary;
    }

    public static double distanceBetween ( Point point1, Point point2){
        return (  Math.pow((point1.getX()-point2.getX()*110.574), 2) +  Math.pow(111.320*Math.cos(point1.getY()-point2.getY()), 2) );
    }
}
