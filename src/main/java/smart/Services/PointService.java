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
