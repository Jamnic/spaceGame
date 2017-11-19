package model.celestials.types;

import model.celestials.CelestialBody;
import model.celestials.Orbit;
import model.celestials.Sphere;


public class Star extends CelestialBody {

	public Star(String name, Orbit orbit, Sphere sphere) {
		super(name, orbit, sphere);
	}
}