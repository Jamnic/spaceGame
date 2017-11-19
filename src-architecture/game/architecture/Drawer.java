package game.architecture;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;

import com.jogamp.opengl.util.texture.Texture;

import model.Coords;
import model.interfaces.Drawable;
import engine.graphics.holders.GLUHolder;
import engine.utils.TextureLoader;

/**
 * Abstraction over all {@link Drawer} classes.
 * 
 * @param <T>
 *            type of drawed {@link Drawable} entity.
 */
public abstract class Drawer<T extends Drawable> extends GameComponentContainer {

    protected static final int RING_THICKNESS = 10;

    /* ========== PUBLIC ========== */
    /**
     * Draws given {@link Drawable} object.
     */
    public void draw(GL2 gl, T t) {

        // if (t.isVisible()) {
        gl.glPushMatrix();
        drawDrawable(gl, t);
        gl.glPopMatrix();
        // }
    }

    /* ========== PROTECTED ========== */
    protected static final GLU GLU = GLUHolder.GLU;

    protected abstract void drawDrawable(GL2 gl, T t);

    public void translateWithCoords(GL2 gl, Coords coords) {
        gl.glTranslated(coords.getX(), coords.getY(), coords.getZ());
    }

    protected Texture prepareTexture(GL2 gl, Drawable drawable) {
        Texture texture = drawable.getTexture();
        if (texture == null) {
            drawable.setTexture(texture = TextureLoader.getTexture(gl, drawable.getTextureFile()));
        }
        return texture;
    }
}