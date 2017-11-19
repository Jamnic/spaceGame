package engine.components.drawers;

import engine.utils.LightLoader;
import game.architecture.Constants;
import game.architecture.Drawer;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import model.DrawableResolution;
import model.celestials.Clouds;
import model.celestials.Ring;
import model.celestials.Sphere;
import model.celestials.SphereType;

import com.jogamp.opengl.util.texture.Texture;

/**
 * Component responsible for drawing {@link Sphere}.
 */
public final class SphereDrawer extends Drawer<Sphere> {

    /* ========== PROTECTED ========== */
    @Override
    protected void drawDrawable(GL2 gl, Sphere sphere) {

        DrawableResolution sphereResolution = sphere.getResolution();

        if (sphereResolution != DrawableResolution.INVISIBLE) {
            applyLight(gl, sphere);

            rotate(gl, sphere);

            GLUquadric quadric = GLU.gluNewQuadric();
            int resolution = sphereResolution.getResolution();
            Texture texture = prepareTexture(gl, sphere);

            texture.enable(gl);
            texture.bind(gl);
            GLU.gluQuadricTexture(quadric, true);
            GLU.gluSphere(quadric, sphere.getRadius(), resolution, resolution);

            texture.disable(gl);

            GLU.gluDeleteQuadric(quadric);

            drawClouds(gl, sphere);
            drawRing(gl, sphere);
        }
    }

    /* ========== PRIVATE ========== */
    private void applyLight(GL2 gl, Sphere sphere) {
        if (SphereType.getGlowing().contains(sphere.getType())) {
            LightLoader.sunLight(gl);
        } else {
            LightLoader.planetaryLight(gl);
        }
    }

    private void rotate(GL2 gl, Sphere sphere) {
        gl.glRotated(Constants.QUARTER_CIRCLE, 1, 0, 0);
        gl.glRotated(sphere.getInclination(), 1, 0, 0);
        gl.glRotated(sphere.getRotation(), 0, 0, 1);
    }

    private void drawClouds(GL2 gl, Sphere sphere) {
        Clouds clouds = sphere.getClouds();
        if (clouds != null) {
            cloudsDrawer.draw(gl, clouds);
        }
    }

    private void drawRing(GL2 gl, Sphere sphere) {
        Ring ring = sphere.getRing();
        if (ring != null) {
            ringDrawer.draw(gl, ring);
        }
    }
}