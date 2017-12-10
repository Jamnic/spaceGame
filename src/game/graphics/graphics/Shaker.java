package game.graphics.graphics;

public class Shaker {

    private boolean cooldown;
    private int shakeStage;
    private double xShakeAcc;
    private double yShakeAcc;
    private double xShake;
    private double yShake;

    public void shake() {

        if (shakeStage == 0) {
            xShake = 0.10;
            yShake = -0.10;
            shakeStage = 1;
        } else if (shakeStage == 1) {
            xShake = 0.20;
            yShake = -0.20;
            shakeStage = 2;
        } else if (shakeStage == 2) {
            xShake = 0.10;
            yShake = -0.10;
            shakeStage = 3;
        } else if (shakeStage == 3) {
            xShake = 0.00;
            yShake = -0.00;
            shakeStage = 4;
        } else if (shakeStage == 4) {
            xShake = -0.10;
            yShake = 0.10;

            shakeStage = 5;
        } else if (shakeStage == 5) {
            xShake = -0.20;
            yShake = 0.20;

            shakeStage = 6;
        } else if (shakeStage == 6) {
            xShake = -0.10;
            yShake = 0.10;

            shakeStage = 7;
        } else if (shakeStage == 7) {
            xShake = -0.00;
            yShake = 0.00;

            shakeStage = 0;
        }
    }

    public boolean getCooldown() {
        return cooldown;
    }

    public void setCooldown(boolean b) {
        cooldown = b;
    }

    public double getXShake() {
        return xShake += xShakeAcc;
    }

    public double getYShake() {
        return yShake += yShakeAcc;
    }

}
