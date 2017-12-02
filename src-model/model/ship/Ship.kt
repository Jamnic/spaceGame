package model.ship

import engine.math.Distance
import engine.math.Radius
import game.architecture.GameComponentContainer
import model.StarSystemObject
import model.celestials.CelestialBody
import model.celestials.Wormhole
import model.ship.parts.Control
import model.ship.parts.Engine
import model.ship.parts.Position
import model.system.StarSystem
import model.type.DrawableResolution

open class Ship(
        var position: Position,
        var engine: Engine,
        var mesh: Mesh?,
        val size: Float
) : StarSystemObject<Ship>(Ship::class.java, position.coords) {

    var resolution: DrawableResolution = DrawableResolution.VERY_CLOSE
    var control: Control = Control()
    var starSystem: StarSystem? = null

    fun visible(): Boolean? {
        return resolution !== DrawableResolution.INVISIBLE
    }

    fun updateDistance(body: CelestialBody) {
        val distance = distance(body)
        body.updateResolution(resolution(distance, body.radius()))
        checkCollisions(body, distance)
    }

    private fun resolution(distance: Distance, radius: Radius): DrawableResolution {
        return DrawableResolution.determineResolution(distance.div(radius))
    }

    private fun checkCollisions(body: CelestialBody, distance: Distance) {
        if (body is Wormhole) {
            if (distance.lessThan(body.radius())) {
                starSystem = GameComponentContainer.starSystemRepository.getById(body.systemToId)
            }
        }
    }
}