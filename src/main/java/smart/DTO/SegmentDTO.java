package smart.DTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;

public class SegmentDTO {
    private List<PointsDataDTO> segments;

    public List<PointsDataDTO> getSegments() {
        return segments;
    }

    public void setSegments(List<PointsDataDTO> segments) {
        this.segments = segments;
    }
}
