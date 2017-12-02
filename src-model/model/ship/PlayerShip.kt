package model.ship

import engine.graphics.window.GameWindow.FRAME_HEIGHT
import engine.graphics.window.GameWindow.FRAME_WIDTH
import engine.math.Degree
import model.Coords
import model.ship.parts.Engine
import model.ship.parts.Position
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.awt.event.MouseEvent

class PlayerShip(
        coords: Coords
) : Ship(Position(coords), Engine(1F), null, 0F) {

    fun mouseMoved(e: MouseEvent) {
        engine.rotationXChange = Degree(e.x.toFloat() / FRAME_WIDTH.toFloat() - 0.5f) * 2F
        engine.rotationYChange = Degree(e.y.toFloat() / FRAME_HEIGHT.toFloat() - 0.5f) * 2F
    }

    fun keyPressed(e: KeyEvent) {
        when (e.keyCode) {
            VK_S -> control.isBraking = true
            VK_W -> control.isAccelerating = true
            VK_SPACE -> {
                control.isTurbo = true
                control.isAccelerating = true
            }
        }
    }

    fun keyReleased(e: KeyEvent) {
        when (e.keyCode) {
            VK_S -> control.isBraking = false
            VK_W -> control.isAccelerating = false
            VK_SPACE -> {
                control.isTurbo = false
                control.isAccelerating = false
            }
        }
    }
}