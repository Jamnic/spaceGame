package engine.components.drawers;

import javax.media.opengl.GL2;
import javax.media.opengl.glu.GLU;
import javax.media.opengl.glu.GLUquadric;

import model.celestials.parts.Sphere;
import model.system.parts.SkyBox;

import com.jogamp.opengl.util.texture.Texture;

import engine.graphics.holders.GLUHolder;
import engine.utils.TextureLoader;
import game.architecture.Drawer;

public final class SkyBoxDrawer extends Drawer<SkyBox> {

	/* ========== PROTECTED ========== */
	protected void drawDrawable(GL2 gl, SkyBox skyBox) {

		Sphere sphere = skyBox.getSkyBox();

		gl.glDepthMask(false);

		Texture texture = sphere.getTexture();
		if (texture == null) {
			texture = TextureLoader.getTexture(gl, sphere.getTextureFile());
			sphere.setTexture(texture);
		}

		new DrawableSphere(sphere, texture).draw(gl);

		gl.glDepthMask(true);
	}
}