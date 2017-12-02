package model.celestials.parts

import engine.calculators.PhysicsCalculator
import engine.components.drawers.DrawableSphere
import engine.math.AngularVelocity
import engine.math.Degree
import engine.math.Radius
import engine.math.Velocity
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
class Sphere(
        var textureFile: String?,
        var radius: Radius,
        rotationVelocity: Velocity,
        var inclination: Degree
) : Drawable {
    var rotation: Degree = Degree.ZERO

    var angularVelocity: AngularVelocity = AngularVelocity(radius, rotationVelocity)
    var resolution: DrawableResolution = DrawableResolution.VERY_FAR
    var drawable: DrawableSphere? = null

    fun radius(): Radius {
        return radius
    }

    override fun draw(gl: GL2) {
        if (this.drawable == null) {
            this.drawable = DrawableSphere(this, TextureLoader.getTexture(gl, textureFile))
        }

        this.drawable?.draw(gl)
    }

    fun tick() {
        rotateSphere()
    }

    private fun rotateSphere() {
        rotation = (rotation + angularVelocity).normalize()
    }
}