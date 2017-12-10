package game.model.ship

import game.engine.math.Degree
import game.engine.math.ScaleUnit
import game.graphics.graphics.window.GameWindow.FRAME_HEIGHT
import game.graphics.graphics.window.GameWindow.FRAME_WIDTH
import game.model.Coords
import game.model.ship.parts.Engine
import game.model.ship.parts.Position
import java.awt.event.KeyEvent
import java.awt.event.KeyEvent.*
import java.awt.event.MouseEvent

class PlayerShip(
        coords: Coords
) : Ship(
        Position(coords),
        Engine(),
        null,
        0F) {

    val scale: ScaleUnit = ScaleUnit.THOUSAND_KM

    fun mouseMoved(e: MouseEvent) {
        engine.rotationXChange = Degree(e.x.toDouble() / FRAME_WIDTH - 0.5) * 2.0
        engine.rotationYChange = Degree(e.y.toDouble() / FRAME_HEIGHT - 0.5) * 2.0
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