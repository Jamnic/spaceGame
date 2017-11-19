package engine.components.drawers;

import com.jogamp.opengl.util.texture.Texture;
import model.celestials.parts.Ring;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import static engine.graphics.holders.GLUHolder.GLU;

public class DrawableRing {

    private final Ring ring;
    private final Texture texture;

    private static final int RING_THICKNESS = 10;

    public DrawableRing(Ring ring, Texture texture) {
        this.ring = ring;
        this.texture = texture;
    }

    // TODO połączyć z DrawableSphere
    public void draw(GL2 gl) {
        GLUquadric quadric = GLU.gluNewQuadric();
        GLU.gluQuadricTexture(quadric, true);

        texture.enable(gl);
        texture.bind(gl);

        GLU.gluCylinder(quadric,
                ring.getInnerRadius(),
                ring.getOuterRadius(),
                RING_THICKNESS,
                ring.getResolution().getResolution(),
                1);

        gl.glDisable(GL2.GL_BLEND);
        texture.disable(gl);
        GLU.gluDeleteQuadric(quadric);
    }
}
