package smart.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "timeframe")
public class TimeFrame {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "HEUREDEBUT", length = 50)
    @NotNull
    private int heureDebut;

    @Column(name = "HEUREFIN", length = 50)
    @NotNull
    private int heureFin;

    @Column(name = "NOMJOUR", length = 50)
    @NotNull
    @Enumerated(EnumType.STRING)
    private Jour jour;

    @Column(name = "EVALUATION")
    private double evaluation;

    @Column(name="DATE")
    @Temporal(TemporalType.DATE)
    Date date;

    @OneToOne(fetch = FetchType.LAZY)
    private DonneeAthmospherique donneeAthmospherique;

    @OneToOne(fetch = FetchType.LAZY)
    private WeatherData weatherData;

    public void generateDate (Date date){
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR, this.heureDebut);
        this.date = calendar.getTime();
    }

    public Jour getJour() {
        return jour;
    }

    public void setJour(Jour jour) {
        this.jour = jour;
    }

    public double getEvaluation() {
        return evaluation;
    }

    public void setEvaluation(double evaluation) {
        this.evaluation = evaluation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public void setHeureDebut(int heureDebut) {
        this.heureDebut = heureDebut;
    }

    public int getHeureFin() {
        return heureFin;
    }

    public void setHeureFin(int heureFin) {
        this.heureFin = heureFin;
    }

    public Jour getNomJour() {
        return jour;
    }

    public void setNomJour(Jour jour) {
        this.jour = jour;
    }

    public DonneeAthmospherique getDonneeAthmospherique() {
        return donneeAthmospherique;
    }

    public void setDonneeAthmospherique(DonneeAthmospherique donneeAthmospherique) {
        this.donneeAthmospherique = donneeAthmospherique;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}

