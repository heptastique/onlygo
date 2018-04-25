package smart.Entities;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "donneeathmospherique")
public class DonneeAthmospherique {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    private Double indice;

    @Temporal(TemporalType.DATE)
    private Date date;

    public DonneeAthmospherique() {
    }

    public DonneeAthmospherique(Double indice, Date date) {
        this.indice = indice;
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getIndice() {
        return indice;
    }

    public void setIndice(Double indice) {
        this.indice = indice;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

}
