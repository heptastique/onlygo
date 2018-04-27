package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@JSONNORECURSION_ACTIVITYID")
@Table(name = "activity")
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
    private Programme programme;

    @Column(name = "DATE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date date;

    @Column(name = "ESTREALISEE")
    private boolean estRealisee;
    //TODO
    //Parcours;

    @ManyToOne
    @JoinColumn(name="CENTREINTERET_ID")
    CentreInteret centreInteret;

    public Activity() {
    }

    public Activity(Sport sport, float distance, Programme programme, Date date) {
        this.sport = sport;
        this.distance = distance;
        this.programme = programme;
        this.date = date;
        this.estRealisee = false;
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

    @JsonIgnore
    public String getDateString()
    {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormater.format(this.date);
    }

    public boolean isEstRealisee() {
        return estRealisee;
    }

    public void setEstRealisee(boolean estRealisee) {
        this.estRealisee = estRealisee;
    }

    public CentreInteret getCentreInteret() {
        return centreInteret;
    }

    public void setCentreInteret(CentreInteret centreInteret) {
        this.centreInteret = centreInteret;
    }

    @Override
    public String toString() {
        String mandatoryPart;
        String optionalPart;
        mandatoryPart = "Activity{" +
            "sport=" + sport.toString() +
            ", distance=" + distance +
            ", date=" + this.getDateString() +
            ", estRealisee=" + estRealisee;
        if(programme!=null)
        {
            optionalPart = ", programme=" + programme.getId();
            return mandatoryPart + optionalPart + "}";
        }
        return mandatoryPart + "}";
    }
}
