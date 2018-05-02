package smart.Algorithms;

import com.fasterxml.jackson.databind.ObjectMapper;
import smart.DTO.SegmentDTO;
import smart.Entities.PointCentreInteret;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class JsonToSQL {

    public static List<PointCentreInteret> findPoints (String points){
        List<PointCentreInteret> listPoints = new ArrayList<>();
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

            PointCentreInteret p = new PointCentreInteret( (((double) lat / 1E5)),
                (((double) lng / 1E5)));
            listPoints.add(p);
        }
        return listPoints;
    }

    public static SegmentDTO parseToDTO(String file){
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
