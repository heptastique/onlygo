package smart.Algorithms;


import smart.Entities.*;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.StrictMath.sqrt;

public class ActivityItinerary {

    public static List<PointCentreInteret> findActivityItinerary (User user, Activity activivity, CentreInteret centreInteret)
    {
        List<PointCentreInteret> itinerary = new ArrayList<>();
        Point userLocalisation = user.getLocation();
        PointCentreInteret userPoint = new PointCentreInteret();
        userPoint.setY(userLocalisation.getY());
        userPoint.setX(userLocalisation.getX());
        itinerary.add(userPoint);
        List<PointCentreInteret> listCIPoints = centreInteret.getListPoint();
        double min = Double.MAX_VALUE;
        int position = -1;
        int i = 0;
        double distance = 100;
        for ( PointCentreInteret point : listCIPoints){
            distance = distanceBetween( userPoint, point);
            if ( distance < min){
                min = distance;
                position = i;
            }
            i++;
        }
        double objectif = activivity.getDistancePrevue();
        System.out.println(min);
        System.out.println("objectif : " + objectif);
        objectif -= 2 * min;
        itinerary.add(listCIPoints.get(position));

        if ( objectif > 0){
            double [][] distancesInterPoints = new double[listCIPoints.size()][listCIPoints.size()];
            int [][] StatutPoints = new int[listCIPoints.size()][listCIPoints.size()];
            // generate distance
            i = 0;
            for ( PointCentreInteret point : listCIPoints ){
                int j =0;
                for ( PointCentreInteret point2 : listCIPoints) {
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
            List<PointCentreInteret> newItinerary = new ArrayList<>();
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
            newItinerary.add(userPoint);
            return newItinerary;
        }
        itinerary.add(listCIPoints.get(position));
        itinerary.add(userPoint);
        return itinerary;
    }

    public static double distanceBetween (PointCentreInteret point1, PointCentreInteret point2){
        /*double deltaY = point1.getX()-point2.getX();
        double deltaX = (point1.getY()-point2.getY())*Math.cos( (deltaY) /2);
        double distance= Math.sqrt(deltaX*deltaX+deltaY*deltaY);
        return (distance);*/
        return sqrt(pow((point1.getX() - point2.getX()) * 111, 2) +
            pow((point1.getY() - point2.getY()) * 111 * cos(point1.getX() - point2.getX()), 2));
    }
}
