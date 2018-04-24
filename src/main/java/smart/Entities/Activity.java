package smart.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Entity
@Table(name = "ACTIVITY")
public class Activity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="SPORT_ID")
    @NotNull
    private Sport sport;

    @Column(name = "DISTANCE")
    private float distance;

    @ManyToOne(fetch = FetchType.LAZY)
    Programme programme;

    @Column(name = "DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;

    //TODO
    //Parcours;
}
