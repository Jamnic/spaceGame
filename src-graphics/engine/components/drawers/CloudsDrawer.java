package engine.components.drawers;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.texture.Texture;

import engine.utils.TextureLoader;
import game.architecture.Drawer;
import model.celestials.parts.Clouds;

/**
 * Draws transparent planetary clouds.
 */
public class CloudsDrawer extends Drawer<Clouds> {

    private static final double BLEND_ALPHA = 0.1;

    /* ========== PROTECTED ========== */
    @Override
    protected void drawDrawable(GL2 gl, Clouds clouds) {

        GLUquadric quadric = GLU.gluNewQuadric();
        int resolution = clouds.getResolution().getResolution();
        double radius = clouds.getRadius();

        Texture texture = clouds.getTexture();
        if (texture == null) {
            clouds.setTexture(texture = TextureLoader.getTexture(gl, clouds.getTextureFile()));
        }

        texture.enable(gl);
        texture.bind(gl);

        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE);
        gl.glColor4d(0.0, 0.0, 0.0, BLEND_ALPHA);

//        glu.gluSphere(quadric, radius, resolution, resolution);

        texture.disable(gl);

        gl.glDisable(GL2.GL_BLEND);
    }
}