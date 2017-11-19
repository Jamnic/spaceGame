package engine.managers;

import engine.calculators.CoordsCalculator;
import engine.calculators.DegreeCalculator;
import engine.graphics.Camera;
import game.architecture.Manager;

import java.util.List;

import javax.media.opengl.GL2;

import model.Coords;
import model.DrawableResolution;
import model.celestials.CelestialBody;
import model.celestials.Clouds;
import model.celestials.Orbit;
import model.celestials.Ring;
import model.celestials.Sphere;
import model.celestials.SphereType;
import model.celestials.types.Wormhole;
import model.ship.PlayerShip;
import model.ship.Position;

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

            gl.glPushMatrix();
            sphereDrawer.translateWithCoords(gl, body.getOrbit().getCoords());
            sphereDrawer.draw(gl, body.getSphere());
            gl.glPopMatrix();
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

    private void updateDistance(CelestialBody body, PlayerShip playerShip) {

        // Parameters
        Position position = playerShip.getPosition();
        Coords playerShipCoords = position.getCoords();
        Coords bodyCoords = body.getOrbit().getCoords();
        Sphere sphere = body.getSphere();

        // Distance
        double distance = CoordsCalculator.distance(bodyCoords, playerShipCoords);
        setResolution(sphere, distance, position, bodyCoords);

        if (body.getName().equals("Uranus")) {
            DegreeCalculator.pickTarget(position, playerShip.getTarget(), Camera.PERSPECTIVE, bodyCoords);
        }

        // Collisions
        checkCollisions(body, distance);

        // Gravity
        CoordsCalculator.gravityAttraction(playerShipCoords, bodyCoords, sphere);
    }

    private void setResolution(Sphere sphere, double distance, Position position, Coords bodyCoords) {

        boolean isVisible = DegreeCalculator.checkVisibility(position, Camera.PERSPECTIVE, bodyCoords);
        DrawableResolution resolution = DrawableResolution.determineResolution(sphere.getRadius(), distance);
        sphere.setResolution(resolution);
        sphere.setVisible(isVisible);

        Clouds clouds = sphere.getClouds();
        if (clouds != null) {
            clouds.setResolution(resolution);
            clouds.setVisible(isVisible);
        }

        Ring ring = sphere.getRing();
        if (ring != null) {
            ring.setResolution(resolution);
            ring.setVisible(isVisible);
        }
    }

    private void checkCollisions(CelestialBody body, double distance) {
        if (distance < body.getSphere().getRadius() && body.getSphere().getType() == SphereType.WORMHOLE) {
            Wormhole wormhole = (Wormhole) body;

            starSystemManager.warp(wormhole);
        }
    }
}