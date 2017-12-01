package model.celestials

import model.celestials.parts.Clouds
import model.celestials.parts.Orbit
import model.celestials.parts.Ring
import model.celestials.parts.Sphere

class Planet(
        name: String,
        orbit: Orbit,
        clouds: Clouds,
        ring: Ring,
        sphere: Sphere
) : CelestialBody(name, orbit, sphere, clouds, ring)
