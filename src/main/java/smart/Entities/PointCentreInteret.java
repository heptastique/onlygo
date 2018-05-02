package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "pointcentreinteret")
public class PointCentreInteret {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "X")
    private double x;

    @Column(name = "Y")
    private double y;

    @JsonIgnore
    @ManyToOne
    private CentreInteret centreInteret;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public CentreInteret getCentreInteret() {
        return centreInteret;
    }

    public void setCentreInteret(CentreInteret centreInteret) {
        this.centreInteret = centreInteret;
    }

    public PointCentreInteret(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public PointCentreInteret() {
    }

    @Override
    public String toString() {
        return "Point{" +
            "x=" + x +
            ", y=" + y +
            '}';
    }
}
