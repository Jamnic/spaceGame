package game.graphics.drawers

import com.jogamp.opengl.util.texture.Texture
import game.engine.math.ScaleUnit
import game.graphics.graphics.holders.GLUHolder.GLU
import game.model.celestials.parts.Clouds
import javax.media.opengl.GL2

class DrawableClouds(
        private val clouds: Clouds,
        texture: Texture
) : DrawableElement(texture) {

    override fun drawElement(gl: GL2) {
        blend(gl)

        GLU.gluSphere(
                this.quadric,
                clouds.radius.value(ScaleUnit.THOUSAND_KM).toDouble(),
                clouds.resolution.resolution(),
                clouds.resolution.resolution())

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
