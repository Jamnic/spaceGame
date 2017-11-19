package engine.components.drawers;

import static game.architecture.Constants.BLEND_ALPHA;
import game.architecture.Drawer;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import model.celestials.Clouds;

import com.jogamp.opengl.util.texture.Texture;

/**
 * Draws transparent planetary clouds.
 */
public class CloudsDrawer extends Drawer<Clouds> {

    /* ========== PROTECTED ========== */
    @Override
    protected void drawDrawable(GL2 gl, Clouds clouds) {

        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE);
        gl.glColor4d(0.0, 0.0, 0.0, BLEND_ALPHA);

        GLUquadric quadric = GLU.gluNewQuadric();
        int resolution = clouds.getResolution().getResolution();
        double radius = clouds.getRadius();

        Texture texture = prepareTexture(gl, clouds);

        texture.enable(gl);
        texture.bind(gl);

        GLU.gluQuadricTexture(quadric, true);
        GLU.gluSphere(quadric, radius, resolution, resolution);

        texture.disable(gl);

        GLU.gluDeleteQuadric(quadric);

        gl.glDisable(GL2.GL_BLEND);
    }
}