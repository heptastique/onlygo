package smart.Entities;


import javax.persistence.*;

@Entity
@Table(name = "evaluation")
public class Evaluation {

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


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public Zone getZone() {
        return zone;
    }

    public void setZone(Zone zone) {
        this.zone = zone;
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }

    public DonneeAthmospherique getDonneeAthmospherique() {
        return donneeAthmospherique;
    }

    public void setDonneeAthmospherique(DonneeAthmospherique donneeAthmospherique) {
        this.donneeAthmospherique = donneeAthmospherique;
    }
}
