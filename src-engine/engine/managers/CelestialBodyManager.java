package engine.managers;

import java.util.List;

import javax.media.opengl.GL2;

import model.Coords;
import model.celestials.CelestialBody;
import model.celestials.Wormhole;
import model.celestials.parts.Clouds;
import model.celestials.parts.Orbit;
import model.celestials.parts.Ring;
import model.celestials.parts.Sphere;
import model.ship.PlayerShip;
import engine.calculators.CoordsCalculator;
import engine.calculators.DegreeCalculator;
import game.architecture.Manager;
import model.type.DrawableResolution;

/**
 * Manages the {@link CelestialBody} objects.
 */
public class CelestialBodyManager extends Manager<CelestialBody> {

    /* ========== PUBLIC ========== */
    /**
     * Draws the given {@link CelestialBody} list using given {@link GL2} graphic context.
     * 
     * @param gl - {@link GL2} graphical context of drawing.
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
     * @param playerShip - used to calculate resolution and collisions.
     */
    public void tick(List<CelestialBody> celestialBodies, PlayerShip playerShip) {
        for (CelestialBody celestialBody : celestialBodies) {
            updateCalculations(celestialBody);
            updateDistance(celestialBody, playerShip);
        }
    }

    /* ========== PRIVATE ========== */
    private void updateCalculations(CelestialBody body) {

        // Parameters
        Sphere sphere = body.getSphere();
        Orbit orbit = body.getOrbit();

        // Calculations
        DegreeCalculator.rotateSphere(sphere);
        CoordsCalculator.setCelestialBodyPosition(orbit);

        CelestialBody orbittingBody = celestialBodyRepository.getById(orbit.getOrbittingBodyId());
        if (orbittingBody != null) {

            // Parameters
            Sphere orbittingBodySphere = orbittingBody.getSphere();
            Orbit orbittingBodyOrbit = orbittingBody.getOrbit();

            // Calculations
            CoordsCalculator.calculateOrbitalPosition(orbit, orbittingBodySphere);
            CoordsCalculator.translateBy(orbit, orbittingBodyOrbit);
        }
    }

    private void updateDistance(CelestialBody body, PlayerShip ship) {

        // Parameters
        Coords shipCoords = ship.getPosition().getCoords();
        Coords bodyCoords = body.getOrbit().getCoords();
        Sphere sphere = body.getSphere();

        // Distance
        double distance = CoordsCalculator.distance(bodyCoords, shipCoords);
        setResolution(body, distance);

        // Collisions
        checkCollisions(body, distance);

        // Gravity
        CoordsCalculator.gravityAttraction(shipCoords, bodyCoords, sphere);
    }

    private void setResolution(CelestialBody body, double distance) {

        DrawableResolution resolution = DrawableResolution.determineResolution(body.getSphere().getRadius(), distance);
        body.getSphere().setResolution(resolution);

        Clouds clouds = body.getClouds();
        if (clouds != null) {
            clouds.setResolution(resolution);
        }

        Ring ring = body.getRing();
        if (ring != null) {
            ring.setResolution(resolution);
        }
    }

    private void checkCollisions(CelestialBody body, double distance) {
        if (distance < body.getSphere().getRadius() && body instanceof Wormhole) {
            Wormhole wormhole = (Wormhole) body;

            starSystemManager.warp(wormhole);
        }
    }
}