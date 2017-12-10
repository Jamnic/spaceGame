package game.architecture;

import game.graphics.graphics.holders.GLUHolder;
import game.model.Coords;
import game.model.interfaces.Drawable;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

/**
 * Abstraction over all {@link Drawer} classes.
 *
 * @param <T> type of drawed {@link Drawable} entity.
 */
public abstract class Drawer<T> extends GameComponentContainer {

    /* ========== PUBLIC ========== */

    /* ========== PROTECTED ========== */
    protected static final GLU GLU = GLUHolder.GLU;

    /**
     * Draws given {@link Drawable} object.
     */
    public void draw(GL2 gl, T t) {

        gl.glPushMatrix();
        drawDrawable(gl, t);
        gl.glPopMatrix();
    }

    protected abstract void drawDrawable(GL2 gl, T t);

    protected void translateWithCoords(GL2 gl, Coords coords) {
        gl.glTranslated(coords.getX(), coords.getY(), coords.getZ());
    }
}