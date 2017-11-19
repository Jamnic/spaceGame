package engine.components.drawers;

import javax.media.opengl.GL2;

import model.celestials.parts.Sphere;
import model.system.parts.SkyBox;

import game.architecture.Drawer;

public final class SkyBoxDrawer extends Drawer<SkyBox> {

	/* ========== PROTECTED ========== */
	protected void drawDrawable(GL2 gl, SkyBox skyBox) {

		Sphere sphere = skyBox.getSphere();

		gl.glDepthMask(false);

		skyBox.draw(gl);

		gl.glDepthMask(true);
	}
}