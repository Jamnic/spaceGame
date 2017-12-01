package engine.managers;

import engine.calculators.CoordsCalculator;
import engine.calculators.DegreeCalculator;
import engine.math.Distance;
import engine.math.Unit;
import game.architecture.Manager;
import model.Coords;
import model.celestials.CelestialBody;
import model.celestials.Wormhole;
import model.celestials.parts.Clouds;
import model.celestials.parts.Orbit;
import model.celestials.parts.Ring;
import model.celestials.parts.Sphere;
import model.ship.PlayerShip;
import model.type.DrawableResolution;

import javax.media.opengl.GL2;
import java.util.List;

/**
 * Manages the {@link CelestialBody} objects.
 */
public class CelestialBodyManager extends Manager<CelestialBody> {

    /* ========== PUBLIC ========== */

    /**
     * Draws the given {@link CelestialBody} list using given {@link GL2} graphic context.
     *
     * @param gl              - {@link GL2} graphical context of drawing.
     * @param celestialBodies - {@link List} of {@link CelestialBody}s to be drawn.
     */
    public void draw(GL2 gl, List<CelestialBody> celestialBodies) {
        for (CelestialBody body : celestialBodies) {
            celestialBodyDrawer.draw(gl, body);
        }
    }

    /**
     * Checks collisions and resolution and updates position of {@link CelestialBody}.
     *
     * @param celestialBodies - {@link List} of {@link CelestialBody}s to process.
     * @param playerShip      - used to calculate resolution and collisions.
     */
    public void tick(List<CelestialBody> celestialBodies, PlayerShip playerShip) {
        for (CelestialBody celestialBody : celestialBodies) {
            updateCalculations(celestialBody);
            playerShip.updateDistance(celestialBody);
        }
    }

    /* ========== PRIVATE ========== */
    private void updateCalculations(CelestialBody body) {

        // Parameters
        Sphere sphere = body.sphere();
        Orbit orbit = body.getOrbit();

        // Calculations
        DegreeCalculator.rotateSphere(sphere);
        CoordsCalculator.setCelestialBodyPosition(orbit);

        CelestialBody orbittingBody = orbit.getOrbitting();
        if (orbittingBody != null) {

            // Parameters
            Sphere orbittingBodySphere = orbittingBody.sphere();
            Orbit orbittingBodyOrbit = orbittingBody.getOrbit();

            // Calculations
            CoordsCalculator.calculateOrbitalPosition(orbit, orbittingBodySphere);
            CoordsCalculator.translateBy(orbit, orbittingBodyOrbit);
        }
    }
}