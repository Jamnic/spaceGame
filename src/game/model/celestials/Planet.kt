package game.model.celestials

import game.model.celestials.parts.Clouds
import game.model.celestials.parts.Orbit
import game.model.celestials.parts.Ring
import game.model.celestials.parts.Sphere

class Planet(
        name: String,
        orbit: Orbit,
        clouds: Clouds,
        ring: Ring,
        sphere: Sphere
) : CelestialBody(name, orbit, sphere, clouds, ring)
