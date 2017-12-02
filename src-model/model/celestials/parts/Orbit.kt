package model.celestials.parts

import engine.math.AngularVelocity
import engine.math.Degree
import engine.math.Radius
import engine.math.Velocity
import model.Coords
import model.celestials.CelestialBody

class Orbit(
        var orbiting: CelestialBody?,
        var radius: Radius,
        velocity: Velocity,
        var position: Degree
) {
    var coords: Coords = Coords()
    var angularVelocity: AngularVelocity = AngularVelocity(radius, velocity)

    fun tick() {
        rotate()
    }

    private fun rotate() {
        position = (position + angularVelocity).normalize()
    }
}