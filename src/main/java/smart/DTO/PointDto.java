package smart.DTO;

import javax.validation.constraints.NotNull;

public class PointDto {
    @NotNull
    private double x;
    @NotNull
    private double y;

    public PointDto() {
    }

    public double getX() { return x; }

    public void setX(double x) { this.x = x; }

    public double getY() { return y; }

    public void setY(double y) { this.y = y; }
}
