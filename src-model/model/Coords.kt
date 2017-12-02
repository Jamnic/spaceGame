package model

import engine.utils.TextLogger

class Coords(
        var x: Float = 0F,
        var y: Float = 0F,
        var z: Float = 0F
) {

    // TODO consider making it immutable
    operator fun plusAssign(coords: Coords) {
        x += coords.x
        y += coords.y
        z += coords.z
    }

    fun distanceTo(coords: Coords): Float {
        val x = Math.pow((x - coords.x).toDouble(), 2.0)
        val y = Math.pow((y - coords.y).toDouble(), 2.0)
        val z = Math.pow((z - coords.z).toDouble(), 2.0)

        return Math.sqrt(x + y + z).toFloat()
    }

    override fun toString(): String {
        return "(${TextLogger.twoDecimal(x)}, ${TextLogger.twoDecimal(y)}, ${TextLogger.twoDecimal(z)})"
    }
}