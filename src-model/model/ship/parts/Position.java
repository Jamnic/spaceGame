package model.ship.parts;

import model.Coords;

public class Position {

    private Coords coords;
    private double rotationX;
    private double rotationY;

    /* ========== PUBLIC ========== */
    public Position(Coords coords, double rotationX, double rotationY) {
        super();

        this.coords = coords;
        this.rotationX = rotationX;
        this.rotationY = rotationY;
    }

    /* ========== PRIVATE ========== */
    /* ========== PROPERTIES ========== */
    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public double getRotationX() {
        return rotationX;
    }

    public void setRotationX(double rotationX) {
        this.rotationX = rotationX;
    }

    public double getRotationY() {
        return rotationY;
    }

    public void setRotationY(double rotationY) {
        this.rotationY = rotationY;
    }

}
