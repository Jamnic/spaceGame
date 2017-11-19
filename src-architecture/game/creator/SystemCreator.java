package game.creator;

import game.architecture.GameComponentContainer;
import model.celestials.*;
import model.celestials.parts.Clouds;
import model.celestials.parts.Orbit;
import model.celestials.parts.Ring;
import model.celestials.parts.Sphere;
import model.system.parts.SkyBox;

import javax.media.opengl.GL2;
import java.io.IOException;
import java.util.List;

import static game.architecture.Constants.*;

/**
 * Class containing utility methods used to create planetary systems.
 * <p>
 * TODO systems should be parsed from JSON files.
 */
public abstract class SystemCreator {

    /* ========== PROTECTED ========== */
    protected static SkyBox createSkyBox(String name) {
        return new SkyBox(new Sphere(name, 110, 0.0, 0));
    }

    protected static CelestialBody planet(GL2 gl, String name, String cloudsFileName, String ringName, double radius,
                                          double rotationSpeed, double inclination, CelestialBody orbittingBody, double orbitRadius,
                                          double orbitSpeed, double positionInOrbit, List<CelestialBody> bodies) throws IOException {

        radius *= RADIUS_PARAMETER;
        orbitRadius *= AU_PARAMETER;
        orbitSpeed *= ORBITTING_PARAMETER;

        Ring ring = createRing(ringName, radius);
        Clouds clouds = createClouds(cloudsFileName, radius);

        Sphere sphere = createSphere(gl, name, radius, rotationSpeed, inclination);
        Orbit orbit = createOrbit(orbittingBody, orbitRadius, orbitSpeed, positionInOrbit);

        return addObject(new Planet(name, orbit, clouds, ring, sphere), bodies);
    }

    protected static CelestialBody wormhole(GL2 gl, String name, double radius, double rotationSpeed,
                                            double inclination, CelestialBody orbittingBody, double orbitRadius, double orbitSpeed,
                                            double positionInOrbit, long systemFromId, long systemToId, List<CelestialBody> bodies) throws IOException {

        radius *= RADIUS_PARAMETER;
        orbitRadius *= AU_PARAMETER;
        orbitSpeed *= ORBITTING_PARAMETER;

        Sphere sphere = createSphere(gl, name, radius, rotationSpeed, inclination);
        Orbit orbit = createOrbit(orbittingBody, orbitRadius, orbitSpeed, positionInOrbit);

        return addObject(new Wormhole(name, orbit, sphere, systemFromId, systemToId), bodies);
    }

    protected static Star star(GL2 gl, String name, double radius, double rotationSpeed, List<CelestialBody> bodies)
            throws IOException {

        radius *= RADIUS_PARAMETER;

        Sphere sphere = createSphere(gl, name, radius, rotationSpeed, 0);
        Orbit orbit = new Orbit(null, 0, 0, 0);

        return (Star) addObject(new Star(name, orbit, sphere), bodies);
    }

    protected static CelestialBody moon(GL2 gl, String name, String cloudsFileName, double radius,
                                        double rotationSpeed, double inclination, CelestialBody orbittingBody, double orbitRadius,
                                        double orbitSpeed, double positionInOrbit, List<CelestialBody> bodies) throws IOException {

        radius *= RADIUS_PARAMETER;
        orbitRadius *= AU_PARAMETER * MOON_VARIABLE;
        orbitSpeed *= ORBITTING_PARAMETER;

        Sphere sphere = createSphere(gl, name, radius, rotationSpeed, inclination);
        Orbit orbit = createOrbit(orbittingBody, orbitRadius, orbitSpeed, positionInOrbit);

        return addObject(new Moon(name, orbit, sphere), bodies);
    }

    private static CelestialBody addObject(CelestialBody body, List<CelestialBody> bodies) {
        bodies.add(body);
        GameComponentContainer.celestialBodyRepository.add(body);

        return body;
    }

    private static Clouds createClouds(String file, double radius) {
        return file != null ? new Clouds(file, radius + CLOUDS_RADIUS) : null;
    }

    private static Ring createRing(String file, double radius) {
        return file != null ? new Ring(file, radius + INNER_RING_RADIUS, radius + OUTER_RING_RADIUS) : null;
    }

    private static Sphere createSphere(GL2 gl, String name, double radius,
                                       double rotationSpeed, double inclination) {

        return new Sphere(name, radius, rotationSpeed * ROTATION_PARAMETER, inclination);
    }

    private static Orbit createOrbit(CelestialBody orbittingBody, double orbitRadius, double orbitSpeed,
                                     double positionInOrbit) {

        return new Orbit(orbittingBody, orbitRadius, orbitSpeed, positionInOrbit);
    }
}