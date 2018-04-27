package smart.DTO;

import smart.Entities.TimeFrame;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;

public class ActivityDTO {

    @NotNull
    @NotEmpty
    private String sportName;

    @NotNull
    private float distance;

    private Long programmeId;

    @NotNull
    private Date date;

    private Long timeFrameId;

    private Long centreinteretId;

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

    public Long getTimeFrameId() {
        return timeFrameId;
    }

    public void setTimeFrameId(Long timeFrameId) {
        this.timeFrameId = timeFrameId;
    }

    public Long getCentreinteretId() {
        return centreinteretId;
    }

    public void setCentreinteretId(Long centreinteretId) {
        this.centreinteretId = centreinteretId;
    }
}
