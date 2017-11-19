package game.creator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL2;

import model.Coords;
import model.celestials.CelestialBody;
import model.celestials.types.Star;
import model.ship.Engine;
import model.ship.Mesh;
import model.ship.Position;
import model.ship.Ship;
import model.system.StarSystem;
import model.system.parts.SkyBox;
import engine.utils.JsonSystemParser;
import game.architecture.Constants;
import game.architecture.GameComponentContainer;

/**
 * Creates Solar system.
 */
public class SolarSystemCreator extends SystemCreator {

    /* ========== PUBLIC ========== */
    /**
     * Method that creates Solar system.
     * 
     * TODO In future - systems should be loaded from JSON file.
     */
    public static StarSystem create(GL2 gl) throws IOException {

        List<CelestialBody> bodies = new LinkedList<CelestialBody>();

        Star sun = createStar(gl, bodies);
        createCelestialBodies(gl, bodies, sun);
        SkyBox skyBox = createSkyBox("solarSystem");

        ArrayList<Ship> ships = new ArrayList<Ship>();

        // Mesh exxonDrawer = GameComponentContainer.meshRepository.getById(0);
        Mesh exxonDrawer = GameComponentContainer.meshRepository.getById(0);

        Position position = new Position(new Coords(Constants.AU_PARAMETER, 0, 0), 90, 0);
        Engine engine = new Engine(2);
        ships.add(new Ship(position, engine, exxonDrawer, 1));

        GameComponentContainer.shipRepository.add(ships);

        StarSystem system = new StarSystem(skyBox, ships, bodies);
        JsonSystemParser.saveStarSystem("solarSystem", system);

        return system;
    }

    /* ========== PRIVATE ========== */
    private static Star createStar(GL2 gl, List<CelestialBody> celestialBodies) throws IOException {
        return star(gl, "Sun", 190671, 0.5748, celestialBodies);
    }

    private static void createCelestialBodies(GL2 gl, List<CelestialBody> bodies, Star sun) throws IOException {
        planet(gl, "Mercury", null, null, 2395, 0.2560, 0.03, sun, 0.3871, 47.87, 120, bodies);
        planet(gl, "VenusClouds", null, null, 6023, -0.0617, 2.64, sun, 0.7233, 35.02, 60, bodies);

        CelestialBody earth = planet(gl, "Earth", "EarthClouds", null, 6230, 14.4000, 23.44, sun, 1.0000, 29.78, 60,
                bodies);
        moon(gl, "Moon", null, 1738, 0.548861, 1.54, earth, 0.000256, 1.022, 0, bodies);

        planet(gl, "Mars", null, null, 3402, 14.6202, 25.19, sun, 1.5236, 24.16, 100, bodies);

        CelestialBody jupiter = planet(gl, "Jupiter", null, null, 71095, 36.3528, 3.12, sun, 5.2043, 17.82, 130, bodies);
        moon(gl, "Io", null, 1821, 0.4718, 1.00, jupiter, 0.000281, 13.07, 100, bodies);
        moon(gl, "Europe", null, 1561, 0.5505, 1.00, jupiter, 0.000447, 1.500, 80, bodies);
        moon(gl, "Ganymede", null, 2631, 0.3266, 1.00, jupiter, 0.000714, 1.500, 100, bodies);
        moon(gl, "Callisto", null, 2410, 0.3565, 1.00, jupiter, 0.001255, 1.500, 100, bodies);

        CelestialBody saturn = planet(gl, "Saturn", null, "SaturnRings", 64095, 33.7492, 26.73, sun, 9.5370, 9.63, 21,
                bodies);
        moon(gl, "Titan", null, 2575, 0.3337, 0.31, saturn, 0.000815, 1.500, 51, bodies);
        moon(gl, "Rhea", null, 764, 1.1249, 0.33, saturn, 0.000351, 1.500, 10, bodies);
        moon(gl, "Iapetus", null, 718, 1.1969, 8.30, saturn, 0.002374, 1.500, 120, bodies);
        moon(gl, "Dione", null, 559, 1.5374, 0.03, saturn, 0.000252, 1.500, 70, bodies);
        moon(gl, "Tethys", null, 529, 1.6231, 1.09, saturn, 0.000291, 1.500, 70, bodies);

        CelestialBody uranus = planet(gl, "Uranus", null, null, 41095, 20.8927, 97.77, sun, 12.1912, 6.79, 100, bodies);
        moon(gl, "Titania", null, 788, 1.089, 1.09, uranus, 0.000291, 1.500, 70, bodies);
        moon(gl, "Oberon", null, 761, 1.1293, 1.09, uranus, 0.000389, 1.500, 170, bodies);
        moon(gl, "Umbriel", null, 584, 1.4698, 1.09, uranus, 0.000177, 1.500, 270, bodies);
        moon(gl, "Ariel", null, 578, 1.4846, 1.09, uranus, 0.000127, 1.500, 80, bodies);

        CelestialBody neptune = planet(gl, "Neptune", null, null, 40095, 21.4593, 15.58, sun, 14.0689, 5.48, 0, bodies);
        moon(gl, "Triton", null, 1353, 0.6350, 156.80, neptune, 0.000256, -4.75, 70, bodies);

        wormhole(gl, "BlueGalaxy", 10000.00, 10.43, 0, sun, 4.6432, 10.32, 180, 0, 1, bodies);
    }
}