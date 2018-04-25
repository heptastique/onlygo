package smart.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "ACTIVITY")
public class Activity {

    @Id
    @Column(name = "ACTIVITY_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="SPORT_ID", nullable=false)
    private Sport sport;

    @Column(name = "DISTANCE")
    private float distance;

    @ManyToOne
    @JoinColumn(name="PROGRAMME_ID")
    Programme programme;

    @Column(name = "DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    Date date;

    //TODO
    //Parcours;

    public Activity() {
    }

    public Activity(Long id, Sport sport, float distance, Programme programme, Date date) {
        this.id = id;
        this.sport = sport;
        this.distance = distance;
        this.programme = programme;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}