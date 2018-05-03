package smart.Entities;


import com.fasterxml.jackson.annotation.JsonIgnore;

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

    @Column(name = "LONGUEURCOURSE")
    private double longueurCourse = -1;

    @OneToOne(fetch = FetchType.EAGER)
    private Point point;

    @JsonIgnore
    @OneToMany
    private List<PointCentreInteret> listPoint;

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

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public List<PointCentreInteret> getListPoint() {
        return listPoint;
    }

    public void setListPoint(List<PointCentreInteret> listPoint) {
        this.listPoint = listPoint;
    }

    public double getLongueurCourse() {
        return longueurCourse;
    }

    public void setLongueurCourse(double longueurCourse) {
        this.longueurCourse = longueurCourse;
    }
}
