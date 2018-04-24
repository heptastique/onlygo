package smart.Entities;

import javax.persistence.*;

@Entity
@Table(name = "REALISATION")
public class Realisation {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @OneToOne
    private Activity activite;

    public Realisation() {
        this.activite = null;
    }

    public Realisation(Activity activite) {
        this.activite = activite;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Activity getActivite() {
        return activite;
    }

    public void setActivite(Activity activite) {
        this.activite = activite;
    }
}
