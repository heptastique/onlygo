package smart.Entities;


import javax.persistence.*;

@Entity
@Table(name = "cercle")
public class Cercle {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "rayon")
    private double rayon;

    @OneToOne(fetch = FetchType.EAGER)
    private Point centre;

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

    public Point getCentre() {
        return centre;
    }

    public void setCentre(Point centre) {
        this.centre = centre;
    }

    public boolean IsInsindeCercle (Point p) {
        double distanceToCentre = Math.pow(this.centre.getX()-p.getX(),2) + Math.pow(this.centre.getY()-p.getY(),2);
        if ( this.rayon - distanceToCentre > 0 ) {
            return true;
        }
        return false;
    }
}
