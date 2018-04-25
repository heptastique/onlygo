package smart.Entities;


import javax.persistence.*;

@Entity
@Table(name = "Cercle")
public class Cercle {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "rayon")
    private double rayon;

    @OneToOne(fetch = FetchType.EAGER)
    private Point point;

    @ManyToOne
    @JoinColumn(name="ZONE_ID")
    Zone zone;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getRayon() {
        return rayon;
    }

    public void setRayon(double rayon) {
        this.rayon = rayon;
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }
}
