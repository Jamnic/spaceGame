package engine.calculators;

import static game.architecture.Constants.POWER_TWO;
import static game.architecture.Constants.ZERO_COORDINATE;
import static java.lang.Math.cos;
import static java.lang.Math.pow;
import static java.lang.Math.sin;
import static java.lang.Math.sqrt;
import static java.lang.Math.toRadians;
import model.Coords;
import model.celestials.Orbit;
import model.celestials.Sphere;
import model.ship.Engine;
import model.ship.Position;

/**
 * Utility class which calculates distances between {@link Coords} objects and transforms them.
 */
public final class CoordsCalculator {

    /* ========== PUBLIC ========== */
    /**
     * Calculates Euclidesian distance between given coordinates.
     */
    public static double distance(Coords coords1, Coords coords2) {
        double x = pow(coords1.getX() - coords2.getX(), POWER_TWO);
        double y = pow(coords1.getY() - coords2.getY(), POWER_TWO);
        double z = pow(coords1.getZ() - coords2.getZ(), POWER_TWO);

        return sqrt(x + y + z);
    }

    /**
     * Calculates Euclidesian distance between given coordinate and (0, 0, 0) point.
     */
    public static double distance(Coords coords) {
        return distance(coords, ZERO_COORDINATE);
    }

    /**
     * Calculates gravity force between shipCoords and planetaryCoords of given radius.
     */
    public static void gravityAttraction(Coords shipCoords, Coords planetaryCoords, Sphere sphere) {

        double distance = distance(shipCoords, planetaryCoords);

        double force = PhysicsCalculator.gravityForce(shipCoords, planetaryCoords, sphere.getRadius(), distance);

        double xDifference = planetaryCoords.getX() - shipCoords.getX();
        double yDifference = planetaryCoords.getY() - shipCoords.getY();
        double zDifference = planetaryCoords.getZ() - shipCoords.getZ();

        double sum = force / (Math.abs(xDifference) + Math.abs(yDifference) + Math.abs(zDifference));

        shipCoords.setX(shipCoords.getX() + xDifference * sum);
        shipCoords.setY(shipCoords.getY() + yDifference * sum);
        shipCoords.setZ(shipCoords.getZ() + zDifference * sum);
    }

    /**
     * Sets the given coords with orbital calculations including orbit inclination.
     */
    public static void calculateOrbitalPosition(Orbit orbit, Sphere orbittingBodySphere) {

        Coords coords = orbit.getCoords();
        double radius = orbit.getRadius();
        double position = orbit.getPosition();

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

    public static void translateBy(Position position, Orbit orbittingBodyOrbit) {

        Coords coords = position.getCoords();
        Coords coordsToTranslate = orbittingBodyOrbit.getCoords();

        translateBy(coords, coordsToTranslate);
    }

    /**
     * Calculates a new coordinates for ship.
     */
    public static void setShipPosition(Position position, Engine engine) {

        double velocity = engine.getVelocity();

        Coords coords = position.getCoords();
        double rotationX = position.getRotationX();
        double rotationY = position.getRotationY();

        double xRotationInRadians = toRadians(rotationX);
        double yRotationInNegativeRadians = toRadians(-rotationY);

        coords.setX(coords.getX() + velocity * cos(xRotationInRadians));
        coords.setY(coords.getY() + velocity * sin(yRotationInNegativeRadians));
        coords.setZ(coords.getZ() + velocity * sin(xRotationInRadians));
    }

    public static void setCelestialBodyPosition(Orbit orbit) {
        orbit.setPosition(DegreeCalculator.normalize(orbit.getPosition() + orbit.getAngularVelocity()));
    }

    /**
     * Checks if given coordinates are equal with a given precision. Not used as far.
     */
    public static boolean equals(Coords coords1, Coords coords2, double precision) {
        return distance(coords1, coords2) <= precision;
    }

    /**
     * Checks if given coordinates are equal.
     */
    public static boolean equals(Coords coords1, Coords coords2) {
        return equals(coords1, coords2, 0);
    }

    /* ========== PRIVATE ========== */
    private CoordsCalculator() {
    }

    private static void translateBy(Coords coords, Coords coordsToTranslate) {
        coords.setX(coords.getX() + coordsToTranslate.getX());
        coords.setY(coords.getY() + coordsToTranslate.getY());
        coords.setZ(coords.getZ() + coordsToTranslate.getZ());
    }
}