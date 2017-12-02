package model.celestials

import engine.calculators.PhysicsCalculator
import engine.math.Radius
import engine.math.ScaleUnit
import engine.utils.LightLoader
import model.StarSystemObject
import model.celestials.parts.*
import model.ship.PlayerShip
import model.type.DrawableResolution
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

    fun draw(gl: GL2) {
        if (visible()) {
            if (glows())
                LightLoader.sunLight(gl)
            else
                LightLoader.planetaryLight(gl)

            gl.glTranslatef(orbit.coords.x, orbit.coords.y, orbit.coords.z)

            gl.glPushMatrix()

            gl.glRotated(90.0, 1.0, 0.0, 0.0) // TODO how to avoid this rotation?
            gl.glRotatef(sphere.inclination.value(), 1.0F, 0.0F, 0.0F)
            gl.glRotatef(sphere.rotation.value(), 0.0F, 0.0F, 1.0F)

            drawComponents(gl)

            gl.glPopMatrix()
        }
    }

    fun tick(playerShip: PlayerShip) {
        sphere.tick()
        orbit.tick()
        orbitCelestialBody()

        playerShip.updateDistance(this)
    }

    private fun orbitCelestialBody() {
        val orbitingBody = orbit.orbiting
        if (orbitingBody != null) {
            calculateOrbitalPosition(orbit, orbitingBody.sphere)
            orbit.coords += orbitingBody.orbit.coords
        }
    }

    private fun calculateOrbitalPosition(orbit: Orbit, orbitingBodySphere: Sphere) {
        val radius = orbit.radius.value(ScaleUnit.THOUSAND_KM)
        val radiansPosition = orbit.position.toRadians().value()
        val inclination = orbitingBodySphere.inclination
        val coords = orbit.coords

        coords.x = Math.sin(radiansPosition.toDouble()).toFloat() * radius
        coords.y = PhysicsCalculator.inclinedYCoordinate(radiansPosition, inclination.value(), radius)
        coords.z = Math.cos(radiansPosition.toDouble()).toFloat() * radius
    }

    private fun visible(): Boolean {
        return sphere.resolution != DrawableResolution.INVISIBLE
    }

    private fun drawComponents(gl: GL2) {
        sphere.draw(gl)
        clouds.draw(gl)
        ring.draw(gl)
    }
}