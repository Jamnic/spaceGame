package game.model.celestials.parts

import game.architecture.GameComponentContainer
import game.engine.math.AngularVelocity
import game.engine.math.Degree
import game.engine.math.Radius
import game.engine.math.Velocity
import game.engine.math.cyclometric.Cos
import game.engine.math.cyclometric.Sin
import game.model.Coords
import game.model.celestials.CelestialBody

class Orbit(
        private var orbiting: CelestialBody?,
        var radius: Radius,
        velocity: Velocity,
        var position: Degree,
        private var angularVelocity: AngularVelocity = AngularVelocity(radius, velocity),
        var coords: Coords = Coords()
) {

    fun tick() {
        calculateNewPosition()
    }

    private fun calculateNewPosition() {
        position = (position + angularVelocity).normalize()

        val orbitingBody = orbiting
        if (orbitingBody != null) {
            val radius = radius.scale(GameComponentContainer.shipManager.playerShip?.scale!!)
            val inclination = orbitingBody.inclination()

            coords.x = Sin(position) * radius
            coords.y = if (inclination.value() == 0.0) 0.0 else Sin(inclination) * Cos(position) * -radius.value()
            coords.z = Cos(position) * radius

            coords += orbitingBody.orbit.coords
        }
    }
}