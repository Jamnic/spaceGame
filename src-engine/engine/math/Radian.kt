package engine.math

data class Radian(
        private val value: Float
) {
    constructor(doubleValue: Double) : this(doubleValue.toFloat())

    companion object {
        val ZERO = Degree(0F)
    }

    fun value(): Float {
        return value
    }

    // TODO plus operator
    operator fun plus(degree: Radian) : Radian {
        return Radian(degree.value + value)
    }

    fun negate() : Radian {
        return Radian(-value)
    }
}