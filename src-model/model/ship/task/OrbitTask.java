package model.ship.task;

import model.Coords;
import model.celestials.CelestialBody;
import model.ship.Ship;
import engine.calculators.CoordsCalculator;
import engine.calculators.PhysicsCalculator;

public class OrbitTask extends Task {

    private CelestialBody celestialBody;
    private double inclination;
    private double orbitRadius;
    private double position;

    /* ========== PUBLIC ========== */
    public OrbitTask(CelestialBody celestialBody, double orbitRadius, double inclination, double position) {
        System.out.println("Lecê orbitowac " + celestialBody.getName());

        this.celestialBody = celestialBody;
        this.inclination = inclination;
        this.orbitRadius = orbitRadius;
        this.position = position;
    }

    @Override
    public boolean execute(Ship ship) {

        Coords orbittingBodyCoords = celestialBody.getOrbit().getCoords();
        Coords coords = ship.getPosition().getCoords();

        // Duplikacja
        if (CoordsCalculator.equals(coords, orbittingBodyCoords, orbitRadius)) {
            position += PhysicsCalculator.angularVelocity(orbitRadius, ship.getEngine().getVelocity());

            CoordsCalculator.calculateOrbitalPosition(celestialBody.getOrbit(), celestialBody.getSphere());
            CoordsCalculator.translateBy(ship.getPosition(), celestialBody.getOrbit());

            return true;
        }

        System.out.println("Lecê orbitowac " + celestialBody.getName() + " " + celestialBody.getOrbit().getCoords()
                + ", jestem " + ship.getPosition().getCoords());

        boolean rotationChange = rotationChange(ship, orbittingBodyCoords);
        // ship.getControl().setTurbo(rotationChange);
        accelerationChange(ship, orbittingBodyCoords, orbitRadius);

        return false;

    }
}
