package model.celestials;


import model.celestials.parts.Orbit;
import model.celestials.parts.Sphere;

public class Wormhole extends CelestialBody {

    private long systemFromId;
    private long systemToId;

    public Wormhole(String name, Orbit orbit, Sphere sphere, long systemFromId, long systemToId) {
        super(name, orbit, sphere, CelestialBodyType.WORMHOLE);

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