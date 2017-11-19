package model

import engine.utils.TextLogger

/**
 * Represents mutable coordinates in 3D space.
 */
class Coords {

    /* ========== PRIVATE ========== */
    /* ========== PROPERTIES ========== */
    var x: Double = 0.toDouble()
    var y: Double = 0.toDouble()
    var z: Double = 0.toDouble()

    /* ========== PUBLIC ========== */
    /** Used to create coordinates.  */
    constructor(x: Double, y: Double, z: Double) {
        this.x = x
        this.y = y
        this.z = z
    }

    /** Used to clone coordinates.  */
    constructor(coords: Coords) {
        this.x = coords.x
        this.y = coords.y
        this.z = coords.z
    }

    override fun toString(): String {
        return "(" + TextLogger.twoDecimal(x) + ", " + TextLogger.twoDecimal(y) + ", " + TextLogger.twoDecimal(z) + ")"
    }
}