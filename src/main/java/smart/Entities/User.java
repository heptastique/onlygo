package smart.Entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import org.assertj.core.internal.bytebuddy.implementation.bind.annotation.Default;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
@Entity
@JsonIdentityInfo(generator=ObjectIdGenerators.IntSequenceGenerator.class, property="@JSONNORECURSION_ID")
@Table(name = "users")
public class User {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @Column(name = "USERNAME", length = 50, unique = true)
    @NotNull
    @Size(min = 4, max = 50)
    private String username;

    @Column(name = "PASSWORD", length = 100)
    @NotNull
    @Size(min = 4, max = 100)
    @JsonIgnore
    private String password;

    @Column(name = "FIRSTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String firstname;

    @Column(name = "LASTNAME", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String lastname;

    @Column(name = "EMAIL", length = 50)
    @NotNull
    @Size(min = 4, max = 50)
    private String email;

    @Column(name = "OBJECTIFHEBDO")
    private double objectifHebdo;

    @Column(name = "DIFFICULTY")
    private int difficulte;

    @Column(name = "ENABLED")
    @NotNull
    @JsonIgnore
    private Boolean enabled;

    @Column(name = "DISTANCEMAX" )
    @org.hibernate.annotations.ColumnDefault("5")
    private double distanceMax ;
    @Column(name = "LASTPASSWORDRESETDATE")
    @Temporal(TemporalType.TIMESTAMP)
    @NotNull
    @JsonIgnore
    private Date lastPasswordResetDate;

    @ManyToMany(fetch = FetchType.EAGER)
    @JsonIgnore
    @JoinTable(
            name = "USER_AUTHORITY",
            joinColumns = {@JoinColumn(name = "USER_ID", referencedColumnName = "ID")},
            inverseJoinColumns = {@JoinColumn(name = "AUTHORITY_ID", referencedColumnName = "ID")})
    private List<Authority> authorities;

    @OneToMany(cascade = CascadeType.ALL,
        fetch = FetchType.LAZY,
        mappedBy = "user")
        private List<Programme> programmes;

    @OneToOne(fetch = FetchType.EAGER,
                cascade =CascadeType.MERGE)
    private Point location;

    public Point getLocation() { return location; }

    public void setLocation(Point location) { this.location = location; }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getDifficulte() {
        return difficulte;
    }

    public void setDifficulte(int difficulte) {
        this.difficulte = difficulte;
    }

    public double getObjectifHebdo(){ return objectifHebdo; }

    public void setObjectifHebdo(double objectifHebdo){ this.objectifHebdo=objectifHebdo; }

    public Boolean getEnabled() {
        return enabled;
    }

    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    public List<Authority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<Authority> authorities) {
        this.authorities = authorities;
    }

    public Date getLastPasswordResetDate() {
        return lastPasswordResetDate;
    }

    public void setLastPasswordResetDate(Date lastPasswordResetDate) {
        this.lastPasswordResetDate = lastPasswordResetDate;
    }

    public List<Programme> getProgrammes() {
        return programmes;
    }

    public void setProgrammes(List<Programme> programmes) {
        this.programmes = programmes;
    }

    public double getDistanceMax(){
        return distanceMax;
    }
    public void setDistanceMax(double distanceMax){
        this.distanceMax=distanceMax;
    }
}
