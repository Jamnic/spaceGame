package engine.components.drawers;

import engine.utils.LightLoader;
import game.architecture.Drawer;
import model.celestials.CelestialBody;
import model.celestials.parts.*;

import javax.media.opengl.GL2;

/**
 * Component responsible for drawing {@link CelestialBody}.
 */
public final class CelestialBodyDrawer extends Drawer<CelestialBody> {

    /* ========== PROTECTED ========== */
    @Override
    protected void drawDrawable(GL2 gl, CelestialBody celestialBody) {

        if (celestialBody.visible()) {

            if (celestialBody.glows())
                LightLoader.sunLight(gl);
            else
                LightLoader.planetaryLight(gl);

            translateWithCoords(gl, celestialBody.getOrbit().getCoords());
            drawSphere(gl, celestialBody);
        }
    }

    /* ========== PRIVATE ========== */
    private void drawSphere(GL2 gl, CelestialBody celestialBody) {

        Sphere sphere = celestialBody.getSphere();

        gl.glPushMatrix();

        gl.glRotated(90, 1, 0, 0); // TODO how to avoid this rotation?
        gl.glRotated(sphere.getInclination(), 1, 0, 0);
        gl.glRotated(sphere.getRotation(), 0, 0, 1);

        celestialBody.getSphere().draw(gl);
        celestialBody.getClouds().draw(gl);
        celestialBody.getRing().draw(gl);

        gl.glPopMatrix();
    }
}
