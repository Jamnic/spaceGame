package game.creator

import engine.utils.JsonSystemParser
import model.celestials.CelestialBody
import model.celestials.Star
import model.ship.Ship
import model.system.StarSystem
import java.io.IOException
import java.util.*

/**
 * Creates Apollo system.
 */
class ApolloSystemCreator : SystemCreator() {

    /* ========== PUBLIC ========== */

    /**
     * Method that creates Apollo system.
     *
     *
     * TODO In future - systems should be loaded from JSON file.
     */
    @Throws(IOException::class)
    fun create(): StarSystem {

        val bodies = LinkedList<CelestialBody>()

        val sun = createStar(bodies)
        createPlanets(bodies, sun)
        val skyBox = createSkyBox("BlueGalaxy")

        val ships = ArrayList<Ship>()

        val system = StarSystem(skyBox, ships, bodies)
        JsonSystemParser.saveStarSystem("apolloSystem", system)

        return system
    }

    /* ========== PRIVATE ========== */
    @Throws(IOException::class)
    private fun createStar(bodies: MutableList<CelestialBody>): Star {
        return star("Apollo", 182671.0, 1973.20f, bodies)
    }

    @Throws(IOException::class)
    private fun createPlanets(bodies: MutableList<CelestialBody>, sun: Star) {
        planet("Zorh", null, null, 5982.78f, 3412.92f, 0.01, sun, 0.4321f, 56.23f, 60F, bodies)

        planet("Anthony", null, null, 4329.71f, 543.01f, 32.41, sun, 0.7218f, 43.29f, 80F, bodies)

        val david = planet("David", null, null, 8123.41f, 2120.23f, 17.21, sun, 1.3253f, 32.12f, 100F, bodies)
        moon("Murmillo", 3291.89, 829.03f, 2.32, david, 0.000329f, 1.232f, 100F, bodies)
        moon("Retarius", 1023.17, 281.75f, 4.89, david, 0.000218f, 1.452f, 80F, bodies)

        val alicia = planet("Alicia", null, "AliciaRings", 54495.42f, 13243.89f, 10.03, sun, 4.4171f, 18.87f,
                120F, bodies)
        moon("Julio", 2398.17, 762.81f, 11.12, alicia, 0.000318f, 1.452f, 80F, bodies)
        moon("Maya", 1118.81, 342.75f, 24.81, alicia, 0.000448f, 1.452f, 100F, bodies)
        moon("Leo", 523.17, 21.75f, 8.19, alicia, 0.000398f, 1.452f, 60F, bodies)
        moon("Julia", 872.19, 162.21f, 16.91, alicia, 0.000418f, 1.452f, 80F, bodies)

        val victor = planet("Victor", null, "NinurtaRings", 103459.32f, 32429.43f, 19.32, sun, 9.6432f,
                12.32f, 200F, bodies)
        moon("Hoth", 1126.12, 562.81f, 12.17, victor, 0.000432f, 1.452f, 120F, bodies)
        moon("Polie", 2926.12, 762.81f, 17.17, victor, 0.000518f, 1.452f, 80F, bodies)
        moon("Barr", 676.21, 72.81f, 7.11, victor, 0.000628f, 1.452f, 80F, bodies)

        planet("Ninurta", null, "VictorRings", 62459.32f, 10421.43f, 65.32, sun, 14.6432f, 16.32f, 200F, bodies)

        wormhole("solarSystem", 10000.00, 10421.43f, 0.0, sun, 4.6432f, 10.32f, 200F, 1, 0, bodies)
    }
}