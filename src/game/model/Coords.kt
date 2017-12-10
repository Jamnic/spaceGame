package game.model

import game.engine.math.ScaleUnit
import game.engine.utils.TextLogger

class Coords(
        var x: Double = 0.0,
        var y: Double = 0.0,
        var z: Double = 0.0,
        var unit: ScaleUnit = ScaleUnit.THOUSAND_KM
) {

    // TODO consider making it immutable
    operator fun plusAssign(coords: Coords) {
        x += coords.x
        y += coords.y
        z += coords.z
    }

    fun distanceTo(coords: Coords): Double {
        val x = Math.pow((x - coords.x), 2.0)
        val y = Math.pow((y - coords.y), 2.0)
        val z = Math.pow((z - coords.z), 2.0)

        return Math.sqrt(x + y + z)
    }

    fun convertTo(scaleUnit: ScaleUnit): Coords {
        val convertTo = unit.convertTo(scaleUnit)
        return Coords(
                x * convertTo,
                y * convertTo,
                z * convertTo,
                scaleUnit)
    }

    override fun toString(): String {
        return "(${TextLogger.twoDecimal(x)} $unit, ${TextLogger.twoDecimal(y)} $unit, ${TextLogger.twoDecimal(z)} $unit)"
    }

    operator fun unaryMinus(): Coords {
        return Coords(-x, -y, -z, unit)
    }
}