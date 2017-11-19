package engine.components.drawers;

import com.jogamp.opengl.util.texture.Texture;
import engine.utils.LightLoader;
import engine.utils.TextureLoader;
import game.architecture.Drawer;
import model.celestials.CelestialBody;
import model.celestials.CelestialBodyType;
import model.celestials.parts.Clouds;
import model.celestials.parts.Ring;
import model.celestials.parts.Sphere;
import model.type.DrawableResolution;

import javax.media.opengl.GL2;

/**
 * Component responsible for drawing {@link CelestialBody}.
 */
public final class CelestialBodyDrawer extends Drawer<CelestialBody> {

    /* ========== PROTECTED ========== */
    @Override
    protected void drawDrawable(GL2 gl, CelestialBody celestialBody) {

        Sphere sphere = celestialBody.getSphere();
        DrawableResolution sphereResolution = sphere.getResolution();

        if (sphereResolution != DrawableResolution.INVISIBLE) {

            CelestialBodyType type = celestialBody.getType();
            if (CelestialBodyType.glowingCelestials().contains(type))
                LightLoader.sunLight(gl);
            else
                LightLoader.planetaryLight(gl);

            translateWithCoords(gl, celestialBody.getOrbit().getCoords());
            drawSphere(gl, sphere);
        }

    }

    /* ========== PRIVATE ========== */
    private void drawSphere(GL2 gl, Sphere sphere) {

        Texture texture = sphere.getTexture();
        if (texture == null)
            sphere.setTexture(texture = TextureLoader.getTexture(gl, sphere.getTextureFile()));

        gl.glPushMatrix();

        gl.glRotated(90, 1, 0, 0);
        gl.glRotated(sphere.getInclination(), 1, 0, 0);
        gl.glRotated(sphere.getRotation(), 0, 0, 1);

        new DrawableSphere(sphere, texture).draw(gl);

        Clouds clouds = sphere.getClouds();
        if (clouds != null) {
            cloudsDrawer.draw(gl, clouds);
        }

        Ring ring = sphere.getRing();
        if (ring != null) {
            ringDrawer.draw(gl, ring);
        }

        gl.glPopMatrix();
    }
}
