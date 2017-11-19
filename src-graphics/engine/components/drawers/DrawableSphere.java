package engine.components.drawers;

import com.jogamp.opengl.util.texture.Texture;
import model.celestials.parts.Sphere;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import static engine.graphics.holders.GLUHolder.GLU;

public class DrawableSphere {

    private final Sphere sphere;
    private final Texture texture;

    public DrawableSphere(Sphere sphere, Texture texture) {
        this.sphere = sphere;
        this.texture = texture;
    }

    public void draw(GL2 gl) {
        enableTexture(gl);
        GLUquadric quadric = createQuadric(gl);

        draw(quadric);

        deleteQuadric(quadric);
        disableTexture(gl);
    }

    private void draw(GLUquadric quadric) {
        // TODO przenieść do Sphere
        GLU.gluSphere(quadric,
                sphere.getRadius(),
                sphere.getResolution().getResolution(),
                sphere.getResolution().getResolution());
    }

    private void disableTexture(GL2 gl) {
        texture.disable(gl);
    }

    private void deleteQuadric(GLUquadric quadric) {
        GLU.gluDeleteQuadric(quadric);
    }

    protected GLUquadric createQuadric(GL2 gl) {
        GLUquadric quadric = GLU.gluNewQuadric();
        GLU.gluQuadricTexture(quadric, true);
        return quadric;
    }

    protected void enableTexture(GL2 gl) {
        texture.enable(gl);
        texture.bind(gl);
    }
}