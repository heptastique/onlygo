package smart.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

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



    /*@OneToOne(fetch = FetchType.EAGER)
    @JoinTable(
    name = "ATMOSPHERICDATA",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    - idZone
    - idDonneeAtmospherique
    - idDonneeMeteo
    */

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

}

