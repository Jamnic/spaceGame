package game.creator

import engine.math.Degree
import engine.utils.JsonSystemParser
import game.architecture.GameComponentContainer
import model.Coords
import model.celestials.CelestialBody
import model.celestials.Star
import model.ship.Ship
import model.ship.parts.Engine
import model.ship.parts.Position
import model.system.StarSystem
import java.io.IOException
import java.util.*

class SolarSystemCreator : SystemCreator() {

    @Throws(IOException::class)
    fun create(): StarSystem {

        val bodies = LinkedList<CelestialBody>()

        val sun = createStar(bodies)
        createCelestialBodies(bodies, sun)
        val skyBox = createSkyBox("solarSystem")

        val ships = ArrayList<Ship>()

        // Mesh exxonDrawer = GameComponentContainer.meshRepository.getById(0);
        val exxonDrawer = GameComponentContainer.meshRepository.getById(0)

        ships.add(Ship(Position(Coords(1000.0, 0.0, 0.0), Degree(90f), Degree(0f)), Engine(2f, 0f, 0f, Degree.ZERO, Degree.ZERO), exxonDrawer, 1.0))

        GameComponentContainer.shipRepository.add(ships)

        val system = StarSystem(skyBox, ships, bodies)
        JsonSystemParser.saveStarSystem("solarSystem", system)

        return system
    }

    /* ========== PRIVATE ========== */
    @Throws(IOException::class)
    private fun createStar(celestialBodies: MutableList<CelestialBody>): Star {
        return star("Sun", 190671.0, 1973.20f, celestialBodies)
    }

    @Throws(IOException::class)
    private fun createCelestialBodies(bodies: MutableList<CelestialBody>, sun: Star) {
        planet("Mercury", null, null, 2395f, 10.892f, 0.03, sun, 0.3871f, 47.87f, 120f, bodies)
        planet("VenusClouds", null, null, 6023f, -6.52f, 2.64, sun, 0.7233f, 35.02f, 60f, bodies)

        val earth = planet("Earth", "EarthClouds", null, 6230f, 1601.21f, 23.44, sun, 1.0000f, 29.78f, 60f,
                bodies)
        moon("Moon", 1738.0, 16.65f, 1.54, earth, 0.000256f, 1.022f, 0f, bodies)

        planet("Mars", null, null, 3402f, 868.22f, 25.19, sun, 1.5236f, 24.16f, 100f, bodies)

        val jupiter = planet("Jupiter", null, null, 71095f, 45360.00f, 3.12, sun, 5.2043f, 17.82f, 130f,
                bodies)
        moon("Io", 1821.0, 15.00f, 1.00, jupiter, 0.000281f, 13.07f, 100f, bodies)
        moon("Europe", 1561.0, 15.00f, 1.00, jupiter, 0.000447f, 1.500f, 80f, bodies)
        moon("Ganymede", 2631.0, 15.00f, 1.00, jupiter, 0.000714f, 1.500f, 100f, bodies)
        moon("Callisto", 2410.0, 15.00f, 1.00, jupiter, 0.001255f, 1.500f, 100f, bodies)

        val saturn = planet("Saturn", null, "SaturnRings", 64095f, 35500.00f, 26.73, sun, 9.5370f, 9.63f, 21f,
                bodies)
        moon("Titan", 2575.0, 15.00f, 0.31, saturn, 0.000815f, 1.500f, 51f, bodies)
        moon("Rhea", 764.0, 15.00f, 0.33, saturn, 0.000351f, 1.500f, 10f, bodies)
        moon("Iapetus", 718.0, 15.00f, 8.30, saturn, 0.002374f, 1.500f, 120f, bodies)
        moon("Dione", 559.0, 15.00f, 0.03, saturn, 0.000252f, 1.500f, 70f, bodies)
        moon("Tethys", 529.0, 15.00f, 1.09, saturn, 0.000291f, 1.500f, 70f, bodies)

        val uranus = planet("Uranus", null, null, 41095f, 9320.00f, 97.77, sun, 12.1912f, 6.79f, 100f, bodies)
        moon("Titania", 788.0, 15.00f, 1.09, uranus, 0.000291f, 1.500f, 70f, bodies)
        moon("Oberon", 761.0, 15.00f, 1.09, uranus, 0.000389f, 1.500f, 170f, bodies)
        moon("Umbriel", 584.0, 15.00f, 1.09, uranus, 0.000177f, 1.500f, 270f, bodies)
        moon("Ariel", 578.0, 15.00f, 1.09, uranus, 0.000127f, 1.500f, 80f, bodies)

        val neptune = planet("Neptune", null, null, 40095f, 9275.52f, 15.58, sun, 14.0689f, 5.48f, 0f, bodies)
        moon("Triton", 1353.0, 15.00f, 156.80, neptune, 0.000256f, -4.75f, 70f, bodies)

        wormhole("BlueGalaxy", 10000.00, 10421.43f, 0.0, sun, 4.6432f, 10.32f, 180f, 0, 1, bodies)
    }
}