package model.system

import model.celestials.CelestialBody
import model.ship.Ship
import model.system.parts.SkyBox

import com.fasterxml.jackson.annotation.JsonIgnore

import game.architecture.Entity

/**
 * Entity that contains whole information about given [StarSystem].
 */
class StarSystem(
        var skyBox: SkyBox?,
        var ships: List<Ship>?,
        var celestialBodies: List<CelestialBody>?
) : Entity(StarSystem::class.java) {

    @JsonIgnore
    var isVisible: Boolean = false
}