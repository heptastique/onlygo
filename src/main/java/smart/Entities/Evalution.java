package smart.Entities;


import javax.persistence.*;

@Entity
@Table(name = "EVALUATION")
public class Evalution {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "NOTE")
    private double note;

    @ManyToOne
    @JoinColumn(name="ZONE_ID", nullable=false)
    private Zone zone;

    @ManyToOne
    @JoinColumn(name="TIMEFRAME_ID", nullable=false)
    private TimeFrame timeFrame;

    @OneToOne(fetch = FetchType.LAZY)
    private DonneeAthmospherique donneeAthmospherique;

    //@TODO Donnee Meteo
    // @OneToOne(fetch = FetchType.LAZY)
    // private DonneeMeteo donneeMeteo

}
