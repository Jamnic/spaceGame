package model.celestials;

import java.util.LinkedList;
import java.util.List;

/**
 * Determines the type of {@link CelestialBody}.
 */
public enum CelestialBodyType {

    STAR,
    PLANET,
    MOON,
    ASTEROID,
    WORMHOLE;

    /* ========== PUBLIC ========== */
    /**
     * Returns {@link CelestialBody}'ies which glows with their own light.
     */
    public static List<CelestialBodyType> glowingCelestials() {
        return glowingCelestials;
    }

    /* ========== PRIVATE ========== */
    private static List<CelestialBodyType> glowingCelestials = new LinkedList<CelestialBodyType>();

    static {
        glowingCelestials.add(STAR);
        glowingCelestials.add(WORMHOLE);
    }

}
