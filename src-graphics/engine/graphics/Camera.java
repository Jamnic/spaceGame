package engine.graphics;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import engine.graphics.holders.GLUHolder;
import game.GameRunner;

import static engine.graphics.window.GameWindow.FRAME_HEIGHT;
import static engine.graphics.window.GameWindow.FRAME_WIDTH;

public class Camera {

    private static final GLU glu = GLUHolder.GLU;
    public static final int Z_FAR = 10000000;

    public void setCamera(GL2 gl, double perspective) {
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();

        glu.gluPerspective(perspective, (double) FRAME_WIDTH / (double) FRAME_HEIGHT, 0.1, Z_FAR);
        glu.gluLookAt(-0.1, 0, 0, 0, 0, 0, 0, 1, 0);

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
    }
}