package model.celestials;


import model.celestials.parts.Orbit;
import model.celestials.parts.Sphere;

public class Asteroid extends CelestialBody {

    public Asteroid(String name, Orbit orbit, Sphere sphere) {
        super(name, orbit, sphere, CelestialBodyType.ASTEROID);
    }
}
