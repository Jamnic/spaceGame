package model.celestials.parts

import model.celestials.CelestialBody
import model.type.DrawableResolution

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.jogamp.opengl.util.texture.Texture

import engine.calculators.PhysicsCalculator
import engine.components.drawers.DrawableSphere
import engine.utils.TextureLoader
import model.interfaces.Drawable
import javax.media.opengl.GL2

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
        var radius: Double,
        var velocity: Double,
        var inclination: Double
) : Drawable {
    var rotation: Double = 0.toDouble()

    @JsonIgnore
    var texture: Texture? = null
    @JsonIgnore
    var angularVelocity: Double = PhysicsCalculator.angularVelocity(radius, velocity)
    @JsonIgnore
    var resolution: DrawableResolution = DrawableResolution.VERY_FAR

    override fun draw(gl: GL2?) {
        if (this.texture == null) {
            val texture = TextureLoader.getTexture(gl, textureFile)
            this.texture = texture
        }
        DrawableSphere(this, texture).draw(gl)
    }
}