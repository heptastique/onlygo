package smart.Entities;

import org.hibernate.annotations.Formula;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "SPORT")
public class Sport {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "NOM")
    @NotNull
    private String nom;

    @Column(name = "KCALKM")
    private int kcalKm = 0;

    @Column(name = "KCALH")
    private int kcalH = 0;

    @Formula("KCALH / KCALKM")
    @Column(name = "KMH")
    private int kmH;

    public Sport(Long id, @NotNull String nom, int kcalKm, int kcalH) {
        this.id = id;
        this.nom = nom;
        this.kcalKm = kcalKm;
        this.kcalH = kcalH;
        this.updateKmH();
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

    public int getKcalKm() {
        return kcalKm;
    }

    public void setKcalKm(int kcalKm) {
        this.kcalKm = kcalKm;
        this.updateKmH();
    }

    public int getKcalH() {
        return kcalH;
    }

    public void setKcalH(int kcalH) {
        this.kcalH = kcalH;
        this.updateKmH();
    }

    public int getKmH() {
        return kmH;
    }

    public void updateKmH()
    {
        this.kmH = this.kcalH / this.kcalKm;
    }
}
