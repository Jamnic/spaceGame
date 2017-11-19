package engine.components.drawers

import com.jogamp.opengl.util.texture.Texture
import model.celestials.parts.Clouds

import javax.media.opengl.GL2
import javax.media.opengl.glu.GLUquadric

import engine.graphics.holders.GLUHolder.GLU

class DrawableClouds(
        private val clouds: Clouds,
        texture: Texture
) : DrawableElement(texture) {

    override fun drawElement(gl: GL2) {
        blend(gl)

        GLU.gluSphere(
                this.quadric,
                clouds.radius,
                clouds.resolution.resolution,
                clouds.resolution.resolution)

        disableBlend(gl)
    }

    private fun blend(gl: GL2) {
        gl.glEnable(GL2.GL_BLEND)
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE)
        gl.glColor4d(0.0, 0.0, 0.0, BLEND_ALPHA)
    }

    private fun disableBlend(gl: GL2) {
        gl.glDisable(GL2.GL_BLEND)
    }

    companion object {

        private val BLEND_ALPHA = 0.1
    }
}
