package engine.managers;

import java.util.List;

import javax.media.opengl.GL2;

import model.celestials.Wormhole;
import model.ship.PlayerShip;
import game.architecture.Manager;
import model.system.StarSystem;

/**
 * Manages the {@link StarSystem}.
 */
public class StarSystemManager extends Manager<StarSystem> {

    /* ========== PUBLIC ========== */
    /**
     * Draws the given {@link StarSystem} list using given {@link GL2} graphic context. Drawing is performed only on
     * visible systems.
     * 
     * @param gl - {@link GL2} graphical context of drawing.
     * @param starSystems - {@link List} of {@link StarSystem}s to be drawn.
     */
    public void draw(GL2 gl, List<StarSystem> starSystems) {
        for (StarSystem system : starSystems) {
            if (system.isVisible()) {
                celestialBodyManager.draw(gl, system.getCelestialBodies());
                shipManager.draw(gl, system.getShips());
            }
        }
    }

    /**
     * Updates positions of objects that belong to visible {@link StarSystem}s.
     */
    public void tick(List<StarSystem> starSystems, PlayerShip playerShip) {
        for (StarSystem system : starSystems) {
            if (system.isVisible()) {
                celestialBodyManager.tick(system.getCelestialBodies(), playerShip);
                shipManager.tick(system.getShips(), playerShip);
            }
        }
    }

    /**
     * Sets the visibility of given systems (warps). // TODO
     */
    public void warp(Wormhole wormhole) {
        starSystemRepository.getById(wormhole.getSystemFromId()).setVisible(false);
        starSystemRepository.getById(wormhole.getSystemToId()).setVisible(true);
    }
}