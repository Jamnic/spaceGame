package engine.calculators;

import static game.architecture.Constants.FULL_CIRCLE;
import static game.architecture.Constants.HALF_CIRCLE;
import static game.architecture.Constants.QUARTER_CIRCLE;
import model.Coords;
import model.celestials.Sphere;
import model.ship.Coords2D;
import model.ship.Engine;
import model.ship.Position;

/**
 * Normalizes and calculates the degrees.
 */
public final class DegreeCalculator {

    /* ========== PUBLIC ========== */
    /**
     * Normalizes the degrees - from 0 to 360.
     */
    public static double normalize(double degree) {
        if (degree >= FULL_CIRCLE) {
            degree -= FULL_CIRCLE;
        } else if (degree < 0) {
            degree += FULL_CIRCLE;
        }

        return degree;
    }

    /**
     * Calculates the angle where ship should be heading to achieve its destination.
     */
    public static double calculateHeadingAngle(Coords coords, Coords destination) {
        double degree = Math.toDegrees(Math.atan2(destination.getX() - coords.getX(),
                destination.getZ() - coords.getZ()));

        return normalize(degree - QUARTER_CIRCLE);
    }

    public static boolean checkVisibility(Position position, double cameraPerspective, Coords objectCoords) {

        cameraPerspective += 10;

        Coords coords = position.getCoords();
        double rotation = position.getRotationX();
        double halfPerspective = cameraPerspective / 2;

        double relative = relativeXAngle(objectCoords, coords, rotation);

        return relative <= halfPerspective || relative >= FULL_CIRCLE - halfPerspective;
    }

    /**
     * Returns relative angle between given coordinates and X rotation.
     */
    public static double relativeXAngle(Coords objectCoords, Coords coords, double rotation) {
        double angle = Math.toDegrees(Math.atan2(objectCoords.getX() - coords.getX(),
                objectCoords.getZ() - coords.getZ()));

        return normalize(angle + rotation - QUARTER_CIRCLE);
    }

    /**
     * Returns relative angle between given coordinates and X rotation.
     */
    public static double relativeYAngle(Coords objectCoords, Coords coords, double rotation) {
        double angle = Math.toDegrees(Math.atan2(objectCoords.getY() - coords.getY(),
                objectCoords.getX() - coords.getX()));

        return normalize(angle + rotation);
    }

    /**
     * Calculates the change of X rotation.
     */
    public static double rotationXChange(double heading, double rotationX, double rotationXSpeed) {

        double missing = heading - rotationX;
        if (missing > HALF_CIRCLE) {
            missing = missing - FULL_CIRCLE;
        } else if (missing < -HALF_CIRCLE) {
            missing = missing + FULL_CIRCLE;
        }

        double rotationChange = rotationXSpeed <= Math.abs(missing) ? rotationXSpeed * Math.signum(missing) : missing;

        return rotationChange;
    }

    public static void pickTarget(Position position, Coords2D target, double perspective, Coords shipCoords) {
        Coords coords = position.getCoords();
        double rotationX = position.getRotationX();
        double rotationY = position.getRotationY();

        double xAngle = DegreeCalculator.relativeXAngle(shipCoords, coords, rotationX);
        double yAngle = DegreeCalculator.relativeYAngle(shipCoords, coords, rotationY);

        xAngle = renormalize(xAngle);
        yAngle = renormalize(yAngle);

        target.setX(xAngle / perspective);
        target.setY(yAngle / perspective);
    }

    /**
     * Normalized angle from -180 to +180.
     */
    private static double renormalize(double angle) {
        angle = normalize(angle);

        return angle > HALF_CIRCLE ? angle -= FULL_CIRCLE : angle;
    }

    /**
     * Rotates ship using rotation change parameters.
     */
    public static void rotateShip(Position position, Engine engine) {
        position.setRotationX(DegreeCalculator.normalize(position.getRotationX() + engine.getRotationXChange()));
        position.setRotationY(DegreeCalculator.normalize(position.getRotationY() + engine.getRotationYChange()));
    }

    /**
     * Rotates sphere using rotation change parameters.
     */
    public static void rotateSphere(Sphere sphere) {
        sphere.setRotation(DegreeCalculator.normalize(sphere.getRotation() + sphere.getAngularVelocity()));
    }

    /* ========== PRIVATE ========== */
    private DegreeCalculator() {
    }
}