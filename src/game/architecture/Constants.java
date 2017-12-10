package game.architecture;


/**
 * Class that stores Constants.
 */
public interface Constants {

    // System creation constants
    float SCALE = 0.01f;

    float ORBITTING_PARAMETER = 0.001f * SCALE;

    float INNER_RING_RADIUS = 20000;
    float OUTER_RING_RADIUS = 80000;
    float CLOUDS_RADIUS = 100;

    // Velocity constants
    double TURBO_MAX_VELOCITY = 2000 * SCALE;
    double TURBO_ACCELERATION_CHANGE = 1 * SCALE;
    double MAX_ACCELERATION = 1 * SCALE;
    double MAX_TURBO_ACCELERATION = 10 * SCALE;
    double ACCELERATION_CHANGE = 1 * SCALE;
    double MAX_VELOCITY = 1 * SCALE;
    double ACCELERATION_DECREASE = 1 * SCALE;

    // Ship resolution
    double SHIP_SIZE_MAGNIFIER = 0.1;

    // Math constants
    int POWER_TWO = 2;
    int POWER_THREE = 3;

    // Circle constants
    float FULL_CIRCLE = 360;

    // Physics
    double GRAVITY_CONSTANT = 0.00001;
    float TWO_PI = (float) Math.PI * 2.0f;
    double VOLUME_PARAMETER = 1.33333333;

    // Time constants
    int TICKS_PER_SECOND = 60;
    int SECOND = 1000;

    int K = 1000;
}