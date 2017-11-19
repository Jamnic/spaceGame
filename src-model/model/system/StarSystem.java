package model.system;

import java.util.List;

import model.celestials.CelestialBody;
import model.ship.Ship;
import model.system.parts.SkyBox;

import com.fasterxml.jackson.annotation.JsonIgnore;

import game.architecture.Entity;

/**
 * Entity that contains whole information about given {@link StarSystem}.
 */
public class StarSystem extends Entity {

    private SkyBox skyBox;
    private List<Ship> ships;
    private List<CelestialBody> celestialBodies;

    /* ========== PUBLIC ========== */
    public StarSystem(SkyBox skyBox, List<Ship> ships, List<CelestialBody> celestialBodies) {
        super(StarSystem.class);

        this.skyBox = skyBox;
        this.ships = ships;
        this.celestialBodies = celestialBodies;
    }

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private boolean isVisible;

    /* ========== PROPERTIES ========== */
    public SkyBox getSkyBox() {
        return skyBox;
    }

    public void setSkyBox(SkyBox skyBox) {
        this.skyBox = skyBox;
    }

    public List<Ship> getShips() {
        return ships;
    }

    public void setShips(List<Ship> ships) {
        this.ships = ships;
    }

    public List<CelestialBody> getCelestialBodies() {
        return celestialBodies;
    }

    public void setCelestialBodies(List<CelestialBody> celestialBodies) {
        this.celestialBodies = celestialBodies;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        this.isVisible = visible;
    }
}