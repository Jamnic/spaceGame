package model.system

import game.architecture.Entity
import model.celestials.CelestialBody
import model.ship.Ship
import model.system.parts.SkyBox

class StarSystem(
        var skyBox: SkyBox,
        var ships: List<Ship>,
        var celestialBodies: List<CelestialBody>
) : Entity(StarSystem::class.java)