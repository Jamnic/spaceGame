package engine.components.drawers;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLUquadric;

import com.jogamp.opengl.util.texture.Texture;

import engine.graphics.holders.GLUHolder;
import engine.utils.TextureLoader;
import game.architecture.Drawer;
import model.celestials.parts.Clouds;
import model.celestials.parts.Sphere;

import static engine.graphics.holders.GLUHolder.GLU;

/**
 * Draws transparent planetary clouds.
 */
public class CloudsDrawer extends Drawer<Clouds> {

    /* ========== PROTECTED ========== */
    @Override
    protected void drawDrawable(GL2 gl, Clouds clouds) {
        Texture texture = clouds.getTexture();
        if (texture == null) {
            clouds.setTexture(texture = TextureLoader.getTexture(gl, clouds.getTextureFile()));
        }

        new DrawableClouds(clouds, texture).draw(gl);

    }
}