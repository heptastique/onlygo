package smart.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import smart.Algorithms.JsonToSQL;
import smart.DTO.PointsDataDTO;
import smart.DTO.SegmentDTO;
import smart.Entities.CentreInteret;
import smart.Entities.Point;
import smart.Entities.PointCentreInteret;
import smart.Repositories.PointCentreInteretRepository;
import smart.Repositories.PointRepository;

import java.util.ArrayList;
import java.util.List;

import static smart.Algorithms.ActivityItinerary.distanceBetween;

@Service
public class PointService {

    @Autowired
    private CentreInteretService centreInteretService;

    @Autowired
    private PointCentreInteretRepository pointCentreInteretRepository;

    public void generate(){
        Iterable<CentreInteret> listCentreInterets = centreInteretService.getCentreInteretAll();
        SegmentDTO response = JsonToSQL.parseToDTO("data.json");
        for ( PointsDataDTO pointsDataDTO :  response.getSegments()){
            List<PointCentreInteret> pointList = new ArrayList<>();
            pointList.add( new PointCentreInteret( pointsDataDTO.getStart_latlng().get(0), pointsDataDTO.getStart_latlng().get(1) ));
            List<PointCentreInteret> foundedPoints = JsonToSQL.findPoints(pointsDataDTO.getPoints());
            for ( PointCentreInteret point : foundedPoints){
                pointList.add(point);
            }
            pointList.add( new PointCentreInteret( pointsDataDTO.getEnd_latlng().get(0), pointsDataDTO.getEnd_latlng().get(1) ));
            for ( PointCentreInteret point : pointList){
                pointCentreInteretRepository.save(point);
            }
            for ( CentreInteret centreInteret : listCentreInterets){
                if ( pointsDataDTO.getName().compareTo(centreInteret.getName()) == 0){

                    List<PointCentreInteret> listAllPoint = centreInteret.getListPoint();
                    listAllPoint.addAll(pointList);
                    centreInteret.setListPoint(listAllPoint);

                    double distance = 0;
                    double [][] distancesInterPoints = new double[pointList.size()][pointList.size()];
                    int [] statutPoints = new int[pointList.size()];
                    int i = 0;
                    for ( PointCentreInteret point : pointList ){
                        int j = 0;
                        for ( PointCentreInteret point2 : pointList) {
                            distancesInterPoints[i][j] = distanceBetween(point,point2);
                            j++;
                        }
                        i++;
                    }
                    for (int a = 1; a < statutPoints.length; a++){
                        statutPoints[a] = 0;
                    }
                    statutPoints[0] = 1;
                    int position = 0;
                    boolean done = false;
                    while ( !done ){
                        double distanceMin = Double.MAX_VALUE;
                        int pointChoisi = -1;
                        boolean found = false;
                        for ( int j = 0; j < distancesInterPoints[position].length; j++){
                            if ( statutPoints[j] == 0){
                                if ( distancesInterPoints[position][j]< distanceMin ){
                                    distanceMin = distancesInterPoints[position][j];
                                    found = true;
                                    pointChoisi = j;
                                }
                            }
                        }
                        if ( found ){
                            distance+= distanceMin;
                            position = pointChoisi;
                            statutPoints[position]=1;
                        }else{
                            distance+=distancesInterPoints[0][position];
                        }
                        done = !found;
                    }

                    centreInteret.setLongueurCourse(distance);

                    for ( PointCentreInteret point : listAllPoint){
                        point.setCentreInteret(centreInteret);
                        pointCentreInteretRepository.save(point);
                    }
                    centreInteretService.persist(centreInteret);

                }
            }
        }
    }
}
