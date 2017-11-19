package model.celestials;

import model.interfaces.Drawable;

/**
 * Object which stores the clouds parameters for {@link Sphere} part of {@link CelestialBody} entity.
 */
public class Clouds extends Drawable {

    private double radius;

    /* ========== PUBLIC ========== */
    public Clouds(String textureFile, double radius) {
        super(Clouds.class, textureFile);

        this.radius = radius;
    }

    /* ========== PRIVATE ========== */
    /* ========== PROPERTIES ========== */
    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }
}