package game.graphics.drawers

import com.jogamp.opengl.util.texture.Texture
import game.architecture.GameComponentContainer
import game.graphics.graphics.holders.GLUHolder.GLU
import game.model.celestials.parts.Sphere
import javax.media.opengl.GL2

class DrawableSphere(
        private val sphere: Sphere,
        texture: Texture
) : DrawableElement(texture) {

    override fun drawElement(gl: GL2) {
        GLU.gluSphere(
                this.quadric,
                sphere.radius.value(GameComponentContainer.shipManager.playerShip?.scale!!).toDouble(),
                sphere.resolution.resolution(),
                sphere.resolution.resolution())
    }
}