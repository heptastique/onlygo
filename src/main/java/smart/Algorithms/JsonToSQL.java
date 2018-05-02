package smart.Algorithms;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import smart.DTO.PointsDataDTO;
import smart.DTO.SegmentDTO;
import smart.Entities.CentreInteret;
import smart.Entities.Point;
import smart.Repositories.PointRepository;
import smart.Services.CentreInteretService;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class JsonToSQL {

    @Autowired
    private CentreInteretService centreInteretService;

    @Autowired
    private PointRepository pointRepository;

    public void generate(){
        Iterable<CentreInteret> listCentreInterets = centreInteretService.getCentreInteretAll();
        SegmentDTO response = parseToDTO("data.json");
        for ( PointsDataDTO pointsDataDTO :  response.getSegments()){
            List<Point> pointList = new ArrayList<>();
            pointList.add( new Point( pointsDataDTO.getStart_latlng().get(0), pointsDataDTO.getStart_latlng().get(1) ));
            List<Point> foundedPoints = findPoints(pointsDataDTO.getPoints());
            for ( Point point : foundedPoints){
                pointList.add(point);
            }
            pointList.add( new Point( pointsDataDTO.getEnd_latlng().get(0), pointsDataDTO.getEnd_latlng().get(1) ));
            for ( Point point : pointList){
                pointRepository.save(point);
            }
            for ( CentreInteret centreInteret : listCentreInterets){
                if ( pointsDataDTO.getName().compareTo(centreInteret.getName()) == 0){
                    List<Point> listAllPoint = centreInteret.getListPoint();
                    listAllPoint.addAll(pointList);
                    centreInteret.setListPoint(listAllPoint);
                    for ( Point point : listAllPoint){
                        point.setCentreInteret(centreInteret);
                        pointRepository.save(point);
                    }
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

            Point p = new Point( (((double) lat / 1E5)),
                (((double) lng / 1E5)));
            listPoints.add(p);
        }
        return listPoints;
    }

    private SegmentDTO parseToDTO(String file){
        byte[] jsonData = new byte[0];
        try {
            jsonData = Files.readAllBytes(Paths.get("./src/main/resources/" + file));
            //create ObjectMapper instance
            ObjectMapper objectMapper = new ObjectMapper();

            //convert json string to object
            SegmentDTO segmentDTO = objectMapper.readValue(jsonData, SegmentDTO.class);
            return segmentDTO;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
