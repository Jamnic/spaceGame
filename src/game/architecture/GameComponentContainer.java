package game.architecture;

import game.engine.managers.ShipManager;
import game.graphics.drawers.ShipDrawer;
import game.model.repositories.CelestialBodyRepository;
import game.model.repositories.MeshRepository;
import game.model.repositories.ShipRepository;
import game.model.repositories.StarSystemRepository;

public abstract class GameComponentContainer {

    public static ShipManager shipManager = new ShipManager();

    public static ShipDrawer shipDrawer = new ShipDrawer();

    public static CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository();
    public static StarSystemRepository starSystemRepository = new StarSystemRepository();
    public static ShipRepository shipRepository = new ShipRepository();
    public static MeshRepository meshRepository = new MeshRepository();
}
