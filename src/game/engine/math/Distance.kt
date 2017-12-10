package game.engine.math

import game.model.Coords

open class Distance(
        protected val value: Double,
        protected val unit: ScaleUnit = ScaleUnit.KM
) {

    constructor(
            firstCoords: Coords,
            secondCoords: Coords,
            unit: ScaleUnit
    ) : this(firstCoords.distanceTo(secondCoords), unit)

    fun value(newUnit: ScaleUnit): Double {
        return if (unit == newUnit)
            value
        else
            value * unit.convertTo(newUnit)
    }

    fun scale(newUnit: ScaleUnit): Distance {
        return if (unit == newUnit)
            Distance(value)
        else
            Distance(value * unit.convertTo(newUnit), newUnit)
    }

    fun value(): Double {
        return value
    }

    fun unit(): ScaleUnit {
        return unit
    }

    fun div(distance: Distance): Double {
        return this.value.div(distance.value(unit))
    }

    fun lessThan(distance: Distance): Boolean {
        return this.value < distance.value(unit)
    }

    override fun toString(): String {
        return "$value $unit"
    }

    operator fun unaryMinus(): Radius {
        return Radius(-value)
    }
}