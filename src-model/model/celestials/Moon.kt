package model.celestials

import model.celestials.parts.Orbit
import model.celestials.parts.Sphere

class Moon(
        name: String,
        orbit: Orbit,
        sphere: Sphere
) : CelestialBody(CelestialBodyType.MOON, name, orbit, sphere)
