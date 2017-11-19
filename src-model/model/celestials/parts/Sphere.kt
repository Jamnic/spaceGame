package model.celestials.parts

import model.celestials.CelestialBody
import model.type.DrawableResolution

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.jogamp.opengl.util.texture.Texture

import engine.calculators.PhysicsCalculator

/**
 * Object which stores the sphere parameters for [CelestialBody] entity.
 *
 * TODO It should be upgraded by:<br></br>
 * - mass - json ignore, calculated from volume and density<br></br>
 * - density<br></br>
 */
class Sphere
@JsonCreator
constructor(
        var textureFile: String?,
        var clouds: Clouds?,
        var ring: Ring?,
        var radius: Double,
        var velocity: Double,
        var inclination: Double) {

    var rotation: Double = 0.toDouble()

    @JsonIgnore
    var texture: Texture? = null
    @JsonIgnore
    var angularVelocity: Double = PhysicsCalculator.angularVelocity(radius, velocity)
    @JsonIgnore
    var resolution: DrawableResolution = DrawableResolution.VERY_FAR
}