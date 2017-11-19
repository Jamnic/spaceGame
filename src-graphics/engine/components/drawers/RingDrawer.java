package engine.components.drawers;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.texture.Texture;

import engine.utils.TextureLoader;
import game.architecture.Drawer;
import model.celestials.parts.Ring;

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

        Texture texture = ring.getTexture();
        if (texture == null) {
            ring.setTexture(texture = TextureLoader.getTexture(gl, ring.getTextureFile()));
        }

        texture.enable(gl);
        texture.bind(gl);

        glu.gluCylinder(quadric, innerRadius, outerRadius, RING_THICKNESS, resolution, 1);

        texture.disable(gl);
    }
}