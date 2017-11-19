package model;

public enum DrawableResolution {

    VERY_CLOSE(64, 25),
    CLOSE(32, 50),
    MEDIUM(24, 100),
    FAR(12, 400),
    VERY_FAR(8, 1200),
    INVISIBLE(0, 0);

    /* ========== PRIVATE ========== */
    private double distance;
    private int resolution;
    private int ringResolution;

    private DrawableResolution(int resolution, double distance) {
        this.resolution = resolution;
        this.ringResolution = 4 * resolution;
        this.distance = distance;
    }

    public int getResolution() {
        return resolution;
    }

    public int getRingResolution() {
        return ringResolution;
    }

    public static DrawableResolution determineResolution(double radius, double distanceBetween) {

        double ratio = distanceBetween / radius;

        // Let's assume that enums are sorted
        for (DrawableResolution resolution : values()) {
            if (ratio < resolution.distance) {
                return resolution;
            }
        }

        return INVISIBLE;
    }
}