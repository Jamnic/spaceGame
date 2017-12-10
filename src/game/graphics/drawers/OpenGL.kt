package game.graphics.drawers

import game.architecture.GameComponentContainer
import game.engine.math.Degree
import game.engine.utils.LightLoader
import game.graphics.graphics.Shaker
import game.graphics.graphics.holders.GLUHolder
import game.graphics.graphics.window.GameWindow
import game.model.Coords
import game.model.ship.parts.Position
import javax.media.opengl.GL2

class OpenGL(
        val gl: GL2
) {

    // TODO very bad design
    private val playerShip = GameComponentContainer.shipManager.playerShip

    fun translate(coords: Coords) {
        if (playerShip != null) {
            val newCoords = coords.convertTo(playerShip.scale)
            gl.glTranslated(newCoords.x, newCoords.y, newCoords.z)
        } else {
            gl.glTranslated(coords.x, coords.y, coords.z)
        }
    }

    fun clear() {
        gl.glClear(GL2.GL_COLOR_BUFFER_BIT or GL2.GL_DEPTH_BUFFER_BIT)
    }

    fun rotate(position: Position) {
        gl.glRotated(position.rotationY.value(), 0.0, 0.0, 1.0)
        gl.glRotated(position.rotationX.value(), 0.0, 1.0, 0.0)
    }

    // TODO remove that or merge with above rotate()
    fun shake(shaker: Shaker) {
        gl.glRotated(shaker.xShake, 0.0, 1.0, 0.0)
        gl.glRotated(shaker.yShake, 0.0, 0.0, 1.0)
    }

    fun camera(perspective: Double) {
        val zFar = 1000000f
        val zNear = 1f
        val aspect = GameWindow.FRAME_WIDTH.toDouble() / GameWindow.FRAME_HEIGHT.toDouble()

        gl.glMatrixMode(GL2.GL_PROJECTION)
        gl.glLoadIdentity()

        GLUHolder.GLU.gluPerspective(perspective, aspect, zNear.toDouble(), zFar.toDouble())
        GLUHolder.GLU.gluLookAt(-0.1, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, 0.0)

        gl.glMatrixMode(GL2.GL_MODELVIEW)
        gl.glLoadIdentity()
    }

    fun pushMatrix() {
        gl.glPushMatrix()
    }

    fun popMatrix() {
        gl.glPopMatrix()
    }

    fun rotateX(degree: Degree) {
        gl.glRotated(degree.value(), 1.0, 0.0, 0.0)
    }

    // TODO delegate it
    fun skyBoxLight() {
        LightLoader.skyBoxLight(gl)
    }
}