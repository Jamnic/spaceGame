package game.creator

import engine.math.Degree
import engine.math.Radius
import engine.math.Unit
import game.architecture.Constants.*
import game.architecture.GameComponentContainer
import model.celestials.*
import model.celestials.parts.*
import model.system.parts.SkyBox
import java.io.IOException

/**
 * Class containing utility methods used to create planetary systems.
 *
 *
 * TODO systems should be parsed from JSON files.
 */
abstract class SystemCreator {

    fun createSkyBox(name: String): SkyBox {
        val radius = Radius(110f)
        return SkyBox(Sphere(name, radius, 0.0f, 0.0))
    }

    @Throws(IOException::class)
    fun planet(name: String, cloudsFileName: String?, ringName: String?, radius: Float,
               rotationSpeed: Float, inclination: Double, orbittingBody: CelestialBody,
               orbitRadius: Float,
               orbitSpeed: Float, positionInOrbit: Float, bodies: MutableList<CelestialBody>): CelestialBody {
        var orbitSpeed = orbitSpeed

        orbitSpeed *= ORBITTING_PARAMETER.toFloat()

        val ring = createRing(ringName, radius)
        val clouds = createClouds(cloudsFileName, radius)

        val sphere = Sphere(name, Radius(radius), rotationSpeed, inclination)
        val orbit = createOrbit(orbittingBody, orbitSpeed, positionInOrbit, Radius(orbitRadius, Unit.AU))

        return addObject(Planet(name, orbit, clouds, ring, sphere), bodies)
    }

    @Throws(IOException::class)
    fun wormhole(name: String,
                 radius: Double,
                 rotationSpeed: Float,
                 inclination: Double,
                 orbittingBody: CelestialBody,
                 orbitRadius: Float,
                 orbitSpeed: Float,
                 positionInOrbit: Float,
                 systemFromId: Long,
                 systemToId: Long,
                 bodies: MutableList<CelestialBody>): CelestialBody {
        var orbitSpeed = orbitSpeed

        orbitSpeed *= ORBITTING_PARAMETER.toFloat()

        val sphere = Sphere(name, Radius(radius.toFloat()), rotationSpeed, inclination)
        val orbit = createOrbit(orbittingBody, orbitSpeed, positionInOrbit, Radius(orbitRadius, Unit.AU))

        return addObject(Wormhole(name, orbit, sphere, systemFromId, systemToId), bodies)
    }

    @Throws(IOException::class)
    fun star(name: String, radius: Double, rotationSpeed: Float, bodies: MutableList<CelestialBody>): Star {

        val sphere = Sphere(name, Radius(radius.toFloat()), rotationSpeed, 0.0)
        val orbit = createOrbit(null, 0f, 0f, Radius(0f, Unit.AU))

        return addObject(Star(name, orbit, sphere), bodies) as Star
    }

    @Throws(IOException::class)
    fun moon(name: String,
             radius: Double,
             rotationSpeed: Float,
             inclination: Double,
             orbittingBody: CelestialBody,
             orbitRadius: Float,
             orbitSpeed: Float,
             positionInOrbit: Float,
             bodies: MutableList<CelestialBody>): CelestialBody {
        var orbitSpeed = orbitSpeed

        orbitSpeed *= ORBITTING_PARAMETER.toFloat()

        val sphere = Sphere(name, Radius(radius.toFloat()), rotationSpeed, inclination)
        val orbit = createOrbit(orbittingBody, orbitSpeed, positionInOrbit, Radius(orbitRadius, Unit.AU))

        return addObject(Moon(name, orbit, sphere), bodies)
    }

    private fun addObject(body: CelestialBody, bodies: MutableList<CelestialBody>): CelestialBody {
        bodies.add(body)
        GameComponentContainer.celestialBodyRepository.add(body)

        return body
    }

    private fun createClouds(textureFile: String?, radius: Float): Clouds {
        return if (textureFile != null) {
            val cloudsRadius = Radius(radius + CLOUDS_RADIUS)
            Clouds(textureFile, cloudsRadius)
        } else {
            EmptyClouds()
        }
    }

    private fun createRing(textureFile: String?, radius: Float): Ring {
        return if (textureFile != null) {
            val innerRadius = Radius(radius + INNER_RING_RADIUS)
            val outerRadius = Radius(radius + OUTER_RING_RADIUS)
            Ring(textureFile, innerRadius, outerRadius)
        } else {
            EmptyRing()
        }
    }

    private fun createOrbit(orbittingBody: CelestialBody?, orbitSpeed: Float,
                        positionInOrbit: Float, radius: Radius): Orbit {

        return Orbit(orbittingBody, radius, orbitSpeed, Degree(positionInOrbit))
    }
}