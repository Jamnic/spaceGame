package game.model.celestials


import game.model.celestials.parts.Orbit
import game.model.celestials.parts.Sphere

class Wormhole(
        name: String,
        orbit: Orbit,
        sphere: Sphere,
        var systemFromId: Long,
        var systemToId: Long
) : CelestialBody(name, orbit, sphere) {

    override fun glows() = true
}