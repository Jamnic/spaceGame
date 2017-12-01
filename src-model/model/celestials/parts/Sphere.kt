package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import engine.calculators.PhysicsCalculator
import engine.components.drawers.DrawableSphere
import engine.math.Radius
import engine.utils.TextureLoader
import model.celestials.CelestialBody
import model.interfaces.Drawable
import model.type.DrawableResolution
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
        var radius: Radius,
        rotationVelocity: Float,
        var inclination: Double
) : Drawable {
    var rotation: Float = 0f

    @JsonIgnore
    var angularVelocity: Float = PhysicsCalculator.angularVelocity(radius.value(), rotationVelocity)
    @JsonIgnore
    var resolution: DrawableResolution = DrawableResolution.VERY_FAR

    @JsonIgnore
    var drawable: DrawableSphere? = null

    override fun draw(gl: GL2) {
        if (this.drawable == null) {
            this.drawable = DrawableSphere(this, TextureLoader.getTexture(gl, textureFile))
        }

        this.drawable?.draw(gl)
    }
}