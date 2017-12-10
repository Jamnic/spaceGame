package game.engine.math.cyclometric

import game.engine.math.Degree
import game.engine.math.Radian

class Sin(
        radians: Radian
) : CyclometricFunction(radians) {

    constructor(
            degree: Degree
    ) : this(
            degree.toRadians())

    override fun value(): Double {
        return Math.sin(radians.value())
    }
}