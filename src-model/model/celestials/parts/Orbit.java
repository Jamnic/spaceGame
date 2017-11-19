package model.celestials.parts;

import model.Coords;
import model.celestials.CelestialBody;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;

import engine.calculators.PhysicsCalculator;

/**
 * Object which stores the orbit parameters for {@link CelestialBody} entity.
 * 
 * TODO It should be upgraded by:<br>
 * - ellipse<br>
 * - inclination<br>
 */
public class Orbit {

    private double radius;
    private double velocity;
    private double position;
    private long orbittingBodyId;

    /* ========== PUBLIC ========== */
    @JsonCreator
    public Orbit(long orbittingBodyId, double radius, double velocity, double position) {
        this.radius = radius;
        this.velocity = velocity;
        this.position = position;
        this.orbittingBodyId = orbittingBodyId;

        this.coords = new Coords(0, 0, 0);
        this.angularVelocity = PhysicsCalculator.angularVelocity(radius, velocity);
    }

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private Coords coords;
    @JsonIgnore
    private Coords displayCoords;
    @JsonIgnore
    private double angularVelocity;

    /* ========== PROPERTIES ========== */
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

    public double getPosition() {
        return position;
    }

    public void setPosition(double positionInOrbit) {
        this.position = positionInOrbit;
    }

    public long getOrbittingBodyId() {
        return orbittingBodyId;
    }

    public void setOrbittingBodyId(long orbittingBodyId) {
        this.orbittingBodyId = orbittingBodyId;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    public Coords getDisplayCoords() {
        return displayCoords;
    }

    public void setDisplayCoords(Coords displayCoords) {
        this.displayCoords = displayCoords;
    }

    public double getAngularVelocity() {
        return angularVelocity;
    }

    public void setAngularVelocity(double angularVelocity) {
        this.angularVelocity = angularVelocity;
    }
}