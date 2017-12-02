package game.creator

import engine.math.Degree
import engine.math.Radius
import engine.math.ScaleUnit
import engine.math.Velocity
import game.architecture.Constants
import game.architecture.Constants.*
import game.architecture.GameComponentContainer
import model.celestials.CelestialBody
import model.celestials.Planet
import model.celestials.Star
import model.celestials.Wormhole
import model.celestials.parts.*
import model.system.parts.SkyBox

abstract class SystemCreator {

    fun createSkyBox(name: String): SkyBox {
        return SkyBox(
                Sphere(
                        name,
                        Radius(110f),
                        Velocity(0.0f),
                        Degree.ZERO))
    }

    fun planet(
            name: String,
            cloudsFileName: String?,
            ringName: String?,
            radius: Float,
            rotationSpeed: Float,
            inclination: Double,
            orbitingBody: CelestialBody,
            orbitRadius: Float,
            orbitSpeed: Float,
            positionInOrbit: Float,
            bodies: MutableList<CelestialBody>
    ): CelestialBody {

        val ring = createRing(ringName, radius)
        val clouds = createClouds(cloudsFileName, radius)

        val sphere = Sphere(
                name,
                Radius(radius),
                Velocity(rotationSpeed),
                Degree(inclination))

        val orbit = createOrbit(
                orbitingBody,
                Velocity(Constants.ORBITTING_PARAMETER * orbitSpeed),
                positionInOrbit,
                Radius(orbitRadius, ScaleUnit.AU))

        return addObject(Planet(name, orbit, clouds, ring, sphere), bodies)
    }

    fun moon(
            name: String,
            radius: Float,
            rotationSpeed: Float,
            inclination: Double,
            orbitingBody: CelestialBody,
            orbitRadius: Float,
            orbitSpeed: Float,
            positionInOrbit: Float,
            bodies: MutableList<CelestialBody>
    ): CelestialBody {
        return planet(
                name,
                null,
                null,
                radius * 1.5f,
                rotationSpeed,
                inclination,
                orbitingBody,
                // TODO moons do not work well
                orbitRadius,
                orbitSpeed,
                positionInOrbit,
                bodies)
    }

    fun star(
            name: String,
            radius: Float,
            rotationSpeed: Float,
            bodies: MutableList<CelestialBody>
    ): Star {

        val sphere = Sphere(
                name,
                Radius(radius),
                Velocity(rotationSpeed),
                Degree.ZERO)

        val orbit = createOrbit(null, Velocity(0f), 0f, Radius(0f, ScaleUnit.AU))

        return addObject(Star(name, orbit, sphere), bodies) as Star
    }

    fun wormhole(
            name: String,
            radius: Float,
            rotationSpeed: Float,
            inclination: Double,
            orbittingBody: CelestialBody,
            orbitRadius: Float,
            orbitSpeed: Float,
            positionInOrbit: Float,
            systemFromId: Long,
            systemToId: Long,
            bodies: MutableList<CelestialBody>): CelestialBody {

        val sphere = Sphere(
                name,
                Radius(radius),
                Velocity(rotationSpeed),
                Degree(inclination))

        val orbit = createOrbit(
                orbittingBody,
                Velocity(orbitSpeed),
                positionInOrbit,
                Radius(orbitRadius, ScaleUnit.AU))

        return addObject(Wormhole(name, orbit, sphere, systemFromId, systemToId), bodies)
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

    private fun createOrbit(
            orbittingBody: CelestialBody?,
            orbitSpeed: Velocity,
            positionInOrbit: Float,
            radius: Radius
    ): Orbit {

        return Orbit(orbittingBody, radius, orbitSpeed, Degree(positionInOrbit))
    }
}