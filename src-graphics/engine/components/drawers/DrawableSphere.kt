package engine.components.drawers

import com.jogamp.opengl.util.texture.Texture
import model.celestials.parts.Sphere

import javax.media.opengl.GL2

import engine.graphics.holders.GLUHolder.GLU
import engine.math.ScaleUnit

class DrawableSphere(
        private val sphere: Sphere,
        texture: Texture
) : DrawableElement(texture) {

    override fun drawElement(gl: GL2) {
        GLU.gluSphere(
                this.quadric,
                sphere.radius.value(ScaleUnit.THOUSAND_KM).toDouble(),
                sphere.resolution.resolution(),
                sphere.resolution.resolution())
    }
}