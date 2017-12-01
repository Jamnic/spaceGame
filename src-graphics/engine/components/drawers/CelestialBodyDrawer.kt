package engine.components.drawers

import engine.utils.LightLoader
import game.architecture.Drawer
import model.celestials.CelestialBody
import model.celestials.parts.*

import javax.media.opengl.GL2

/**
 * Component responsible for drawing [CelestialBody].
 */
class CelestialBodyDrawer : Drawer<CelestialBody>() {

    override fun drawDrawable(gl: GL2, celestialBody: CelestialBody) {

        if (celestialBody.visible()) {

            if (celestialBody.glows())
                LightLoader.sunLight(gl)
            else
                LightLoader.planetaryLight(gl)

            translateWithCoords(gl, celestialBody.orbit.coords)
            drawSphere(gl, celestialBody)
        }
    }

    private fun drawSphere(gl: GL2, celestialBody: CelestialBody) {

        val sphere = celestialBody.sphere()

        gl.glPushMatrix()

        gl.glRotated(90.0, 1.0, 0.0, 0.0) // TODO how to avoid this rotation?
        gl.glRotated(sphere.inclination, 1.0, 0.0, 0.0)
        gl.glRotatef(sphere.rotation, 0.0f, 0.0f, 1.0f)

        celestialBody.draw(gl)

        gl.glPopMatrix()
    }
}
