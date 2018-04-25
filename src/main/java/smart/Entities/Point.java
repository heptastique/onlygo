package smart.Entities;

import javax.persistence.*;

@Entity
@Table(name = "point")
public class Point {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @Column(name = "X")
    private double x;

    @Column(name = "Y")
    private double y;

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
}
