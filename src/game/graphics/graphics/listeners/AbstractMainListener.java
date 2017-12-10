package game.graphics.graphics.listeners;

import game.model.ship.PlayerShip;

public abstract class AbstractMainListener {

    protected PlayerShip ship;

    protected AbstractMainListener(PlayerShip ship) {
        this.ship = ship;
    }

}
