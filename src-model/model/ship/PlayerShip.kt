package model.ship

import engine.graphics.window.GameWindow.FRAME_HEIGHT
import engine.graphics.window.GameWindow.FRAME_WIDTH
import model.Coords
import model.ship.parts.Engine
import model.ship.parts.Position
import java.awt.event.KeyEvent
import java.awt.event.MouseEvent
import engine.math.Degree

class PlayerShip(
        coords: Coords
) : Ship(Position(coords), Engine(1f), null, 0.toDouble()) {

    fun mouseMoved(e: MouseEvent) {
        val rotationXChange = Degree(e.x.toFloat() / FRAME_WIDTH.toFloat() - 0.5f)
        val rotationYChange = Degree(e.y.toFloat() / FRAME_HEIGHT.toFloat() - 0.5f)

        val engine = engine

        engine.rotationXChange = rotationXChange * 2F
        engine.rotationYChange = rotationYChange * 2F
    }

    fun keyPressed(e: KeyEvent) {
        val key = e.keyCode

        val control = control

        when (key) {
            KeyEvent.VK_S -> control.isBraking = true
            KeyEvent.VK_W -> control.isAccelerating = true
            KeyEvent.VK_SPACE -> {
                control.isTurbo = true
                control.isAccelerating = true
            }
        }
    }

    fun keyReleased(e: KeyEvent) {
        val key = e.keyCode

        val control = control

        if (key == KeyEvent.VK_S) {
            control!!.isBraking = false
        } else if (key == KeyEvent.VK_W) {
            control!!.isAccelerating = false
        } else if (key == KeyEvent.VK_SPACE) {
            control!!.isTurbo = false
            control.isAccelerating = false
        }
    }
}