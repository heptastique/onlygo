package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@Entity
@Table(name = "activity")
public class Activity {

    @Id
    @Column(name = "ACTIVITY_ID")
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="SPORT_ID", nullable=false)
    private Sport sport;

    @Column(name = "DISTANCE_PREVUE")
    private float distancePrevue;

    @Column(name = "DISTANCE_REALISEE")
    private float distanceRealisee = 0f;

    @ManyToOne
    @JoinColumn(name="PROGRAMME_ID")
    @JsonIgnore
    private Programme programme;

    @Column(name = "DATE_PREVUE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date datePrevue;

    @Column(name = "DATE_REALISEE")
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateRealisee;

    @Column(name = "ESTREALISEE")
    private boolean estRealisee;

    @ManyToOne
    @JoinColumn(name="CENTREINTERET_ID")
    CentreInteret centreInteret;

    @ManyToOne
    @JoinColumn(name="TIMEFRAME_ID", nullable=false)
    TimeFrame timeFrame;

    public Activity() {
    }

    public Activity(Sport sport, float distancePrevue, Programme programme, Date datePrevue, CentreInteret centreInteret, boolean estRealisee) {
        this.sport = sport;
        this.distancePrevue = distancePrevue;
        this.programme = programme;
        this.datePrevue = datePrevue;
        this.centreInteret = centreInteret;
        this.estRealisee = estRealisee;
    }

    public Activity(Sport sport, float distancePrevue, float distanceRealisee, Programme programme, Date datePrevue, Date dateRealisee, boolean estRealisee, CentreInteret centreInteret, TimeFrame timeFrame) {
        this.sport = sport;
        this.distancePrevue = distancePrevue;
        this.distanceRealisee = distanceRealisee;
        this.programme = programme;
        this.datePrevue = datePrevue;
        this.dateRealisee = dateRealisee;
        this.estRealisee = estRealisee;
        this.centreInteret = centreInteret;
        this.timeFrame = timeFrame;
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

    public Programme getProgramme() {
        return programme;
    }

    public void setProgramme(Programme programme) {
        this.programme = programme;
    }

    public float getDistancePrevue() {
        return distancePrevue;
    }

    public void setDistancePrevue(float distancePrevue) {
        this.distancePrevue = distancePrevue;
    }

    public float getDistanceRealisee() {
        return distanceRealisee;
    }

    public void setDistanceRealisee(float distanceRealisee) {
        this.distanceRealisee = distanceRealisee;
    }

    public Date getDatePrevue() {
        return datePrevue;
    }

    public void setDatePrevue(Date datePrevue) {
        this.datePrevue = datePrevue;
    }

    public Date getDateRealisee() {
        return dateRealisee;
    }

    public void setDateRealisee(Date dateRealisee) {
        this.dateRealisee = dateRealisee;
    }

    public float getTauxCompletion()
    {
        return this.distanceRealisee/this.distancePrevue*100;
    }

    @JsonIgnore
    public String getDatePrevueString()
    {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormater.format(this.datePrevue);
    }

    @JsonIgnore
    public String getDateRealiseeString()
    {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormater.format(this.dateRealisee);
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
            ", distance=" + distancePrevue +
            ", date=" + this.getDatePrevueString() +
            ", estRealisee=" + estRealisee;
        if(programme!=null)
        {
            optionalPart = ", programme=" + programme.getId();
            return mandatoryPart + optionalPart + "}";
        }

        mandatoryPart += ", timeframe=" + timeFrame.getId();

        return mandatoryPart + "}";
    }

    public TimeFrame getTimeFrame() {
        return timeFrame;
    }

    public Long getTimeFrameId() {
        return timeFrame.getId();
    }

    public void setTimeFrame(TimeFrame timeFrame) {
        this.timeFrame = timeFrame;
    }
}
