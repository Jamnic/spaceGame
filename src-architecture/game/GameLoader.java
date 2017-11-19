package game;

import engine.thread.Tickable;
import game.architecture.GameComponentContainer;
import game.creator.ApolloSystemCreator;
import game.creator.SolarSystemCreator;

import java.io.IOException;
import java.util.LinkedList;
import java.util.List;

import javax.media.opengl.GL2;

import model.ship.PlayerShip;
import model.system.StarSystem;

public class GameLoader extends GameComponentContainer implements Tickable {

	private List<StarSystem> starSystems;
	private PlayerShip playerShip;

	@Override
	public void tick() {
		GameComponentContainer.starSystemManager.tick(starSystems, playerShip);
	}

	public void loadSystem(GL2 gl, PlayerShip ship) {

		System.out.println("Initializing...");
		starSystems = new LinkedList<StarSystem>();
		StarSystem solarSystem = null;
		StarSystem apolloSystem = null;

		// List<Mesh> meshes = new ArrayList<Mesh>();

		System.out.println("Loading Meshes...");

		// meshes.add(new MeshDrawer(gl, MeshLoader.loadFromFile("wraith",
		// "wraith")));
		// meshes.add(MeshLoader.loadFromFile("exxon", "Alicia"));
		// meshes.add(MeshLoader.loadFromFile("alien",
		// "alien-mothership-green"));

		// meshRepository.add(meshes);

		// GameComponentContainer.meshRepository.add(meshes);

		try {
			System.out.println("Loading Solar system...");
			solarSystem = SolarSystemCreator.create(gl);
			solarSystem.setVisible(true);

			starSystems.add(solarSystem);
			celestialBodyRepository.add(solarSystem.getCelestialBodies());
			starSystemRepository.add(solarSystem);

			System.out.println("Loading Apollo system...");
			apolloSystem = ApolloSystemCreator.create(gl);
			apolloSystem.setVisible(false);

			starSystems.add(apolloSystem);
			celestialBodyRepository.add(apolloSystem.getCelestialBodies());
			starSystemRepository.add(apolloSystem);
		} catch (IOException e) {
			e.printStackTrace();
		}

		this.playerShip = ship;

		System.out.println("Done!");
	}

	public void drawCurrentSkyBox(GL2 gl) {
		for (StarSystem starSystem : starSystems) {
			if (starSystem.isVisible()) {
				GameComponentContainer.skyBoxDrawer.draw(gl,
						starSystem.getSkyBox());
				return;
			}
		}
	}

	public void drawObjects(GL2 gl) {
		GameComponentContainer.starSystemManager.draw(gl, starSystems);
	}
}
