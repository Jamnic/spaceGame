package model.celestials;

import java.util.LinkedList;
import java.util.List;

/**
 * Determines the type of {@link CelestialBody}.
 */
public enum SphereType {

    STAR,
    PLANET,
    MOON,
    ASTEROID,
    WORMHOLE;

    /* ========== PUBLIC ========== */
    /**
     * Returns {@link CelestialBody}'ies which glows with their own light.
     */
    public static List<SphereType> getGlowing() {
        return glowingCelestials;
    }

    /* ========== PRIVATE ========== */
    private static List<SphereType> glowingCelestials = new LinkedList<SphereType>();

    static {
        glowingCelestials.add(STAR);
        glowingCelestials.add(WORMHOLE);
    }
}