package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.jogamp.opengl.util.texture.Texture
import engine.components.drawers.DrawableRing
import engine.utils.TextureLoader
import model.celestials.CelestialBody
import model.interfaces.Drawable
import model.type.DrawableResolution
import javax.media.opengl.GL2

/**
 * Object which stores the ring parameters for [Sphere] part of [CelestialBody] entity.
 */
open class Ring
@JsonCreator
constructor(
        var textureFile: String?,
        var innerRadius: Double,
        var outerRadius: Double
) : Drawable {
    @JsonIgnore
    var texture: Texture? = null
    @JsonIgnore
    var resolution: DrawableResolution = DrawableResolution.VERY_FAR

    override fun draw(gl: GL2?) {
        if (this.texture == null) {
            val texture = TextureLoader.getTexture(gl, textureFile)
            this.texture = texture
        }

        DrawableRing(this, texture).draw(gl)
    }
}