package engine.calculators;

import static game.architecture.Constants.FULL_CIRCLE;
import static game.architecture.Constants.GRAVITY_CONSTANT;
import static game.architecture.Constants.POWER_THREE;
import static game.architecture.Constants.POWER_TWO;
import static game.architecture.Constants.TWO_PI;
import static game.architecture.Constants.VOLUME_PARAMETER;
import static java.lang.Math.toRadians;

import engine.math.AngularVelocity;
import engine.math.Radius;
import model.Coords;
import model.celestials.CelestialBody;

/**
 * Utility class used to calculate physics parameters.
 */
public final class PhysicsCalculator {

    /**
     * Calculates the inclined Y coordinate of {@link CelestialBody}.
     */
    public static final float inclinedYCoordinate(float positionInRadians, float inclination, float orbitRadius) {
        return inclination == 0
                ? 0F
                : (float) (-orbitRadius * Math.sin(toRadians(inclination)) * Math.cos(positionInRadians));
    }

    /* ========== PRIVATE ========== */
    private PhysicsCalculator() {
    }
}
