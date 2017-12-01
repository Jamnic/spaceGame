package model

import engine.math.Distance
import engine.math.Unit
import game.architecture.Entity
import model.celestials.CelestialBody

abstract class StarSystemObject<T>(
        clazz: Class<T>,
        private val coords: Coords
) : Entity(clazz) {

    fun distance(body: CelestialBody): Distance {
        return Distance(body.orbit.coords, coords, Unit.THOUSAND_KM)
    }
}