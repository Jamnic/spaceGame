package model.system.parts

import engine.components.drawers.DrawableSkyBox
import engine.utils.TextureLoader
import model.celestials.parts.Sphere
import model.interfaces.Drawable
import model.type.DrawableResolution

import javax.media.opengl.GL2

class SkyBox(
        val sphere: Sphere
) : Drawable {

    private var drawable: DrawableSkyBox? = null

    init {
        this.sphere.resolution = DrawableResolution.MEDIUM
    }

    override fun draw(gl: GL2) {
        if (this.drawable == null) {
            this.drawable = DrawableSkyBox(sphere, TextureLoader.getTexture(gl, sphere.textureFile))
        }

        this.drawable!!.draw(gl)
    }
}