package engine.calculators;

import static game.architecture.Constants.FULL_CIRCLE;
import static game.architecture.Constants.HALF_CIRCLE;
import static game.architecture.Constants.QUARTER_CIRCLE;
import model.Coords;
import engine.utils.TextLogger;
import model.celestials.parts.Sphere;
import model.ship.parts.Engine;
import model.ship.parts.Position;

/**
 * Normalizes and calculates the degrees.
 */
public final class DegreeCalculator {

    /* ========== PUBLIC ========== */
    /**
     * Normalizes the degrees.
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

        return rotationXSpeed <= Math.abs(missing) ? rotationXSpeed * Math.signum(missing) : missing;
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