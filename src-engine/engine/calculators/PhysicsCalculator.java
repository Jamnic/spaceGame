package engine.calculators;

import static game.architecture.Constants.FULL_CIRCLE;
import static game.architecture.Constants.GRAVITY_CONSTANT;
import static game.architecture.Constants.POWER_THREE;
import static game.architecture.Constants.POWER_TWO;
import static game.architecture.Constants.TWO_PI;
import static game.architecture.Constants.VOLUME_PARAMETER;
import static java.lang.Math.toRadians;
import model.Coords;
import model.celestials.CelestialBody;

/**
 * Utility class used to calculate physics parameters.
 */
public final class PhysicsCalculator {

    /* ========== PUBLIC ========== */
    /**
     * Calculates angular rotationVelocity from given radius and radial rotationVelocity.
     */
    public static final double angularVelocity(double radius, double velocity) {
        return radius > 0 ? (velocity * FULL_CIRCLE) / (radius * TWO_PI) : 0;
    }

    /**
     * Calculates the inclined Y coordinate of {@link CelestialBody}.
     */
    public static final double inclinedYCoordinate(double positionInRadians, double inclination, double orbitRadius) {
        return inclination == 0 ? 0 : -orbitRadius * Math.sin(toRadians(inclination)) * Math.cos(positionInRadians);
    }

    /**
     * Calculates gravity force between shipCoords and planetaryCoords of given radius.
     */
    public static double gravityForce(Coords shipCoords, Coords planetaryCoords, double radius, double distance) {
        return GRAVITY_CONSTANT * (VOLUME_PARAMETER * Math.PI * Math.pow(radius, POWER_THREE))
                / Math.pow(distance, POWER_TWO);
    }

    /* ========== PRIVATE ========== */
    private PhysicsCalculator() {
    }
}
