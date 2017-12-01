package game;

import engine.thread.Tickable;
import engine.utils.MeshLoader;
import game.architecture.GameComponentContainer;
import game.creator.ApolloSystemCreator;
import game.creator.SolarSystemCreator;
import model.ship.Mesh;
import model.ship.PlayerShip;
import model.system.StarSystem;

import javax.media.opengl.GL2;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GameLoader extends GameComponentContainer implements Tickable {

    private List<StarSystem> starSystems;
    private PlayerShip playerShip;

    @Override
    public void tick() {
        GameComponentContainer.starSystemManager.tick(playerShip);
    }

    public void loadSystem(PlayerShip playerShip) {

        System.out.println("Initializing...");
        starSystems = new LinkedList<>();
        StarSystem solarSystem = null;
        StarSystem apolloSystem;

        List<Mesh> meshes = new ArrayList<Mesh>();

        System.out.println("Loading Meshes...");

        // meshes.add(new MeshDrawer(gl, MeshLoader.loadFromFile("wraith", "wraith")));
        // meshes.add(MeshLoader.loadFromFile("exxon", "Alicia"));
        meshes.add(MeshLoader.loadFromFile("alien", "alien-mothership-green"));

        meshRepository.add(meshes);

        GameComponentContainer.meshRepository.add(meshes);

        try {
            System.out.println("Loading Solar system...");
            solarSystem = new SolarSystemCreator().create();
            solarSystem.setVisible(true);

            starSystems.add(solarSystem);
            celestialBodyRepository.add(solarSystem.getCelestialBodies());
            starSystemRepository.add(solarSystem);

            System.out.println("Loading Apollo system...");
            apolloSystem = new ApolloSystemCreator().create();
            apolloSystem.setVisible(false);

            starSystems.add(apolloSystem);
            celestialBodyRepository.add(apolloSystem.getCelestialBodies());
            starSystemRepository.add(apolloSystem);
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.playerShip = playerShip;
        this.playerShip.setStarSystem(solarSystem);

        System.out.println("Done!");
    }

    public void drawCurrentSkyBox(GL2 gl, PlayerShip playerShip) {
        StarSystem system = playerShip.getStarSystem();
        system.getSkyBox().draw(gl);
    }

    public void drawObjects(GL2 gl, PlayerShip playerShip) {
        GameComponentContainer.starSystemManager.draw(gl, playerShip);
    }
}
