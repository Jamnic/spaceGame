package model.celestials;


import model.celestials.parts.Orbit;
import model.celestials.parts.Sphere;

public class Star extends CelestialBody {

	public Star(String name, Orbit orbit, Sphere sphere) {
		super(name, orbit, sphere, CelestialBodyType.STAR);
	}
}