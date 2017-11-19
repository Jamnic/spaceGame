package engine.components.drawers

import com.jogamp.opengl.util.texture.Texture
import engine.graphics.holders.GLUHolder.GLU
import model.celestials.parts.Ring
import javax.media.opengl.GL2

class DrawableRing(
        private val ring: Ring,
        texture: Texture
) : DrawableElement(texture) {

    companion object {
        private val RING_THICKNESS = 10
    }

    override fun drawElement(gl: GL2) {
        GLU.gluCylinder(
                this.quadric,
                ring.innerRadius,
                ring.outerRadius,
                RING_THICKNESS.toDouble(),
                ring.resolution.resolution,
                1)
    }
}