package smart.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "REALISATION")
public class Realisation {

    @Id
    @Column(name = "REALISATION_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "DISTANCE")
    private float distance;

    @Column(name = "DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @OneToOne
    @JoinColumn(name="ACTIVITY_ID")
    private Activity activite;

    public Realisation() {
        this.activite = null;
    }

    public Realisation(Activity activite) {
        this.activite = activite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public Activity getActivite() {
        return activite;
    }

    public void setActivite(Activity activite) {
        this.activite = activite;
    }
}
