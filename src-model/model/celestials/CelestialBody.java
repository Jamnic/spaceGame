package model.celestials;

import model.celestials.parts.Orbit;
import model.celestials.parts.Sphere;
import model.interfaces.Drawable;

import com.fasterxml.jackson.annotation.JsonCreator;

import game.architecture.Entity;

/**
 * Entity containing all information about given celestial body.
 * 
 * TODO distances between bodies and ships mapping.
 */
public class CelestialBody extends Entity implements Drawable {

	private String name;
	private Orbit orbit;
	private Sphere sphere;

	private CelestialBodyType type;

	/* ========== PUBLIC ========== */
	@JsonCreator
	public CelestialBody(String name, Orbit orbit, Sphere sphere, CelestialBodyType type) {
		super(CelestialBody.class);

		this.name = name;
		this.orbit = orbit;
		this.sphere = sphere;
		this.type = type;
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

	public CelestialBodyType getType() {
		return type;
	}

	public void setType(CelestialBodyType type) {
		this.type = type;
	}
}