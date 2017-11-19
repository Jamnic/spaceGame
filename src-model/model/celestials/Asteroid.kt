package model.celestials

import model.celestials.parts.Orbit
import model.celestials.parts.Sphere

class Asteroid(
        name: String,
        orbit: Orbit,
        sphere: Sphere
) : CelestialBody(CelestialBodyType.ASTEROID, name, orbit, sphere)
