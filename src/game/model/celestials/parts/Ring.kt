package game.model.celestials.parts

import com.fasterxml.jackson.annotation.JsonIgnore
import game.engine.math.Radius
import game.engine.utils.TextureLoader
import game.graphics.drawers.DrawableRing
import game.graphics.drawers.OpenGL
import game.model.celestials.CelestialBody
import game.model.interfaces.Drawable
import game.model.type.DrawableResolution

/**
 * Object which stores the ring parameters for [Sphere] part of [CelestialBody] entity.
 */
open class Ring(
        var textureFile: String?,
        var innerRadius: Radius,
        var outerRadius: Radius
) : Drawable {
    @JsonIgnore
    var resolution: DrawableResolution = DrawableResolution.VERY_FAR

    @JsonIgnore
    var drawable: DrawableRing? = null

    override fun draw(openGL: OpenGL) {
        if (this.drawable == null) {
            this.drawable = DrawableRing(this, TextureLoader.getTexture(openGL.gl, textureFile))
        }

        this.drawable?.draw(openGL.gl)
    }
}