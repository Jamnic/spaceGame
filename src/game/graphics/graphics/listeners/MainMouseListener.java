package game.graphics.graphics.listeners;

import game.model.ship.PlayerShip;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

public class MainMouseListener extends AbstractMainListener implements MouseMotionListener {

    public MainMouseListener(PlayerShip ship) {
        super(ship);
    }

    @Override
    public void mouseMoved(MouseEvent e) {
        ship.mouseMoved(e);
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

}
