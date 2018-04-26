package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.validator.constraints.UniqueElements;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@JSONNORECURSION_PROGRAMMEID")
@Table(name = "programme")
public class Programme {

    @Id
    @Column(name = "PROGRAMME_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID", referencedColumnName="ID", nullable=false)
    private User user;

    @JoinColumn(name = "DATE_DEBUT", unique = true)
    @NotNull
    private Date dateDebut;

    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.EAGER,
        mappedBy = "programme")
        @Fetch(value = FetchMode.SUBSELECT)
        private List<Activity> activites;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinColumn(name="PROGRAMME_ID", referencedColumnName="PROGRAMME_ID")
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Realisation> realisations;

    public Programme() {
    }

    public Programme(User user, List<Activity> activites, List<Realisation> realisations) {
        this.user = user;
        this.activites = activites;
        this.realisations = realisations;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<Activity> getActivites() {
        return activites;
    }

    public void setActivites(List<Activity> activites) {
        this.activites = activites;
    }

    public List<Realisation> getRealisations() {
        return realisations;
    }

    public void setRealisations(List<Realisation> realisations) {
        this.realisations = realisations;
    }

    @JsonIgnore
    public String getDateDebutString()
    {
        SimpleDateFormat dateFormater = new SimpleDateFormat("yyyy-MM-dd");
        return dateFormater.format(this.dateDebut);
    }

    @Override
    public String toString() {
        return "Programme{" +
            "id=" + id +
            ", user=" + user +
            ", dateDebut=" + this.getDateDebutString() +
            ", activites=" + activites +
            ", realisations=" + realisations +
            '}';
    }
}
