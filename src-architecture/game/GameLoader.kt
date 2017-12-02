package game

import engine.thread.Tickable
import engine.utils.MeshLoader
import game.architecture.GameComponentContainer
import game.creator.ApolloSystemCreator
import game.creator.SolarSystemCreator
import model.ship.Mesh
import model.ship.PlayerShip
import model.system.StarSystem
import java.util.*
import javax.media.opengl.GL2

class GameLoader(
        private var playerShip: PlayerShip,
        private var starSystems: MutableList<StarSystem> = mutableListOf()
) : GameComponentContainer(), Tickable {

    override fun tick() {
        val system = playerShip.starSystem

        for (celestialBody in system!!.celestialBodies) {
            celestialBody.tick(playerShip)
        }

        GameComponentContainer.shipManager.tick(system.ships, playerShip)
    }

    fun loadSystem(playerShip: PlayerShip) {
        println("Initializing...")

        println("Loading Meshes...")
        val meshes = ArrayList<Mesh>()
        meshes.add(MeshLoader.loadFromFile("exxon", "alien-mothership-green"))
        meshes.add(MeshLoader.loadFromFile("alien", "alien-mothership-green"))
        GameComponentContainer.meshRepository.add(meshes)
        GameComponentContainer.meshRepository.add(meshes)

        println("Loading Solar system...")
        val solarSystem: StarSystem = SolarSystemCreator().create()
        starSystems.add(solarSystem)

        GameComponentContainer.celestialBodyRepository.add(solarSystem.celestialBodies)
        GameComponentContainer.starSystemRepository.add(solarSystem)

        println("Loading Apollo system...")
        val apolloSystem: StarSystem = ApolloSystemCreator().create()
        starSystems.add(apolloSystem)

        GameComponentContainer.celestialBodyRepository.add(apolloSystem.celestialBodies)
        GameComponentContainer.starSystemRepository.add(apolloSystem)

        this.playerShip.starSystem = solarSystem

        println("Done!")
    }

    fun drawCurrentSkyBox(gl: GL2, playerShip: PlayerShip) {
        val system = playerShip.starSystem
        system!!.skyBox.draw(gl)
    }

    fun drawObjects(gl: GL2, playerShip: PlayerShip) {
        val system = playerShip.starSystem

        for (body in system!!.celestialBodies) {
            body.draw(gl)
        }

        GameComponentContainer.shipManager.draw(gl, system.ships)
    }
}
