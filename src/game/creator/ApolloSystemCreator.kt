package game.creator

import game.model.celestials.CelestialBody
import game.model.celestials.Star
import game.model.ship.Ship
import game.model.system.StarSystem
import java.util.*

class ApolloSystemCreator : SystemCreator() {

    fun create(): StarSystem {
        val bodies = LinkedList<CelestialBody>()

        val sun = createStar(bodies)
        createPlanets(bodies, sun)
        val skyBox = createSkyBox("BlueGalaxy")

        val ships = ArrayList<Ship>()

        return StarSystem(skyBox, ships, bodies)
    }

    private fun createStar(bodies: MutableList<CelestialBody>): Star {
        return star("Apollo", 182671.0, 1973.20, bodies)
    }

    private fun createPlanets(bodies: MutableList<CelestialBody>, sun: Star) {
        planet("Zorh", null, null, 5982.78, 3412.92, 0.01, sun, 0.4321, 56.23, 60.0, bodies)

        planet("Anthony", null, null, 4329.71, 543.01, 32.41, sun, 0.7218, 43.29, 80.0, bodies)

        val david = planet("David", null, null, 8123.41, 2120.23, 17.21, sun, 1.3253, 32.12, 100.0, bodies)
        moon("Murmillo", 3291.89, 829.03, 2.32, david, 0.00329, 1.232, 100.0, bodies)
        moon("Retarius", 1023.17, 281.75, 4.89, david, 0.00218, 1.452, 80.0, bodies)

        val alicia = planet("Alicia", null, "AliciaRings", 54495.42, 13243.89, 10.03, sun, 4.4171, 18.87,
                120.0, bodies)
        moon("Julio", 2398.17, 762.81, 11.12, alicia, 0.00318, 1.452, 80.0, bodies)
        moon("Maya", 1118.81, 342.75, 24.81, alicia, 0.00448, 1.452, 100.0, bodies)
        moon("Leo", 523.17, 21.75, 8.19, alicia, 0.00398, 1.452, 60.0, bodies)
        moon("Julia", 872.19, 162.21, 16.91, alicia, 0.00418, 1.452, 80.0, bodies)

        val victor = planet("Victor", null, "NinurtaRings", 103459.32, 32429.43, 19.32, sun, 9.6432,
                12.32, 200.0, bodies)
        moon("Hoth", 1126.12, 562.81, 12.17, victor, 0.00432, 1.452, 120.0, bodies)
        moon("Polie", 2926.12, 762.81, 17.17, victor, 0.00518, 1.452, 80.0, bodies)
        moon("Barr", 676.21, 72.81, 7.11, victor, 0.00628, 1.452, 80.0, bodies)

        planet("Ninurta", null, "VictorRings", 62459.32, 10421.43, 65.32, sun, 14.6432, 16.32, 200.0, bodies)

        wormhole("solarSystem", 10000.00, 10421.43, 0.0, sun, 4.6432, 10.32, 200.0, 1, 0, bodies)
    }
}