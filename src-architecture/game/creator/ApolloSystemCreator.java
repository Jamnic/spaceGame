package game.creator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL2;

import model.celestials.CelestialBody;
import model.celestials.Star;
import model.ship.Ship;
import model.system.StarSystem;
import model.system.parts.SkyBox;
import engine.utils.JsonSystemParser;

/**
 * Creates Apollo system.
 */
public class ApolloSystemCreator extends SystemCreator {

    /* ========== PUBLIC ========== */
    /**
     * Method that creates Apollo system.
     * 
     * TODO In future - systems should be loaded from JSON file.
     */
    public static StarSystem create(GL2 gl) throws IOException {

        List<CelestialBody> bodies = new LinkedList<CelestialBody>();

        Star sun = createStar(gl, bodies);
        createPlanets(gl, bodies, sun);
        SkyBox skyBox = createSkyBox("BlueGalaxy");

        ArrayList<Ship> ships = new ArrayList<Ship>();

        StarSystem system = new StarSystem(skyBox, ships, bodies);
        JsonSystemParser.saveStarSystem("apolloSystem", system);

        return system;
    }

    /* ========== PRIVATE ========== */
    private static Star createStar(GL2 gl, List<CelestialBody> bodies) throws IOException {
        return star(gl, "Apollo", 182671, 1973.20, bodies);
    }

    private static void createPlanets(GL2 gl, List<CelestialBody> bodies, Star sun) throws IOException {
        planet(gl, "Zorh", null, null, 5982.78, 3412.92, 0.01, sun, 0.4321, 56.23, 60, bodies);

        planet(gl, "Anthony", null, null, 4329.71, 543.01, 32.41, sun, 0.7218, 43.29, 80, bodies);

        CelestialBody david = planet(gl, "David", null, null, 8123.41, 2120.23, 17.21, sun, 1.3253, 32.12, 100, bodies);
        moon(gl, "Murmillo", null, 3291.89, 829.03, 2.32, david, 0.000329, 1.232, 100, bodies);
        moon(gl, "Retarius", null, 1023.17, 281.75, 4.89, david, 0.000218, 1.452, 80, bodies);

        CelestialBody alicia = planet(gl, "Alicia", null, "AliciaRings", 54495.42, 13243.89, 10.03, sun, 4.4171, 18.87,
                120, bodies);
        moon(gl, "Julio", null, 2398.17, 762.81, 11.12, alicia, 0.000318, 1.452, 80, bodies);
        moon(gl, "Maya", null, 1118.81, 342.75, 24.81, alicia, 0.000448, 1.452, 100, bodies);
        moon(gl, "Leo", null, 523.17, 21.75, 8.19, alicia, 0.000398, 1.452, 60, bodies);
        moon(gl, "Julia", null, 872.19, 162.21, 16.91, alicia, 0.000418, 1.452, 80, bodies);

        CelestialBody victor = planet(gl, "Victor", null, "NinurtaRings", 103459.32, 32429.43, 19.32, sun, 9.6432,
                12.32, 200, bodies);
        moon(gl, "Hoth", null, 1126.12, 562.81, 12.17, victor, 0.000432, 1.452, 120, bodies);
        moon(gl, "Polie", null, 2926.12, 762.81, 17.17, victor, 0.000518, 1.452, 80, bodies);
        moon(gl, "Barr", null, 676.21, 72.81, 7.11, victor, 0.000628, 1.452, 80, bodies);

        planet(gl, "Ninurta", null, "VictorRings", 62459.32, 10421.43, 65.32, sun, 14.6432, 16.32, 200, bodies);

        wormhole(gl, "solarSystem", 10000.00, 10421.43, 0, sun, 4.6432, 10.32, 200, 1, 0, bodies);
    }
}