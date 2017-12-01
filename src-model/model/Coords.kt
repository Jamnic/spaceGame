package model

import engine.math.Distance
import engine.utils.TextLogger

class Coords {

    var x: Double = 0.toDouble()
    var y: Double = 0.toDouble()
    var z: Double = 0.toDouble()

    constructor(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    constructor(coords: Coords) {
        this.x = coords.x
        this.y = coords.y
        this.z = coords.z
    }

    fun distanceTo(coords: Coords) : Float {
        val x = Math.pow(x - coords.x, 2.0)
        val y = Math.pow(y - coords.y, 2.0)
        val z = Math.pow(z - coords.z, 2.0)

        return Math.sqrt(x + y + z).toFloat()
    }

    override fun toString(): String {
        return "(" + TextLogger.twoDecimal(x) + ", " + TextLogger.twoDecimal(y) + ", " + TextLogger.twoDecimal(z) + ")"
    }
}