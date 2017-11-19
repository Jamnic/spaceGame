package engine.components.drawers

import com.jogamp.opengl.util.texture.Texture
import model.celestials.parts.Sphere

import javax.media.opengl.GL2
import javax.media.opengl.glu.GLUquadric

import engine.graphics.holders.GLUHolder.GLU

class DrawableSphere(
        private val sphere: Sphere,
        texture: Texture
) : DrawableElement(texture) {

    override fun drawElement(gl: GL2) {
        GLU.gluSphere(
                this.quadric,
                sphere.radius,
                sphere.resolution.resolution,
                sphere.resolution.resolution)
    }
}