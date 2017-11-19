package engine.graphics;

public class FPSCounter {

    private static final int SECOND = 1000;

    private long previous = System.currentTimeMillis();
    private long current;

    private int currentFps;
    private int fps = 0;

    public void tick() {
        
        current = System.currentTimeMillis();

        if (previous + SECOND < current) {
            previous = current;
            fps = currentFps;
            currentFps = 0;
        } else
            ++currentFps;
    }

    public int fps() {
        return fps;
    }

}
