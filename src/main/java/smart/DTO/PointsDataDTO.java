package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;


@JsonIgnoreProperties(ignoreUnknown = true)
public class PointsDataDTO {
    List<Double> start_latlng;
    List<Double> end_latlng;
    private String points;
    String name;

    public List<Double> getStart_latlng() {
        return start_latlng;
    }

    public void setStart_latlng(List<Double> start_latlng) {
        this.start_latlng = start_latlng;
    }

    public List<Double> getEnd_latlng() {
        return end_latlng;
    }

    public void setEnd_latlng(List<Double> end_latlng) {
        this.end_latlng = end_latlng;
    }

    public String getPoints() {
        return points;
    }

    public void setPoints(String points) {
        this.points = points;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
