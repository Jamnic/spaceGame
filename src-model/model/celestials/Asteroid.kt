package model.celestials

import model.celestials.parts.Orbit
import model.celestials.parts.Sphere

class Asteroid(
        name: String,
        orbit: Orbit,
        sphere: Sphere
) : CelestialBody(name, orbit, sphere, CelestialBodyType.ASTEROID)
