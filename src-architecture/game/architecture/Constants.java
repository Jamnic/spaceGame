package game.architecture;


import model.Coords;

/**
 * Class that stores Constants.
 */
public interface Constants {

    // System creation constants
    float SCALE = 0.01f;

    float ROTATION_PARAMETER = 1f * SCALE; // change to angular rotationVelocity
    double ORBITTING_PARAMETER = 0.001 * SCALE;
    double MOON_VARIABLE = 50000 * SCALE;

    float INNER_RING_RADIUS = 20000;
    float OUTER_RING_RADIUS = 80000;
    float CLOUDS_RADIUS = 100;

    // Velocity constants
    float TURBO_MAX_VELOCITY = 2000 * SCALE;
    double TURBO_ACCELERATION_CHANGE = 1 * SCALE;
    float MAX_ACCELERATION = 1 * SCALE;
    float MAX_TURBO_ACCELERATION = 10 * SCALE;
    double ACCELERATION_CHANGE = 1 * SCALE;
    float MAX_VELOCITY = 1 * SCALE;
    double ACCELERATION_DECREASE = 1 * SCALE;

    // Ship resolution
    double SHIP_SIZE_MAGNIFIER = 0.1;

    // Math constants
    int POWER_TWO = 2;
    int POWER_THREE = 3;
    Coords ZERO_COORDINATE = new Coords(0, 0, 0);

    // Circle constants
    int QUARTER_CIRCLE = 90;
    float FULL_CIRCLE = 360;
    double HALF_CIRCLE = 180;

    // Physics
    double GRAVITY_CONSTANT = 0.00001;
    float TWO_PI = (float) Math.PI * 2.0f;
    double VOLUME_PARAMETER = 1.33333333;
    
    // Time constants
    int TICKS_PER_SECOND = 60;
    int SECOND = 1000;
    
    int K = 1000;
}