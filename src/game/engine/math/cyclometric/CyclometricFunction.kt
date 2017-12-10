package game.engine.math.cyclometric

import game.engine.math.Distance
import game.engine.math.Radian

abstract class CyclometricFunction(
        protected val radians: Radian
) {

    operator fun times(distance: Distance): Double {
        return value() * distance.value()
    }

    operator fun times(other: CyclometricFunction): Double {
        return value() * other.radians.value()
    }

    abstract fun value(): Double
}