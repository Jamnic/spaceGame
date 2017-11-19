package model.celestials.types;

import model.celestials.CelestialBody;
import model.celestials.Orbit;
import model.celestials.Sphere;


public class Wormhole extends CelestialBody {

    private long systemFromId;
    private long systemToId;

    public Wormhole(String name, Orbit orbit, Sphere sphere, long systemFromId, long systemToId) {
        super(name, orbit, sphere);

        this.systemFromId = systemFromId;
        this.systemToId = systemToId;
    }

    public long getSystemFromId() {
        return systemFromId;
    }

    public void setSystemFromId(long systemFromId) {
        this.systemFromId = systemFromId;
    }

    public long getSystemToId() {
        return systemToId;
    }

    public void setSystemToId(long systemToId) {
        this.systemToId = systemToId;
    }
}