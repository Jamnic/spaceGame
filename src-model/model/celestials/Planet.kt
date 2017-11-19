package model.celestials

import model.celestials.parts.Orbit
import model.celestials.parts.Sphere

class Planet(
        name: String,
        orbit: Orbit,
        sphere: Sphere
) : CelestialBody(name, orbit, sphere, CelestialBodyType.PLANET)
