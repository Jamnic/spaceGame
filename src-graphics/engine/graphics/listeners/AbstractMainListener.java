package engine.graphics.listeners;

import model.ship.PlayerShip;

public abstract class AbstractMainListener {

	protected PlayerShip ship;
	
	protected AbstractMainListener(PlayerShip ship) {
		this.ship = ship;
	}
	
}
