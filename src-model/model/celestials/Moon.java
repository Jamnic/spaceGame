package model.celestials;


import model.celestials.parts.Orbit;
import model.celestials.parts.Sphere;

public class Moon extends CelestialBody {

    public Moon(String name, Orbit orbit, Sphere sphere) {
        super(name, orbit, sphere, CelestialBodyType.MOON);
    }

}
