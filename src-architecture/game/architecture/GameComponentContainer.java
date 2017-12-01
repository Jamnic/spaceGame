package game.architecture;

import javax.media.opengl.glu.GLU;

import repositories.CelestialBodyRepository;
import repositories.MeshRepository;
import repositories.ShipRepository;
import repositories.StarSystemRepository;

import com.jogamp.opengl.util.gl2.GLUT;

import engine.components.drawers.CelestialBodyDrawer;
import engine.components.drawers.ShipDrawer;
import engine.graphics.holders.GLUHolder;
import engine.graphics.holders.GLUTHolder;
import engine.managers.CelestialBodyManager;
import engine.managers.ShipManager;
import engine.managers.StarSystemManager;
import game.GameLoader;

public abstract class GameComponentContainer {

    protected static ShipManager shipManager = new ShipManager();
    protected static StarSystemManager starSystemManager = new StarSystemManager();
    protected static CelestialBodyManager celestialBodyManager = new CelestialBodyManager();

    protected static CelestialBodyDrawer celestialBodyDrawer = new CelestialBodyDrawer();
    protected static ShipDrawer shipDrawer = new ShipDrawer();

    public static GameLoader systemLoader = new GameLoader();

    public static CelestialBodyRepository celestialBodyRepository = new CelestialBodyRepository();
    public static StarSystemRepository starSystemRepository = new StarSystemRepository();
    public static ShipRepository shipRepository = new ShipRepository();
    public static MeshRepository meshRepository = new MeshRepository();

    protected static final GLU glu = GLUHolder.GLU;
    protected static final GLUT glut = GLUTHolder.GLUT;
}
