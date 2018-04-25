package smart.DTO;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ActivityDTO {

    @NotNull
    private Long sportId;

    @NotNull
    private float distance;

    private Long programmeId;

    @NotNull
    private Date date;

    //TODO
    //Parcours;

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

    public Long getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }
}
