package model.ship.task;

import model.Coords;
import model.ship.Ship;
import engine.calculators.CoordsCalculator;
import engine.calculators.DegreeCalculator;
import model.ship.parts.Control;
import model.ship.parts.Engine;
import model.ship.parts.Position;

public abstract class Task {

    /* ========== PUBLIC ========== */
    public abstract boolean execute(Ship ship);

    /* ========== PROTECTED ========== */
    protected boolean rotationChange(Ship ship, Coords destination) {

        Position position = ship.getPosition();
        Engine engine = ship.getEngine();

        Coords coords = position.getCoords();
        double rotationX = position.getRotationX();
        double rotationSpeed = engine.getRotationSpeed();

        double heading = DegreeCalculator.calculateHeadingAngle(coords, destination);
        double rotationXChange = DegreeCalculator.rotationXChange(heading, rotationX, rotationSpeed);

        engine.setRotationXChange(rotationXChange);
        
        return rotationXChange < 0.001;
    }

    protected boolean accelerationChange(Ship ship, Coords destination, double precision) {

        Position position = ship.getPosition();
        Control control = ship.getControl();

        Coords coords = position.getCoords();

        boolean finished = CoordsCalculator.equals(coords, destination, precision);
        control.setAccelerating(!finished);

        return finished;
    }
}
