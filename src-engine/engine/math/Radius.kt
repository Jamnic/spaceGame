package engine.math

import game.architecture.Constants

class Radius(
        value: Float,
        unit: ScaleUnit = ScaleUnit.KM
) : Distance(value, unit) {

    fun toCircuit(): Circuit {
        return Circuit(value * Constants.TWO_PI, unit)
    }
}