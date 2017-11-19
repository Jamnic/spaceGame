package model.ship;

import engine.graphics.GraphicsPane;
import game.GameRunner;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import model.Coords;

import com.fasterxml.jackson.annotation.JsonIgnore;
import model.ship.parts.Control;
import model.ship.parts.Engine;
import model.ship.parts.Position;

public class PlayerShip extends Ship {

    /* ========== PUBLIC ========== */
    public PlayerShip(Coords coords) {
        super(new Position(coords, 0, 0), new Engine(1), null, 0);
    }

    public void mouseMoved(MouseEvent e) {
        double rotationXChange = (double) e.getX() / (double) GameRunner.FRAME_WIDTH - 0.5;
        double rotationYChange = (double) e.getY() / (double) GameRunner.FRAME_HEIGHT - 0.5;

        Engine engine = getEngine();

        engine.setRotationXChange(2 * rotationXChange);
        engine.setRotationYChange(2 * rotationYChange);
    }

    public void keyPressed(KeyEvent e) {
        int key = e.getKeyCode();

        Control control = getControl();

        if (key == KeyEvent.VK_S) {
            control.setBraking(true);
        } else if (key == KeyEvent.VK_W) {
            control.setAccelerating(true);
        } else if (key == KeyEvent.VK_SPACE) {
            control.setTurbo(true);
            control.setAccelerating(true);
        }
    }

    public void keyReleased(KeyEvent e) {
        int key = e.getKeyCode();

        Control control = getControl();

        if (key == KeyEvent.VK_S) {
            control.setBraking(false);
        } else if (key == KeyEvent.VK_W) {
            control.setAccelerating(false);
        } else if (key == KeyEvent.VK_SPACE) {
            control.setTurbo(false);
            control.setAccelerating(false);
        }
    }

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private GraphicsPane pane;

    public void setPane(GraphicsPane graphicsPane) {
        this.pane = graphicsPane;
    }
}