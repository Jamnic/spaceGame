package engine.components.drawers;

import com.jogamp.opengl.util.texture.Texture;
import model.celestials.parts.Clouds;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import static engine.graphics.holders.GLUHolder.GLU;

class DrawableClouds {

    private final Clouds clouds;
    private final Texture texture;

    private static final double BLEND_ALPHA = 0.1;

    public DrawableClouds(Clouds clouds, Texture texture) {
        this.clouds = clouds;
        this.texture = texture;
    }

    // TODO połączyć z DrawableSphere
    public void draw(GL2 gl) {
        GLUquadric quadric = GLU.gluNewQuadric();
        GLU.gluQuadricTexture(quadric, true);

        texture.enable(gl);
        texture.bind(gl);

        gl.glEnable(GL2.GL_BLEND);
        gl.glBlendFunc(GL2.GL_SRC_ALPHA, GL2.GL_ONE);
        gl.glColor4d(0.0, 0.0, 0.0, BLEND_ALPHA);

        GLU.gluSphere(quadric,
                clouds.getRadius(),
                clouds.getResolution().getResolution(),
                clouds.getResolution().getResolution());

        gl.glDisable(GL2.GL_BLEND);
        texture.disable(gl);
        GLU.gluDeleteQuadric(quadric);
    }
}
