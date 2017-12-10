package game.model.celestials.parts

import game.engine.math.AngularVelocity
import game.engine.math.Degree
import game.engine.math.Radius
import game.engine.math.Velocity
import game.engine.utils.TextureLoader
import game.graphics.drawers.DrawableSphere
import game.graphics.drawers.OpenGL
import game.model.interfaces.Drawable
import game.model.type.DrawableResolution

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

    override fun draw(openGL: OpenGL) {
        if (this.drawable == null) {
            this.drawable = DrawableSphere(this, TextureLoader.getTexture(openGL.gl, textureFile))
        }

        this.drawable?.draw(openGL.gl)
    }

    fun tick() {
        rotateSphere()
    }

    private fun rotateSphere() {
        rotation = (rotation + angularVelocity).normalize()
    }
}