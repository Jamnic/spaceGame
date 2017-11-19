package model.celestials;


import model.celestials.parts.Orbit;
import model.celestials.parts.Sphere;

public class Planet extends CelestialBody {
	
	public Planet(String name, Orbit orbit, Sphere sphere) {
		super(name, orbit, sphere, CelestialBodyType.PLANET);
	}

}
