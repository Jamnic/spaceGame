package model.celestials;

import model.interfaces.Drawable;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Object which stores the sphere parameters for {@link CelestialBody} entity.
 * 
 * TODO It should be upgraded by:<br>
 * - mass - json ignore, calculated from volume and density<br>
 * - density<br>
 */
public class Sphere extends Drawable {

    private Ring ring;
    private Clouds clouds;

    private double radius;
    private double rotation;
    private double inclination;
    private double angularVelocity;

    private SphereType type;

    /* ========== PUBLIC ========== */
    @JsonCreator
    public Sphere(String textureFile, Clouds clouds, Ring ring, SphereType type, double radius, double velocity,
            double inclination) {
        super(Sphere.class, textureFile);

        this.ring = ring;
        this.clouds = clouds;

        this.type = type;

        this.radius = radius;
        this.angularVelocity = velocity;
        this.inclination = inclination;

        this.rotation = 0;
    }

    /* ========== PRIVATE ========== */
    /* ========== PROPERTIES ========== */
    public Ring getRing() {
        return ring;
    }

    public void setRing(Ring ring) {
        this.ring = ring;
    }

    public Clouds getClouds() {
        return clouds;
    }

    public void setClouds(Clouds clouds) {
        this.clouds = clouds;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getRotation() {
        return rotation;
    }

    public void setRotation(double rotation) {
        this.rotation = rotation;
    }

    public double getInclination() {
        return inclination;
    }

    public void setInclination(double inclination) {
        this.inclination = inclination;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public SphereType getType() {
        return type;
    }

    public void setType(SphereType type) {
        this.type = type;
    }
}