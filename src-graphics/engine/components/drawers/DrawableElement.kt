package engine.components.drawers

import com.jogamp.opengl.util.texture.Texture
import engine.graphics.holders.GLUHolder.GLU
import javax.media.opengl.GL2
import javax.media.opengl.glu.GLUquadric

abstract class DrawableElement(
        private val texture: Texture
) {

    protected var quadric: GLUquadric? = null

    abstract fun drawElement(gl : GL2)

    open fun draw(gl: GL2) {
        enableTexture(gl)

        if (this.quadric == null) {
            this.quadric = createQuadric()
        }

        drawElement(gl)
        disableTexture(gl)
    }

    protected fun createQuadric(): GLUquadric {
        val quadric = GLU.gluNewQuadric()
        GLU.gluQuadricTexture(quadric, true)
        return quadric
    }

    protected fun enableTexture(gl: GL2) {
        texture.enable(gl)
        texture.bind(gl)
    }

    protected fun disableTexture(gl: GL2) {
        texture.disable(gl)
    }

    protected fun deleteQuadric(quadric: GLUquadric) {
        GLU.gluDeleteQuadric(quadric)
    }
}