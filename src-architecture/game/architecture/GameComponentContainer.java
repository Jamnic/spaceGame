package game.architecture;

import repositories.CelestialBodyRepository;
import repositories.MeshRepository;
import repositories.ShipRepository;
import repositories.StarSystemRepository;

import engine.components.drawers.ShipDrawer;
import engine.managers.ShipManager;
import game.GameLoader;

public abstract class GameComponentContainer {

    protected static ShipManager shipManager = new ShipManager();

    protected static ShipDrawer shipDrawer = new ShipDrawer();

    public static CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository();
    public static StarSystemRepository starSystemRepository = new StarSystemRepository();
    public static ShipRepository shipRepository = new ShipRepository();
    public static MeshRepository meshRepository = new MeshRepository();
}
