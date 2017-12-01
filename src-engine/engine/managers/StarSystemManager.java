package engine.managers;

import game.architecture.Manager;
import model.ship.PlayerShip;
import model.system.StarSystem;

import javax.media.opengl.GL2;
import java.util.List;

/**
 * Manages the {@link StarSystem}.
 */
public class StarSystemManager extends Manager<StarSystem> {

    /* ========== PUBLIC ========== */

    /**
     * Draws the given {@link StarSystem} list using given {@link GL2} graphic context. Drawing is performed only on
     * visible systems.
     *
     * @param gl          - {@link GL2} graphical context of drawing.
     * @param starSystems - {@link List} of {@link StarSystem}s to be drawn.
     */
    public void draw(GL2 gl, PlayerShip playerShip) {
        StarSystem system = playerShip.getStarSystem();
        celestialBodyManager.draw(gl, system.getCelestialBodies());
        shipManager.draw(gl, system.getShips());
    }

    /**
     * Updates positions of objects that belong to visible {@link StarSystem}s.
     */
    public void tick(PlayerShip playerShip) {
        StarSystem system = playerShip.getStarSystem();
        celestialBodyManager.tick(system.getCelestialBodies(), playerShip);
        shipManager.tick(system.getShips(), playerShip);
    }
}