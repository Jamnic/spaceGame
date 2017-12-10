package game.engine.math

import game.architecture.Constants

class AngularVelocity(
        private val value: Double,
        private val unit: ScaleUnit = ScaleUnit.THOUSANDTH_KM
) {

    constructor(
            radius: Radius,
            velocity: Velocity
    ) : this(Degree(velocity.value() * Constants.FULL_CIRCLE) / radius.toCircuit())

    fun velocity(toUnit: ScaleUnit): Double {
        return value * unit.convertTo(toUnit)
    }
}