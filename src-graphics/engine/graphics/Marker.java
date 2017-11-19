package engine.graphics;

import javax.media.opengl.GL2;

import model.ship.PlayerShip;

import com.jogamp.opengl.util.texture.Texture;

import engine.graphics.holders.GLUHolder;
import engine.utils.LightLoader;
import engine.utils.TextureLoader;

public class Marker {

    private static final double SIZE = 0.05;
    private Texture texture;

    public void target(GL2 gl, PlayerShip playerShip) {

        gl.glMatrixMode(GL2.GL_MODELVIEW);
        gl.glLoadIdentity();
        gl.glMatrixMode(GL2.GL_PROJECTION);
        gl.glLoadIdentity();
        GLUHolder.GLU.gluOrtho2D(-1, 1, -1, 1);

        gl.glDisable(GL2.GL_DEPTH_TEST);
        gl.glDisable(GL2.GL_CULL_FACE);
        gl.glDisable(GL2.GL_TEXTURE_2D);

        LightLoader.sunLight(gl);
        // gl.glDisable(GL2.GL_LIGHTING);

        gl.glColor3f(1, 1, 1);

        if (texture == null) {
            texture = TextureLoader.getTexture(gl, "Alicia");
        }

        gl.glPushMatrix();
        float x = -2 * (float) playerShip.getTarget().getX();
        float y = 2 * (float) playerShip.getTarget().getY();

        if (x > 1) {
            x = 1;
        } else if (x < -1) {
            x = -1;
        }

        if (y > 1) {
            y = 1;
        } else if (y < -1) {
            y = -1;
        }

        gl.glBegin(GL2.GL_QUADS);

        texture.enable(gl);
        texture.bind(gl);

        gl.glVertex2d(-SIZE + x, SIZE + y);
        gl.glVertex2d(-SIZE + x, -SIZE + y);
        gl.glVertex2d(SIZE + x, -SIZE + y);
        gl.glVertex2d(SIZE + x, SIZE + y);
        gl.glEnd();
        gl.glPopMatrix();

        texture.disable(gl);

        gl.glEnable(GL2.GL_DEPTH_TEST);
    }

}
