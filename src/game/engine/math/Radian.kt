package game.engine.math

data class Radian(
        private val value: Double
) {

    companion object {
        val ZERO = Degree(0.0)
    }

    fun value(): Double {
        return value
    }

    // TODO plus operator
    operator fun plus(degree: Radian): Radian {
        return Radian(degree.value + value)
    }

    fun negate(): Radian {
        return Radian(-value)
    }
}