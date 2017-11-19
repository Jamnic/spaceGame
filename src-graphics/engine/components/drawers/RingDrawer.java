package engine.components.drawers;

import game.architecture.Drawer;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import model.celestials.Ring;

import com.jogamp.opengl.util.texture.Texture;

/**
 * Draws planetary ring.
 */
public class RingDrawer extends Drawer<Ring> {

    /* ========== PROTECTED ========== */
    @Override
    protected void drawDrawable(GL2 gl, Ring ring) {

        GLUquadric quadric = GLU.gluNewQuadric();
        int resolution = ring.getResolution().getRingResolution();
        double outerRadius = ring.getOuterRadius();
        double innerRadius = ring.getInnerRadius();

        Texture texture = prepareTexture(gl, ring);

        texture.enable(gl);
        texture.bind(gl);

        GLU.gluQuadricTexture(quadric, true);
        GLU.gluCylinder(quadric, innerRadius, outerRadius, RING_THICKNESS, resolution, 1);

        texture.disable(gl);

        GLU.gluDeleteQuadric(quadric);
    }
}