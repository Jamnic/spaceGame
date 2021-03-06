package game.engine.calculators;

import game.model.ship.Ship;
import game.model.ship.parts.Control;
import game.model.ship.parts.Engine;

import static game.architecture.Constants.*;

/**
 * Calculates rotationVelocity and acceleration of {@link Ship}s.
 */
public final class VelocityCalculator {

    /* ========== PUBLIC ========== */

    /* ========== PRIVATE ========== */
    private VelocityCalculator() {
    }

    /**
     * Calculates and saves rotationVelocity and acceleration parameters of ship.
     */
    public static void setVelocity(Engine engine, Control control) {

        if (control.isTurbo()) {
            calcAcceleration(control, engine, MAX_TURBO_ACCELERATION, TURBO_ACCELERATION_CHANGE);
            calcVelocity(engine, TURBO_MAX_VELOCITY);
        } else {
            calcAcceleration(control, engine, MAX_ACCELERATION, ACCELERATION_CHANGE);
            calcVelocity(engine, MAX_VELOCITY);
        }
    }

    /**
     * Decreases acceleration after releasing the key.
     */
    public static double decreaseAcceleration(Engine engine) {

        double acceleration = engine.getAcceleration();

        if (acceleration > 0) {
            return acceleration > ACCELERATION_DECREASE ? acceleration - ACCELERATION_DECREASE : 0;
        } else if (acceleration < 0) {
            return acceleration < -ACCELERATION_DECREASE ? acceleration + ACCELERATION_DECREASE : 0;
        }

        return acceleration;
    }

    private static void calcVelocity(Engine engine, double maxVelocity) {

        double velocity = engine.getVelocity();
        double acceleration = engine.getAcceleration();

        velocity += acceleration;

        engine.setVelocity(normalize(velocity, maxVelocity));
    }

    private static void calcAcceleration(Control control, Engine engine, double maxAcceleration,
                                         double accelerationChange) {

        double acceleration = engine.getAcceleration();

        if (control.isAccelerating()) {
            engine.setAcceleration(normalize(acceleration += accelerationChange, maxAcceleration));
        } else if (control.isBraking()) {
            engine.setAcceleration(normalize(acceleration -= accelerationChange, maxAcceleration));
        }
    }

    private static double normalize(double value, double maxValue) {

        if (value > maxValue) {
            value = maxValue;
        } else if (value < -maxValue) {
            value = -maxValue;
        }

        return value;
    }
}