package smart.Algorithms;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.client.RestTemplate;
import smart.DTO.PointsDataDTO;
import smart.DTO.SegmentDTO;
import smart.Entities.CentreInteret;
import smart.Entities.Point;
import smart.Services.CentreInteretService;

import java.util.ArrayList;
import java.util.List;

public class JsonToSQL {

    @Autowired
    private CentreInteretService centreInteretService;

    public void generate(){
        Iterable<CentreInteret> listCentreInterets = centreInteretService.getCentreInteretAll();
        RestTemplate restTemplate = new RestTemplate();
        SegmentDTO response = restTemplate.getForObject( "/segments/explore?bounds=",SegmentDTO.class);
        for ( PointsDataDTO pointsDataDTO :  response.getPointsDataDTO()){
            List<Point> pointList = new ArrayList<>();
            pointList.add( new Point( pointsDataDTO.getStart_latlng().get(0), pointsDataDTO.getStart_latlng().get(1) ));
            List<Point> foundedPoints = findPoints(pointsDataDTO.getPoints());
            for ( Point point : foundedPoints){
                pointList.add(point);
            }
            pointList.add( new Point( pointsDataDTO.getEnd_latlng().get(0), pointsDataDTO.getEnd_latlng().get(1) ));
            for ( CentreInteret centreInteret : listCentreInterets){
                if ( pointsDataDTO.getName().compareTo(centreInteret.getName()) == 0){
                    List<Point> listAllPoint = centreInteret.getListPoint();
                    listAllPoint.addAll(pointList);
                    centreInteret.setListPoint(listAllPoint);
                    centreInteretService.persist(centreInteret);
                }
            }


        }
        // to do gérer l'ajout
    }
    public List<Point> findPoints ( String points){
        List<Point> listPoints = new ArrayList<>();
        int index = 0, len = points.length();
        int lat = 0, lng = 0;

        while (index < len) {
            int b, shift = 0, result = 0;
            do {
                b = points.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlat = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lat += dlat;

            shift = 0;
            result = 0;
            do {
                b = points.charAt(index++) - 63;
                result |= (b & 0x1f) << shift;
                shift += 5;
            } while (b >= 0x20);
            int dlng = ((result & 1) != 0 ? ~(result >> 1) : (result >> 1));
            lng += dlng;

            Point p = new Point( (((double) lat / 1E5) * 1E6),
                (((double) lng / 1E5) * 1E6));
            listPoints.add(p);
        }
        return listPoints;
    }
}
