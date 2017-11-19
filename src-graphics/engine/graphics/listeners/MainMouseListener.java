package engine.graphics.listeners;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import model.ship.PlayerShip;

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
