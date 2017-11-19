package model.celestials.types;

import model.celestials.CelestialBody;
import model.celestials.Orbit;
import model.celestials.Sphere;

public class Planet extends CelestialBody {

    public Planet(String name, Orbit orbit, Sphere sphere) {
        super(name, orbit, sphere);
    }
}