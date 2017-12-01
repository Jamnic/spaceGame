package engine.math

import model.Coords

open class Distance(
        private val value: Float,
        private val unit: Unit = Unit.KM
) {

    constructor(
            doubleValue: Double
    ) : this(doubleValue.toFloat())

    constructor(
            firstCoords: Coords,
            secondCoords: Coords,
            unit: Unit
    ) : this(firstCoords.distanceTo(secondCoords), unit)

    fun value(newUnit: Unit): Float {
        return if (unit == newUnit)
            value
        else
            value * unit.convertTo(newUnit)
    }

    fun value(): Float {
        return value
    }

    fun div(distance: Distance): Float {
        return distance.value(unit).div(this.value)
    }

    fun lessThan(distance: Distance): Boolean {
        return this.value < distance.value(unit)
    }

    override fun toString(): String {
        return "$value $unit"
    }
}