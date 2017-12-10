package game.creator

import game.architecture.GameComponentContainer
import game.engine.math.Degree
import game.model.Coords
import game.model.celestials.CelestialBody
import game.model.celestials.Star
import game.model.ship.Ship
import game.model.ship.parts.Engine
import game.model.ship.parts.Position
import game.model.system.StarSystem
import java.util.*

class SolarSystemCreator : SystemCreator() {

    fun create(): StarSystem {

        val bodies = LinkedList<CelestialBody>()

        val sun = createStar(bodies)
        createCelestialBodies(bodies, sun)
        val skyBox = createSkyBox("solarSystem")

        val ships = ArrayList<Ship>()

        val exxonDrawer = GameComponentContainer.meshRepository.getById(0)

        ships.add(
                Ship(
                        Position(
                                Coords(-950.0, 0.0, 0.0),
                                Degree(90.0),
                                Degree(0.0)),
                        Engine(0.0, 0.0, Degree.ZERO, Degree.ZERO),
                        exxonDrawer, 10.0F))

        GameComponentContainer.shipRepository.add(ships)

        return StarSystem(skyBox, ships, bodies)
    }

    private fun createStar(celestialBodies: MutableList<CelestialBody>): Star {
        return star("Sun", 190671.0, 1973.20, celestialBodies)
    }

    private fun createCelestialBodies(bodies: MutableList<CelestialBody>, sun: Star) {
        planet("Mercury", null, null, 2395.0, 10.892, 0.03, sun, 0.3871, 47.87, 120.0, bodies)
        planet("VenusClouds", null, null, 6023.0, -6.52, 2.64, sun, 0.7233, 35.02, 60.0, bodies)

        val earth = planet("Earth", "EarthClouds", null, 6230.0, 1601.21, 23.44, sun, 1.0000, 29.78, 270.0,
                bodies)
        moon("Moon", 1738.0, 16.65, 1.54, earth, 0.00256, 1.022, 0.0, bodies)

        planet("Mars", null, null, 3402.0, 868.22, 25.19, sun, 1.5236, 24.16, 100.0, bodies)

        val jupiter = planet("Jupiter", null, null, 71095.0, 45360.0, 3.12, sun, 5.2043, 17.82, 130.0,
                bodies)
        moon("Io", 1821.0, 15.00, 1.00, jupiter, 0.00281, 13.07, 100.0, bodies)
        moon("Europe", 1561.0, 15.00, 1.00, jupiter, 0.00447, 1.500, 80.0, bodies)
        moon("Ganymede", 2631.0, 15.00, 1.00, jupiter, 0.00714, 1.500, 100.0, bodies)
        moon("Callisto", 2410.0, 15.00, 1.00, jupiter, 0.01255, 1.500, 100.0, bodies)

        val saturn = planet("Saturn", null, "SaturnRings", 64095.0, 35500.0, 26.73, sun, 9.5370, 9.63, 21.0,
                bodies)
        moon("Titan", 2575.0, 15.00, 0.31, saturn, 0.00815, 1.500, 51.0, bodies)
        moon("Rhea", 764.0, 15.00, 0.33, saturn, 0.00351, 1.500, 10.0, bodies)
        moon("Iapetus", 718.0, 15.00, 8.30, saturn, 0.02374, 1.500, 120.0, bodies)
        moon("Dione", 559.0, 15.00, 0.03, saturn, 0.00252, 1.500, 70.0, bodies)
        moon("Tethys", 529.0, 15.00, 1.09, saturn, 0.00291, 1.500, 70.0, bodies)

        val uranus = planet("Uranus", null, null, 41095.0, 9320.0, 97.77, sun, 12.1912, 6.79, 100.0, bodies)
        moon("Titania", 788.0, 15.00, 1.09, uranus, 0.00291, 1.500, 70.0, bodies)
        moon("Oberon", 761.0, 15.00, 1.09, uranus, 0.00389, 1.500, 170.0, bodies)
        moon("Umbriel", 584.0, 15.00, 1.09, uranus, 0.00177, 1.500, 270.0, bodies)
        moon("Ariel", 578.0, 15.00, 1.09, uranus, 0.00127, 1.500, 80.0, bodies)

        val neptune = planet("Neptune", null, null, 40095.0, 9275.52, 15.58, sun, 14.0689, 5.48, 0.0, bodies)
        moon("Triton", 1353.0, 15.0, 156.8, neptune, 0.00256, -4.75, 70.0, bodies)

        wormhole("BlueGalaxy", 10000.0, 10421.43, 0.0, sun, 4.6432, 10.32, 180.0, 0, 1, bodies)
    }
}