package engine.managers;

import java.util.List;

import javax.media.opengl.GL2;

import engine.math.Distance;
import engine.math.Radius;
import engine.math.Unit;
import model.Coords;
import model.ship.PlayerShip;
import model.ship.Ship;
import engine.calculators.CoordsCalculator;
import engine.calculators.DegreeCalculator;
import engine.calculators.VelocityCalculator;
import game.architecture.Constants;
import game.architecture.Manager;
import model.ship.parts.Control;
import model.ship.parts.Engine;
import model.ship.parts.Position;
import model.type.DrawableResolution;

/**
 * Manages the {@link Ship} objects. Updates positions and draws them.
 */
public class ShipManager extends Manager<Ship> {

    /* ========== PUBLIC ========== */
    /**
     * Draws the given {@link Ship} list using given {@link GL2} graphic context.
     * 
     * @param gl - {@link GL2} graphical context of drawing.
     * @param ships - {@link List} of {@link Ship}s to be drawn.
     */
    public void draw(GL2 gl, List<Ship> ships) {
        for (Ship ship : ships) {
            shipDrawer.draw(gl, ship);
        }
    }

    /**
     * Executes {@link Ship}s tasks, checks collisions and resolution and updates position.
     * 
     * @param ships - {@link List} of {@link Ship}s to process.
     * @param playerShip - used to calculate resolution and collisions.
     */
    public void tick(List<Ship> ships, PlayerShip playerShip) {
        updateCalculations(playerShip);

        for (Ship ship : ships) {
            updateDistance(ship, playerShip);
            updateCalculations(ship);
        }
    }

    /* ========== PRIVATE ========== */
    private void updateCalculations(Ship ship) {

        // Parameters
        Position position = ship.getPosition();
        Control control = ship.getControl();
        Engine engine = ship.getEngine();

        // Calculations
        DegreeCalculator.rotateShip(position, engine);
        VelocityCalculator.setVelocity(engine, control);
        CoordsCalculator.setShipPosition(position, engine);
        VelocityCalculator.decreaseAcceleration(engine);
    }

    private void updateDistance(Ship ship, PlayerShip playerShip) {

        // Parameters
        Coords playerShipCoords = playerShip.getPosition().getCoords();
        Coords shipCoords = ship.getPosition().getCoords();
        double size = ship.getSize();

        // Distance
        double distance = shipCoords.distanceTo(playerShipCoords);
        setResolution(ship, size, distance);

        // TODO collisions
    }

    private void setResolution(Ship ship, double size, double distance) {
        Radius radius = new Radius((float) (size * Constants.SHIP_SIZE_MAGNIFIER), Unit.KM);
        Distance distance1 = new Distance((float) distance, Unit.AU);
        DrawableResolution resolution = DrawableResolution.Companion.determineResolution(distance1.div(radius));
        ship.setResolution(resolution);
    }
}