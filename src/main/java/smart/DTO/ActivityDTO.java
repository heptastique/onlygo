package smart.DTO;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.NotEmpty;
import java.util.Date;

public class ActivityDTO {

    @NotNull
    @NotEmpty
    private String sportName;

    @NotNull
    private float distance;

    private Long programmeId;

    @NotNull
    // Parse date in JSON not working
    // @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="MMM dd, yyyy hh:mm:ss a", timezone = "CEST")
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

    public String getSportName() {
        return sportName;
    }

    public void setSportName(String sportName) {
        this.sportName = sportName;
    }

    public Long getProgrammeId() {
        return programmeId;
    }

    public void setProgrammeId(Long programmeId) {
        this.programmeId = programmeId;
    }
}
