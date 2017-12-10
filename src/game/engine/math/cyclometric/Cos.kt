package game.engine.math.cyclometric

import game.engine.math.Degree
import game.engine.math.Radian

class Cos(
        radians: Radian
) : CyclometricFunction(radians) {

    constructor(
            degree: Degree
    ) : this(
            degree.toRadians())

    override fun value(): Double {
        return Math.cos(radians.value())
    }
}