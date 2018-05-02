package smart.Algorithms;

import smart.Entities.Activity;
import smart.Entities.CentreInteret;
import smart.Entities.Point;
import smart.Entities.User;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ActivityItinerary {

    public static List<Point> findActivityItinerary (User user, Activity activivity, CentreInteret centreInteret)
    {
        List<Point> itinerary = new ArrayList<>();
        Point userLocalisation = user.getLocation();
        itinerary.add(userLocalisation);
        List<Point> listCIPoints = centreInteret.getListPoint(); // notImplementedYet
        double min = Double.MAX_VALUE;
        int position = -1;
        int i = 0;
        double distance = 100;
        for ( Point point : listCIPoints){
            distance = distanceBetween( userLocalisation, point);
            if ( distance < min){
                min = distance;
                position = i;
            }
            i++;
        }
        // calcul avec Api google distance entre user et point centre interet
        double objectif = activivity.getDistance();
        System.out.println(min);
        objectif -= 2 * min;
        itinerary.add(listCIPoints.get(position));

        if ( objectif > 0){
            double [][] distancesInterPoints = new double[listCIPoints.size()][listCIPoints.size()];
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
                    if ( a == b){
                        StatutPoints[a][b] = 1;
                    }
                    else{
                        StatutPoints[a][b] = 0;
                    }
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
                            if ( a == b){
                                StatutPoints[a][b] = 1;
                            }
                            else{
                                StatutPoints[a][b] = 0;
                            }
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

        // less points for itinerary
        if ( itinerary.size() > 100){
            List<Point> newItinerary = new ArrayList<>();
            newItinerary.add(itinerary.get(0));
            newItinerary.add(itinerary.get(1));

            int actualSize = itinerary.size()-2;
            int modulo = actualSize/98;
            for ( int l = 2; l < itinerary.size(); l++){
                if ( l%modulo == 0){
                    newItinerary.add(itinerary.get(l));
                }
            }
            newItinerary.add(listCIPoints.get(position));
            newItinerary.add(userLocalisation);
            return newItinerary;
        }

        // appel api google
        return itinerary;
    }

    public static double distanceBetween ( Point point1, Point point2){
        System.out.println(point1.toString());
        System.out.println(point2.toString());
        double deltaY = point1.getX()-point2.getX(); // deltaX = (lngA-lngB).Math.cos((latA+latB)/2);
        double deltaX = (point1.getY()-point2.getY())*Math.cos( (point1.getX()+point2.getX()) /2);
        double distance= Math.sqrt(deltaX*deltaX+deltaY*deltaY);
        System.out.println("deltax : " + deltaX);
        System.out.println("deltay : " + deltaY);
        System.out.println("distance : " + distance);
        return (distance);
    }
}
