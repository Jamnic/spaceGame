package game.architecture;


import model.Coords;

/**
 * Class that stores Constants.
 */
public interface Constants {

    // System creation constants
    double SCALE = 0.01;
    double AU_PARAMETER = 1500000 * SCALE;

    double ROTATION_PARAMETER = 0.0001 * SCALE; // change to angular rotationVelocity
    double ORBITTING_PARAMETER = 0.01 * SCALE;
    double MOON_VARIABLE = 50000 * SCALE;
    double RADIUS_PARAMETER = 1 * SCALE;

    double INNER_RING_RADIUS = 20000 * RADIUS_PARAMETER;
    double OUTER_RING_RADIUS = 80000 * RADIUS_PARAMETER;
    double CLOUDS_RADIUS = 100 * RADIUS_PARAMETER;

    // Velocity constants
    double TURBO_MAX_VELOCITY = 20000 * SCALE;
    double TURBO_ACCELERATION_CHANGE = 10 * SCALE;
    double MAX_ACCELERATION = 10 * SCALE;
    double MAX_TURBO_ACCELERATION = 100 * SCALE;
    double ACCELERATION_CHANGE = 1 * SCALE;
    double MAX_VELOCITY = 10 * SCALE;
    double ACCELERATION_DECREASE = 1 * SCALE;

    // Ship resolution
    int SHIP_SIZE_MAGNIFIER = 1000;

    // Math constants
    int POWER_TWO = 2;
    int POWER_THREE = 3;
    Coords ZERO_COORDINATE = new Coords(0, 0, 0);

    // Circle constants
    int QUARTER_CIRCLE = 90;
    double FULL_CIRCLE = 360;
    double HALF_CIRCLE = 180;

    // Physics
    double GRAVITY_CONSTANT = 0.00001;
    double TWO_PI = Math.PI * 2.0;
    double VOLUME_PARAMETER = 1.33333333;
    
    // Time constants
    int TICKS_PER_SECOND = 60;
    int SECOND = 1000;
    
    int K = 1000;
}