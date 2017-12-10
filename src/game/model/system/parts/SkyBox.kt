package game.model.system.parts

import game.engine.math.Degree
import game.engine.utils.TextureLoader
import game.graphics.drawers.DrawableSkyBox
import game.graphics.drawers.OpenGL
import game.model.celestials.parts.Sphere
import game.model.interfaces.Drawable
import game.model.type.DrawableResolution

class SkyBox(
        val sphere: Sphere
) : Drawable {

    private var drawable: DrawableSkyBox? = null

    init {
        this.sphere.resolution = DrawableResolution.MEDIUM
    }

    override fun draw(openGL: OpenGL) {
        if (this.drawable == null) {
            this.drawable = DrawableSkyBox(sphere, TextureLoader.getTexture(openGL.gl, sphere.textureFile))
        }

        openGL.pushMatrix()
        openGL.rotateX(Degree(90))
        openGL.skyBoxLight()
        this.drawable!!.draw(openGL.gl)
        openGL.popMatrix()
    }
}