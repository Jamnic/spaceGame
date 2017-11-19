package model.system.parts

import engine.components.drawers.DrawableSkyBox
import engine.components.drawers.DrawableSphere
import engine.utils.TextureLoader
import model.celestials.parts.Sphere
import model.interfaces.Drawable
import model.system.StarSystem
import model.type.DrawableResolution

import javax.media.opengl.GL2

/**
 * Class used to draw Sky Box for given [StarSystem] - every system has different star constellations. <br></br>
 * <br></br>
 * **Class responsibilities**
 *
 *  * Containing the Sky Box data.
 *  * Enables to draw Sky Box.
 *
 *
 * @author Jamnic
 */
class SkyBox(
        val sphere: Sphere
) : Drawable {

    private var drawable: DrawableSkyBox? = null

    override fun draw(gl: GL2) {
        if (this.drawable == null) {
            this.drawable = DrawableSkyBox(sphere, TextureLoader.getTexture(gl, sphere.textureFile))
        }

        this.drawable!!.draw(gl)
    }

    companion object {
        private val SKY_BOX_RESOLUTION = DrawableResolution.MEDIUM
    }

    init {
        this.sphere.resolution = SKY_BOX_RESOLUTION
    }
}