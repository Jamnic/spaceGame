package engine.calculators;

import javax.media.opengl.GL;
import javax.media.opengl.GL2;

public final class LightCalculator {

    private static final float PLANET_AMBIENT = 0.000000f;

    /* ========== PUBLIC ========= */
    public static void solarLight(GL2 gl) {
        float[] ambientLight = {1.0f, 1.0f, 1.0f, 1f};
        float[] diffuseLight = {1.0f, 1.0f, 1.0f, 1f};

        gl.glEnable(GL2.GL_LIGHT1);
        gl.glEnable(GL2.GL_LIGHTING);

        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambientLight, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuseLight, 0);

        float[] rgba = {1f, 1f, 1f};
        gl.glMaterialfv(GL.GL_FRONT, GL2.GL_AMBIENT, rgba, 0);
        gl.glMaterialfv(GL2.GL_LIGHT1, GL2.GL_DIFFUSE, diffuseLight, 0);
    }

    public static void planetaryLight(GL2 gl) {
        float[] ambientLight = {PLANET_AMBIENT, PLANET_AMBIENT, PLANET_AMBIENT, 1f};

        float[] lightPosition = {0, 0, 0, 1};
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_POSITION, lightPosition, 0);
        gl.glLightfv(GL2.GL_LIGHT1, GL2.GL_AMBIENT, ambientLight, 0);
    }

    /* ========== PRIVATE ========== */
    private LightCalculator() {
    }
}