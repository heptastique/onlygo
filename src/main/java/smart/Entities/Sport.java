package smart.Entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "sport")
public class Sport {

    @Id
    @Column(name = "SPORT_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "NOM")
    private String nom;

    @Column(name = "KMH")
    private float kmH = 0;

    @Column(name = "KCALH")
    private float kcalH = 0;

    public Sport() {}

    public Sport(@NotNull String nom, float kmH, float kcalH) {
        this.nom = nom;
        this.kmH = kmH;
        this.kcalH = kcalH;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public float getKmH() {
        return kmH;
    }

    public void setKmH(float kmH) {
        this.kmH = kmH;
    }

    public float getKcalH() {
        return kcalH;
    }

    public void setKcalH(float kcalH) {
        this.kcalH = kcalH;
    }

    public float getKcalKm() {
        return this.kcalH / this.kmH;
    }

    @Override
    public String toString() {
        return "Sport{" +
            "id=" + id +
            ", nom='" + nom + '\'' +
            ", kmH=" + kmH +
            ", kcalH=" + kcalH +
            ", kcalkm=" + getKcalKm() +
            '}';
    }
}
