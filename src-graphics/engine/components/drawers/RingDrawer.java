package engine.components.drawers;

import com.jogamp.opengl.util.texture.Texture;
import engine.utils.TextureLoader;
import game.architecture.Drawer;
import model.celestials.parts.Ring;

import javax.media.opengl.GL2;

/**
 * Draws planetary ring.
 */
public class RingDrawer extends Drawer<Ring> {


    /* ========== PROTECTED ========== */
    @Override
    protected void drawDrawable(GL2 gl, Ring ring) {

        Texture texture = ring.getTexture();
        if (texture == null) {
            ring.setTexture(texture = TextureLoader.getTexture(gl, ring.getTextureFile()));
        }

        new DrawableRing(ring, texture).draw(gl);
    }
}