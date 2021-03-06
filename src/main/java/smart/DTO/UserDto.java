package smart.DTO;

import smart.Validation.ValidEmail;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

public class UserDto {

    @NotNull
    @NotEmpty
    private String username;

    @NotNull
    @NotEmpty
    private String password;

    @NotNull
    @NotEmpty
    private String firstname;

    @NotNull
    @NotEmpty
    private String lastname;

    @ValidEmail
    @NotNull
    @NotEmpty
    private String email;

    @NotNull
    private double objectifHebdoCourse;

    @NotNull
    private double objectifHebdoCyclisme;

    @NotNull
    private double objectifHebdoMarche;

    @NotNull
    private double distanceMax;

    @NotNull
    private int nbSessions;

    @NotNull
    private PointDto location;

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

    public double getObjectifHebdoCourse() {
        return objectifHebdoCourse;
    }

    public void setObjectifHebdoCourse(double objectifHebdoCourse) {
        this.objectifHebdoCourse = objectifHebdoCourse;
    }

    public double getObjectifHebdoCyclisme() {
        return objectifHebdoCyclisme;
    }

    public void setObjectifHebdoCyclisme(double objectifHebdoCyclisme) {
        this.objectifHebdoCyclisme = objectifHebdoCyclisme;
    }

    public double getObjectifHebdoMarche() {
        return objectifHebdoMarche;
    }

    public void setObjectifHebdoMarche(double objectifHebdoMarche) {
        this.objectifHebdoMarche = objectifHebdoMarche;
    }

    public double getDistanceMax() {
        return distanceMax;
    }

    public void setDistanceMax(double distanceMax) {
        this.distanceMax = distanceMax;
    }

    public int getNbSessions()
    {
        return nbSessions;
    }

    public void setNbSessions(int nbSessions)
    {
        this.nbSessions = nbSessions;
    }

    public PointDto getLocation() {
        return location;
    }

    public void setLocation(PointDto location) {
        this.location = location;
    }

    public String toString(){
        return username + " " + password + " " + firstname + " " + lastname + " " + email;
    }
}
