package model.ship.parts;

public class Engine {

    private double velocity;
    private double acceleration;

    private double rotationSpeed;

    private double rotationXChange;
    private double rotationYChange;

    /* ========== PUBLIC ========== */
    public Engine(double rotationSpeed) {
        super();

        this.rotationSpeed = rotationSpeed;
    }

    /* ========== PROPERTIES ========== */
    public double getVelocity() {
        return velocity;
    }

    public void setVelocity(double velocity) {
        this.velocity = velocity;
    }

    public double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(double acceleration) {
        this.acceleration = acceleration;
    }

    public double getRotationSpeed() {
        return rotationSpeed;
    }

    public void setRotationSpeed(double rotationSpeed) {
        this.rotationSpeed = rotationSpeed;
    }

    public double getRotationXChange() {
        return rotationXChange;
    }

    public void setRotationXChange(double rotationXChange) {
        this.rotationXChange = rotationXChange;
    }

    public double getRotationYChange() {
        return rotationYChange;
    }

    public void setRotationYChange(double rotationYChange) {
        this.rotationYChange = rotationYChange;
    }
}