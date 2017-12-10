package game.model.celestials

import game.model.celestials.parts.Orbit
import game.model.celestials.parts.Sphere

class Star(
        name: String,
        orbit: Orbit,
        sphere: Sphere
) : CelestialBody(name, orbit, sphere) {
    override fun glows() = true
}