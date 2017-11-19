package model.celestials.types;

import model.celestials.CelestialBody;
import model.celestials.Orbit;
import model.celestials.Sphere;

public class Asteroid extends CelestialBody {

    public Asteroid(String name, Orbit orbit, Sphere sphere) {
        super(name, orbit, sphere);
    }
}
