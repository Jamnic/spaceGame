package model;

import engine.utils.TextLogger;

/**
 * Represents mutable coordinates in 3D space.
 */
public final class Coords {

    private double x;
    private double y;
    private double z;

    /* ========== PUBLIC ========== */
    /** Used to create coordinates. */
    public Coords(double x, double y, double z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    /** Used to clone coordinates. */
    public Coords(Coords coords) {
        this.x = coords.x;
        this.y = coords.y;
        this.z = coords.z;
    }

    @Override
    public String toString() {
        return "(" + TextLogger.twoDecimal(x) + ", " + TextLogger.twoDecimal(y) + ", " + TextLogger.twoDecimal(z) + ")";
    }

    /* ========== PRIVATE ========== */
    /* ========== PROPERTIES ========== */
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

    public double getZ() {
        return z;
    }

    public void setZ(double z) {
        this.z = z;
    }
}