package game.creator;

import static game.architecture.Constants.AU_PARAMETER;
import static game.architecture.Constants.CLOUDS_RADIUS;
import static game.architecture.Constants.INNER_RING_RADIUS;
import static game.architecture.Constants.MOON_VARIABLE;
import static game.architecture.Constants.ORBITTING_PARAMETER;
import static game.architecture.Constants.OUTER_RING_RADIUS;
import static game.architecture.Constants.RADIUS_PARAMETER;
import static game.architecture.Constants.ROTATION_PARAMETER;
import game.architecture.GameComponentContainer;

import java.io.IOException;
import java.util.List;

import javax.media.opengl.GL2;

import model.celestials.CelestialBody;
import model.celestials.Clouds;
import model.celestials.Orbit;
import model.celestials.Ring;
import model.celestials.Sphere;
import model.celestials.SphereType;
import model.celestials.types.Asteroid;
import model.celestials.types.Moon;
import model.celestials.types.Planet;
import model.celestials.types.Star;
import model.celestials.types.Wormhole;
import model.system.parts.SkyBox;

/**
 * Class containing utility methods used to create planetary systems.
 * 
 * TODO systems should be parsed from JSON files.
 */
public abstract class SystemCreator {

    /* ========== PROTECTED ========== */
    protected static SkyBox createSkyBox(String name) {
        return new SkyBox(new Sphere(name, null, null, SphereType.STAR, 110, 0.0, 0));
    }

    protected static CelestialBody planet(GL2 gl, String name, String cloudsFileName, String ringName, double radius,
            double rotationSpeed, double inclination, CelestialBody orbittingBody, double orbitRadius,
            double orbitSpeed, double positionInOrbit, List<CelestialBody> bodies) throws IOException {

        radius *= RADIUS_PARAMETER;
        orbitRadius *= AU_PARAMETER;
        orbitSpeed *= ORBITTING_PARAMETER;

        Ring ring = createRing(ringName, radius);
        Clouds clouds = createClouds(cloudsFileName, radius);

        Sphere sphere = createSphere(gl, name, clouds, ring, SphereType.PLANET, radius, rotationSpeed, inclination);
        Orbit orbit = createOrbit(radius, orbittingBody, orbitRadius, orbitSpeed, positionInOrbit);

        return addObject(new Planet(name, orbit, sphere), bodies);
    }

    protected static CelestialBody wormhole(GL2 gl, String name, double radius, double rotationSpeed,
            double inclination, CelestialBody orbittingBody, double orbitRadius, double orbitSpeed,
            double positionInOrbit, long systemFromId, long systemToId, List<CelestialBody> bodies) throws IOException {

        radius *= RADIUS_PARAMETER;
        orbitRadius *= AU_PARAMETER;
        orbitSpeed *= ORBITTING_PARAMETER;

        Sphere sphere = createSphere(gl, name, null, null, SphereType.WORMHOLE, radius, rotationSpeed, inclination);
        Orbit orbit = createOrbit(radius, orbittingBody, orbitRadius, orbitSpeed, positionInOrbit);

        return addObject(new Wormhole(name, orbit, sphere, systemFromId, systemToId), bodies);
    }

    protected static Star star(GL2 gl, String name, double radius, double rotationSpeed, List<CelestialBody> bodies)
            throws IOException {

        radius *= RADIUS_PARAMETER;

        Sphere sphere = createSphere(gl, name, null, null, SphereType.STAR, radius, rotationSpeed, 0);
        Orbit orbit = new Orbit(-1, 0, 0, 0);

        return (Star) addObject(new Star(name, orbit, sphere), bodies);
    }

    protected static CelestialBody moon(GL2 gl, String name, String cloudsFileName, double radius,
            double rotationSpeed, double inclination, CelestialBody orbittingBody, double orbitRadius,
            double orbitSpeed, double positionInOrbit, List<CelestialBody> bodies) throws IOException {

        radius *= RADIUS_PARAMETER;
        orbitRadius *= AU_PARAMETER * MOON_VARIABLE;
        orbitSpeed *= ORBITTING_PARAMETER;

        Sphere sphere = createSphere(gl, name, null, null, SphereType.MOON, radius, rotationSpeed, inclination);
        Orbit orbit = createOrbit(radius, orbittingBody, orbitRadius, orbitSpeed, positionInOrbit);

        return addObject(new Moon(name, orbit, sphere), bodies);
    }

    protected static CelestialBody createAsteroid(GL2 gl, String name, double radius, double rotationSpeed,
            double inclination, CelestialBody orbittingBody, double orbitRadius, double orbitSpeed,
            double positionInOrbit, List<CelestialBody> bodies) throws IOException {

        radius *= RADIUS_PARAMETER;
        orbitRadius *= AU_PARAMETER;
        orbitSpeed *= ORBITTING_PARAMETER;

        Sphere sphere = createSphere(gl, name, null, null, SphereType.ASTEROID, radius, rotationSpeed, inclination);
        Orbit orbit = createOrbit(radius, orbittingBody, orbitRadius, orbitSpeed, positionInOrbit);

        return addObject(new Asteroid(name, orbit, sphere), bodies);
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

    private static Sphere createSphere(GL2 gl, String name, Clouds clouds, Ring ring, SphereType type, double radius,
            double rotationSpeed, double inclination) {

        return new Sphere(name, clouds, ring, type, radius, rotationSpeed * ROTATION_PARAMETER, inclination);
    }

    private static Orbit createOrbit(double radius, CelestialBody orbittingBody, double orbitRadius, double orbitSpeed,
            double positionInOrbit) {

        return new Orbit(orbittingBody.getId(), orbitRadius, orbitSpeed, positionInOrbit);
    }
}