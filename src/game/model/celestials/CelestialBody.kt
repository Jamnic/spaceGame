package game.model.celestials

import game.engine.math.Degree
import game.engine.math.Radius
import game.engine.utils.LightLoader
import game.graphics.drawers.OpenGL
import game.model.StarSystemObject
import game.model.celestials.parts.*
import game.model.ship.PlayerShip
import game.model.type.DrawableResolution
import javax.media.opengl.GL2

open class CelestialBody(
        val name: String,
        val orbit: Orbit,
        private val sphere: Sphere,
        private val clouds: Clouds = EmptyClouds(),
        private val ring: Ring = EmptyRing()
) : StarSystemObject<CelestialBody>(CelestialBody::class.java, orbit.coords) {

    open fun glows(): Boolean {
        return false
    }

    fun updateResolution(resolution: DrawableResolution) {
        sphere.resolution = resolution
        clouds.resolution = resolution
        ring.resolution = resolution
    }

    fun radius(): Radius {
        return sphere.radius()
    }

    fun inclination(): Degree {
        return sphere.inclination
    }

    fun draw(gl: GL2) {
        if (visible()) {
            if (glows())
                LightLoader.sunLight(gl)
            else
                LightLoader.planetaryLight(gl)

            val openGL = OpenGL(gl)

            openGL.translate(orbit.coords)

            gl.glPushMatrix()

            gl.glRotated(90.0, 1.0, 0.0, 0.0) // TODO how to avoid this rotation?
            gl.glRotated(sphere.inclination.value(), 1.0, 0.0, 0.0)
            gl.glRotated(sphere.rotation.value(), 0.0, 0.0, 1.0)

            drawComponents(openGL)

            gl.glPopMatrix()
        }
    }

    fun tick(playerShip: PlayerShip) {
        sphere.tick()
        orbit.tick()

        playerShip.updateDistance(this)
    }

    private fun visible(): Boolean {
        return sphere.resolution != DrawableResolution.INVISIBLE
    }

    private fun drawComponents(openGL: OpenGL) {
        sphere.draw(openGL)
        clouds.draw(openGL)
        ring.draw(openGL)
    }
}