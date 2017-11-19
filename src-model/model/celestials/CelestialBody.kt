package model.celestials

import com.fasterxml.jackson.annotation.JsonCreator
import game.architecture.Entity
import model.celestials.parts.*
import model.type.DrawableResolution

/**
 * Entity containing all information about given celestial body.
 *
 * TODO distances between bodies and ships mapping.
 */
open class CelestialBody
@JsonCreator
constructor(
        var name: String,
        var orbit: Orbit,
        var sphere: Sphere,
        var clouds: Clouds = EmptyClouds(),
        var ring: Ring = EmptyRing()
) : Entity(CelestialBody::class.java) {

    open fun glows() = false
    fun visible() = sphere.resolution != DrawableResolution.INVISIBLE
}