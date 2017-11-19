package model.celestials;

import model.interfaces.Drawable;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Object which stores the ring parameters for {@link Sphere} part of {@link CelestialBody} entity.
 */
public class Ring extends Drawable {

    private double innerRadius;
    private double outerRadius;

    /* ========== PUBLIC ========== */
    @JsonCreator
    public Ring(String textureFile, double innerRadius, double outerRadius) {
        super(Ring.class, textureFile);

        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;
    }

    /* ========== PRIVATE ========== */
    /* ========== PROPERTIES ========== */
    public double getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(double innerRadius) {
        this.innerRadius = innerRadius;
    }

    public double getOuterRadius() {
        return outerRadius;
    }

    public void setOuterRadius(double outerRadius) {
        this.outerRadius = outerRadius;
    }
}