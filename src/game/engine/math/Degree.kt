package game.engine.math

import game.architecture.Constants

data class Degree(
        private val value: Double
) {

    constructor(
            intValue: Int
    ) : this(
            intValue.toDouble()
    )

    companion object {
        val ZERO = Degree(0.0)
    }

    fun value(): Double {
        return value
    }

    operator fun plus(degree: Degree): Degree {
        return Degree(degree.value + value)
    }

    operator fun plus(angularVelocity: AngularVelocity): Degree {
        return Degree(angularVelocity.velocity(ScaleUnit.THOUSANDTH_KM) + value)
    }

    // TODO normalize more than only one full circle
    fun normalize(): Degree {
        return when {
            value >= Constants.FULL_CIRCLE -> Degree(value - Constants.FULL_CIRCLE)
            value < 0 -> Degree(value + Constants.FULL_CIRCLE)
            else -> this
        }
    }

    fun toRadians(): Radian {
        return Radian(Math.toRadians(value))
    }

    operator fun times(float: Double): Degree {
        return Degree(value * float)
    }

    operator fun div(toCircuit: Circuit): Double {
        return value / toCircuit.value()
    }
}