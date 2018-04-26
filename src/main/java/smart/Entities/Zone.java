package smart.Entities;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "zone")
public class Zone {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "NAME")
    private String name;

    @OneToMany (fetch = FetchType.EAGER,
    mappedBy = "zone")
    private List<Cercle> listCercles;

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

    public List<Cercle> getListCercles() {
        return listCercles;
    }

    public void setListCercles(List<Cercle> listCercles) {
        this.listCercles = listCercles;
    }

    public boolean isInsideZone ( Point p) {
        for ( Cercle cercle : this.listCercles) {
            if ( cercle.IsInsindeCercle(p)) {
                return true;
            }
        }
        return false;
    }
}
