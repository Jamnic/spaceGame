package game.engine.math

import game.architecture.Constants

class Radius(
        value: Double,
        unit: ScaleUnit = ScaleUnit.KM
) : Distance(value, unit) {

    fun toCircuit(): Circuit {
        return Circuit(value * Constants.TWO_PI, unit)
    }
}