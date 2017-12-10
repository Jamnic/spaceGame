package game.model

import game.architecture.Entity
import game.engine.math.Distance
import game.engine.math.ScaleUnit
import game.model.celestials.CelestialBody

abstract class StarSystemObject<T>(
        clazz: Class<T>,
        private val coords: Coords
) : Entity(clazz) {

    fun distance(body: CelestialBody): Distance {
        return Distance(body.orbit.coords, coords, ScaleUnit.THOUSAND_KM)
    }
}