package smart.DTO;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class RealisationDTO {

    @NotNull
    private Long sportId;

    @NotNull
    private float distance;

    private Long activityId;

    @NotNull
    private Date date;

    private Long ciId;

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getSportId() {
        return sportId;
    }

    public void setSportId(Long sportId) {
        this.sportId = sportId;
    }

    public Long getActivityId() {
        return activityId;
    }

    public void setActivityId(Long activityId) {
        this.activityId = activityId;
    }

    public Long getCiId() {
        return ciId;
    }

    public void setCiId(Long ciId) {
        this.ciId = ciId;
    }
}
