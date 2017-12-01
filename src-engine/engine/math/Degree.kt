package engine.math

import game.architecture.Constants

data class Degree(
        private val value: Float
) {
    companion object {
        val ZERO = Degree(0F)
    }

    fun value(): Float {
        return value
    }

    // TODO plus operator
    operator fun plus(degree: Degree): Degree {
        return Degree(degree.value + value)
    }

    // TODO normalize more than only one full circle
    fun normalize(): Degree {
        return when {
            value >= Constants.FULL_CIRCLE -> Degree(value - Constants.FULL_CIRCLE)
            value < 0 -> Degree(value + Constants.FULL_CIRCLE)
            else -> this
        }
    }

    fun toRadians() : Radian {
        return Radian(Math.toRadians(value.toDouble()))
    }

    operator fun times(float: Float): Degree {
        return Degree(value * float)
    }
}