package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.google.maps.model.EncodedPolyline;
import com.google.maps.model.LatLng;
import javafx.scene.shape.Polyline;
import smart.Entities.PointCentreInteret;

import java.util.ArrayList;
import java.util.List;

public class PolylinePointsDTO {
    private List<PointCentreInteret> pointCentreInteretList;

    @JsonIgnore
    EncodedPolyline encodedPolyline;


    String polyline;

    public PolylinePointsDTO(List<PointCentreInteret> pointCentreInteretList) {
        this.pointCentreInteretList = pointCentreInteretList;
        List<LatLng> listPolyline = new ArrayList<>();
        for (PointCentreInteret point :pointCentreInteretList){
            listPolyline.add( new LatLng(point.getX(),point.getY()) );
        }
        this.encodedPolyline = new EncodedPolyline( listPolyline );
        this.polyline = encodedPolyline.getEncodedPath();
    }

    public List<PointCentreInteret> getPointCentreInteretList() {
        return pointCentreInteretList;
    }

    public void setPointCentreInteretList(List<PointCentreInteret> pointCentreInteretList) {
        this.pointCentreInteretList = pointCentreInteretList;
    }

    public EncodedPolyline getEncodedPolyline() {
        return encodedPolyline;
    }

    public void setEncodedPolyline(EncodedPolyline encodedPolyline) {
        this.encodedPolyline = encodedPolyline;
    }

    public String getPolyline() {
        return polyline;
    }

    public void setPolyline(String polyline) {
        this.polyline = polyline;
    }
}
