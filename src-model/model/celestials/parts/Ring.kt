package model.celestials.parts

import com.fasterxml.jackson.annotation.JsonCreator
import com.fasterxml.jackson.annotation.JsonIgnore
import com.jogamp.opengl.util.texture.Texture
import model.celestials.CelestialBody
import model.interfaces.Drawable
import model.type.DrawableResolution

/**
 * Object which stores the ring parameters for [Sphere] part of [CelestialBody] entity.
 */
class Ring
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
}