package engine.graphics;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import engine.graphics.holders.GLUHolder;
import game.GameRunner;

/**
 * Class that stores information about camera settings.
 */
public class Camera {

    private static final double Z_NEAR = 10;
    private static final int Z_FAR = 1000000;
    public static double PERSPECTIVE;

    /* ========== PUBLIC ========== */
    public void setCamera(GL2 gl, double perspective) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        double aspectRatio = (double) GameRunner.FRAME_WIDTH / (double) GameRunner.FRAME_HEIGHT;
        
        PERSPECTIVE = perspective;

        GLU.gluPerspective(perspective, aspectRatio, Z_NEAR, Z_FAR);
        GLU.gluLookAt(-1, 0, 0, 0, 0, 0, 0, 1, 0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }

    /* ========== PRIVATE ========== */
    private static final GLU GLU = GLUHolder.GLU;
}