package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "programme")
public class Programme {

    @Id
    @Column(name = "PROGRAMME_ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @ManyToOne
    @JoinColumn(name="USER_ID", referencedColumnName="ID", nullable=false)
    @JsonIgnore
    private User user;

    @Column(name = "OBJECTIF_DISTANCE")
    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.EAGER)
        @Fetch(value = FetchMode.SUBSELECT)
    private List<Objectif> objectifs;

    @Column(name = "DATE_DEBUT")
    @NotNull
    @Temporal(javax.persistence.TemporalType.DATE)
    private Date dateDebut;

    @OneToMany(cascade = CascadeType.REMOVE,
        fetch = FetchType.EAGER,
        mappedBy = "programme")
        @Fetch(value = FetchMode.SUBSELECT)
        private List<Activity> activites;

    public Programme() {
    }

    public Programme(User user, List<Activity> activites) {
        this.user = user;
        this.activites = activites;
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

    public List<Objectif> getObjectifs() {
        return objectifs;
    }

    public void setObjectifs(List<Objectif> objectifs) {
        this.objectifs = objectifs;
    }

    public List<Activity> getActivites() {
        return activites;
    }

    public void setActivites(List<Activity> activites) {
        this.activites = activites;
    }

    public Date getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(Date dateDebut)
    {
        this.dateDebut = dateDebut;
    }

    public float getTauxCompletion()
    {
        float totalCompletion = 0f;
        int nbActivitiesRealisees = 0;
        for(Activity activity : this.activites)
        {
            if(activity.isEstRealisee()) {
                totalCompletion = totalCompletion + activity.getTauxCompletion();
                nbActivitiesRealisees++;
            }
        }
        if(nbActivitiesRealisees!=0)
        {
            return totalCompletion/nbActivitiesRealisees;
        }
        return 0;
    }

    public void addObjectif(Objectif objectif)
    {
        this.objectifs.add(objectif);
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
            '}';
    }

    public void addActivity(Activity activity)
    {
        this.activites.add(activity);
    }
}
