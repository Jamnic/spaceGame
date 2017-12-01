package model.celestials

import com.fasterxml.jackson.annotation.JsonCreator
import model.StarSystemObject
import model.celestials.parts.*
import model.type.DrawableResolution
import javax.media.opengl.GL2

open class CelestialBody
@JsonCreator
constructor(
        val name: String,
        val orbit: Orbit,
        private val sphere: Sphere,
        private val clouds: Clouds = EmptyClouds(),
        private val ring: Ring = EmptyRing()
) : StarSystemObject<CelestialBody>(CelestialBody::class.java, orbit.coords) {

    open fun glows() = false
    fun visible() = sphere.resolution != DrawableResolution.INVISIBLE
    fun updateResolution(resolution: DrawableResolution) {
        sphere.resolution = resolution
        clouds.resolution = resolution
        ring.resolution = resolution
    }

    fun draw(gl: GL2) {
        sphere.draw(gl)
        clouds.draw(gl)
        ring.draw(gl)
    }

    fun sphere(): Sphere {
        return sphere
    }
}