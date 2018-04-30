package smart.DTO;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class RealisationDTO {

    @NotNull
    private float distance;

    @NotNull
    private Date date;

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
}
