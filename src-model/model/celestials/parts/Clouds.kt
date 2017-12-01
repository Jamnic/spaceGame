package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonIgnore
import engine.components.drawers.DrawableClouds
import engine.math.Radius
import engine.utils.TextureLoader
import model.celestials.CelestialBody
import model.interfaces.Drawable
import model.type.DrawableResolution
import javax.media.opengl.GL2

/**
 * Object which stores the clouds parameters for [Sphere] part of [CelestialBody] entity.
 */
open class Clouds(
        var textureFile: String?,
        var radius: Radius
) : Drawable {
    @JsonIgnore
    var resolution: DrawableResolution = DrawableResolution.VERY_FAR

    @JsonIgnore
    var drawable : DrawableClouds? = null

    override fun draw(gl: GL2) {
        if (this.drawable == null) {
            this.drawable = DrawableClouds(this, TextureLoader.getTexture(gl, textureFile))
        }

        this.drawable?.draw(gl)
    }
}