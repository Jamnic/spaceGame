package engine.calculators;

import model.Coords;
import model.celestials.parts.Sphere;
import model.ship.parts.Engine;
import model.ship.parts.Position;

import static game.architecture.Constants.*;

/**
 * Normalizes and calculates the degrees.
 */
public final class DegreeCalculator {

    /* ========== PUBLIC ========== */

    /**
     * Normalizes the degrees.
     */
    public static float normalize(float degree) {
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
    public static float calculateHeadingAngle(Coords coords, Coords destination) {
        float degree = (float) Math.toDegrees(Math.atan2(destination.getX() - coords.getX(),
                destination.getZ() - coords.getZ()));

        return normalize(degree - QUARTER_CIRCLE);
    }

    /**
     * Calculates the change of X rotation.
     */
    public static float rotationXChange(float heading, float rotationX, float rotationXSpeed) {

        float missing = heading - rotationX;
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
        position.setRotationX(position.getRotationX().plus(engine.getRotationXChange()));
        position.setRotationY(position.getRotationY().plus(engine.getRotationYChange()));
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