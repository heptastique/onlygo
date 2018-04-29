package smart.DTO;

import javax.validation.constraints.NotNull;
import java.util.Date;

public class ProgrammeDTO {

    @NotNull
    private Date dateDebut;

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut) {
        this.dateDebut = dateDebut;
    }
}
