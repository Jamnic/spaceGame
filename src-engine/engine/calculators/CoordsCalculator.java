package engine.calculators;

import engine.math.Degree;
import engine.math.Radian;
import engine.math.Unit;
import model.Coords;
import model.celestials.parts.Orbit;
import model.celestials.parts.Sphere;
import model.ship.parts.Engine;
import model.ship.parts.Position;

import static java.lang.Math.*;

/**
 * Utility class which calculates distances between {@link Coords} objects and transforms them.
 */
public final class CoordsCalculator {

    /**
     * Sets the given coords with orbital calculations including orbit inclination.
     */
    public static void calculateOrbitalPosition(Orbit orbit, Sphere orbittingBodySphere) {

        Coords coords = orbit.getCoords();
        double radius = orbit.getRadius().value(Unit.THOUSAND_KM);
        double position = orbit.getPosition().value();

        double inclination = orbittingBodySphere.getInclination();

        double positionInRadians = Math.toRadians(position);

        coords.setX(Math.sin(positionInRadians) * radius);
        coords.setY(PhysicsCalculator.inclinedYCoordinate(positionInRadians, inclination, radius));
        coords.setZ(Math.cos(positionInRadians) * radius);
    }

    /**
     * Translates given coords by coordsToTranslate by simply adding them.
     */
    public static void translateBy(Orbit orbit, Orbit orbittingBodyOrbit) {

        Coords coords = orbit.getCoords();
        Coords coordsToTranslate = orbittingBodyOrbit.getCoords();

        translateBy(coords, coordsToTranslate);
    }

    /**
     * Calculates a new coordinates for ship.
     */
    public static void setShipPosition(Position position, Engine engine) {

        double velocity = engine.getVelocity();

        Coords coords = position.getCoords();
        Degree rotationX = position.getRotationX();
        Degree rotationY = position.getRotationY();

        Radian xRotationInRadians = rotationX.toRadians();
        Radian yRotationInNegativeRadians = rotationY.toRadians().negate();

        coords.setX(coords.getX() + velocity * cos(xRotationInRadians.value()));
        coords.setY(coords.getY() + velocity * sin(yRotationInNegativeRadians.value()));
        coords.setZ(coords.getZ() + velocity * sin(xRotationInRadians.value()));
    }

    public static void setCelestialBodyPosition(Orbit orbit) {
        orbit.setPosition(new Degree(DegreeCalculator.normalize(orbit.getPosition().value() + (float) orbit.getAngularVelocity())));
    }

    private CoordsCalculator() {
    }

    private static void translateBy(Coords coords, Coords coordsToTranslate) {
        coords.setX(coords.getX() + coordsToTranslate.getX());
        coords.setY(coords.getY() + coordsToTranslate.getY());
        coords.setZ(coords.getZ() + coordsToTranslate.getZ());
    }
}