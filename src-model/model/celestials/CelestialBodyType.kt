package model.celestials

import java.util.LinkedList

/**
 * Determines the type of [CelestialBody].
 */
enum class CelestialBodyType {

    STAR,
    PLANET,
    MOON,
    ASTEROID,
    WORMHOLE;

    companion object {

        /**
         * Returns [CelestialBody]'ies which glows with their own light.
         */
        fun glowingCelestials(): List<CelestialBodyType> {
            return glowingCelestials
        }

        private val glowingCelestials = LinkedList<CelestialBodyType>()

        init {
            glowingCelestials.add(STAR)
            glowingCelestials.add(WORMHOLE)
        }
    }

}
