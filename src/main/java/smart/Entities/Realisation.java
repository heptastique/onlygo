package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@JSONNORECURSION_REALISATIONID")
@Table(name = "realisation")
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

    public Realisation() {}

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

    @JsonIgnore
    public String getDateString()
    {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormater.format(this.date);
    }

    @Override
    public String toString() {
        return "Realisation{" +
            "id=" + id +
            ", distance=" + distance +
            ", date=" + this.getDateString() +
            ", activite=" + activite +
            '}';
    }
}
