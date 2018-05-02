package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@JSONNORECURSION_ID")
@Table(name = "objectif")
public class Objectif {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    private double objectif;

    @OneToOne
    private Sport sport;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getObjectif() {
        return objectif;
    }

    public void setObjectif(double objectif) {
        this.objectif = objectif;
    }

    public Sport getSport() {
        return sport;
    }

    public void setSport(Sport sport) {
        this.sport = sport;
    }

    public Objectif() {
    }

    public Objectif(double objectif, Sport sport) {
        this.objectif = objectif;
        this.sport = sport;
    }
}
