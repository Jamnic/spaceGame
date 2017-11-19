package model.celestials


import model.celestials.parts.Orbit
import model.celestials.parts.Sphere

class Wormhole(
        name: String,
        orbit: Orbit,
        sphere: Sphere,
        var systemFromId: Long,
        var systemToId: Long
) : CelestialBody(name, orbit, sphere) {

    override fun glows() = true
}