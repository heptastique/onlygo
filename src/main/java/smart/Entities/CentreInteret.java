package smart.Entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "centreinteret")
public class CentreInteret {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToOne(fetch = FetchType.EAGER)
    private Cercle cercle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Cercle getCercle() {
        return cercle;
    }

    public void setCercle(Cercle cercle) {
        this.cercle = cercle;
    }
}
