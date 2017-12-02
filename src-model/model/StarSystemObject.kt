package model

import engine.math.Distance
import engine.math.ScaleUnit
import game.architecture.Entity
import model.celestials.CelestialBody

abstract class StarSystemObject<T>(
        clazz: Class<T>,
        private val coords: Coords
) : Entity(clazz) {

    fun distance(body: CelestialBody): Distance {
        return Distance(body.orbit.coords, coords, ScaleUnit.THOUSAND_KM)
    }
}