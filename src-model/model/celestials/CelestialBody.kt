package model.celestials

import model.celestials.parts.Orbit
import model.celestials.parts.Sphere
import model.interfaces.Drawable

import com.fasterxml.jackson.annotation.JsonCreator

import game.architecture.Entity

/**
 * Entity containing all information about given celestial body.
 *
 * TODO distances between bodies and ships mapping.
 */
open class CelestialBody
@JsonCreator
constructor(
        var name: String?,
        var orbit: Orbit?,
        var sphere: Sphere?,
        var type: CelestialBodyType?
) : Entity(CelestialBody::class.java), Drawable