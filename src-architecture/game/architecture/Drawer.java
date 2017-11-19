package game.architecture;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import model.Coords;
import model.interfaces.Drawable;
import engine.graphics.holders.GLUHolder;

/**
 * Abstraction over all {@link Drawer} classes.
 * 
 * @param <T> type of drawed {@link Drawable} entity.
 */
public abstract class Drawer<T extends Drawable> extends GameComponentContainer {

    protected static final int RING_THICKNESS = 10;

    /* ========== PUBLIC ========== */
    /**
     * Draws given {@link Drawable} object.
     */
    public void draw(GL2 gl, T t) {

        gl.glPushMatrix();
        drawDrawable(gl, t);
        gl.glPopMatrix();
    }

    /* ========== PROTECTED ========== */
    protected static final GLU GLU = GLUHolder.GLU;
    
    protected abstract void drawDrawable(GL2 gl, T t);

    protected void translateWithCoords(GL2 gl, Coords coords) {
        gl.glTranslated(coords.getX(), coords.getY(), coords.getZ());
    }
}