package engine.math

import model.Coords

open class Distance(
        protected val value: Float,
        protected val unit: ScaleUnit = ScaleUnit.KM
) {

    constructor(
            firstCoords: Coords,
            secondCoords: Coords,
            unit: ScaleUnit
    ) : this(firstCoords.distanceTo(secondCoords), unit)

    fun value(newUnit: ScaleUnit): Float {
        return if (unit == newUnit)
            value
        else
            value * unit.convertTo(newUnit)
    }

    fun value(): Float {
        return value
    }

    fun unit(): ScaleUnit {
        return unit
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