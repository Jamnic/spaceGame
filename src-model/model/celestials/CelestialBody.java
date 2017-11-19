package model.celestials;

import game.architecture.Entity;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Entity containing all information about given celestial body.
 * 
 * TODO distances between bodies and ships mapping.
 */
public class CelestialBody extends Entity {

    private String name;
    private Orbit orbit;
    private Sphere sphere;

    /* ========== PUBLIC ========== */
    @JsonCreator
    public CelestialBody(String name, Orbit orbit, Sphere sphere) {
        super(CelestialBody.class);

        this.name = name;
        this.orbit = orbit;
        this.sphere = sphere;
    }

    /* ========== PRIVATE ========== */
    /* ========== PROPERTIES ========== */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Orbit getOrbit() {
        return orbit;
    }

    public void setOrbit(Orbit orbit) {
        this.orbit = orbit;
    }

    public Sphere getSphere() {
        return sphere;
    }

    public void setSphere(Sphere sphere) {
        this.sphere = sphere;
    }
}