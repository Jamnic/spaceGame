package engine.graphics.listeners;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import model.ship.PlayerShip;

public class MainKeyListener extends AbstractMainListener implements KeyListener {

	public MainKeyListener(PlayerShip ship) {
		super(ship);
	}

	@Override
	public void keyPressed(KeyEvent e) {
		ship.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		ship.keyReleased(e);
	}

	@Override
	public void keyTyped(KeyEvent e) {
	}

}
