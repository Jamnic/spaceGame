package model.celestials.parts;

import model.celestials.CelestialBody;
import model.type.DrawableResolution;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jogamp.opengl.util.texture.Texture;

import engine.calculators.PhysicsCalculator;

/**
 * Object which stores the sphere parameters for {@link CelestialBody} entity.
 * 
 * TODO It should be upgraded by:<br>
 * - mass - json ignore, calculated from volume and density<br>
 * - density<br>
 */
public class Sphere {

    private Ring ring;
    private Clouds clouds;

    private String textureFile;

    private double radius;
    private double velocity;
    private double rotation;
    private double inclination;

    /* ========== PUBLIC ========== */
    @JsonCreator
    public Sphere(String textureFile, Clouds clouds, Ring ring, double radius, double velocity, double inclination) {

        this.ring = ring;
        this.clouds = clouds;

        this.textureFile = textureFile;

        this.radius = radius;
        this.velocity = velocity;
        this.inclination = inclination;

        this.rotation = 0;
        this.angularVelocity = PhysicsCalculator.angularVelocity(radius, velocity);
        this.resolution = DrawableResolution.VERY_FAR;
    }

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private Texture texture;
    @JsonIgnore
    private double angularVelocity;
    @JsonIgnore
    private DrawableResolution resolution;
    @JsonIgnore
    private double headingRotation;

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

    public String getTextureFile() {
        return textureFile;
    }

    public void setTextureFile(String textureFile) {
        this.textureFile = textureFile;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
    }

    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
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

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }

    public DrawableResolution getResolution() {
        return resolution;
    }

    public void setResolution(DrawableResolution resolution) {
        this.resolution = resolution;
    }

    public double getHeadingRotation() {
        return headingRotation;
    }

    public void setHeadingRotation(double headingRotation) {
        this.headingRotation = headingRotation;
    }
}