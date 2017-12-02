package engine.components.drawers

import com.jogamp.opengl.util.texture.Texture
import engine.graphics.holders.GLUHolder
import engine.math.ScaleUnit
import model.celestials.parts.Sphere
import javax.media.opengl.GL2

class DrawableSkyBox(
        private val sphere: Sphere,
        texture: Texture
) : DrawableElement(texture) {

    override fun drawElement(gl: GL2) {
        gl.glDepthMask(false)

        GLUHolder.GLU.gluSphere(
                this.quadric,
                sphere.radius.value(ScaleUnit.KM).toDouble(),
                sphere.resolution.resolution(),
                sphere.resolution.resolution())

        gl.glDepthMask(true)
    }
}