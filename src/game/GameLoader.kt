package game

import game.architecture.GameComponentContainer
import game.creator.ApolloSystemCreator
import game.creator.SolarSystemCreator
import game.engine.thread.Tickable
import game.engine.utils.MeshLoader
import game.model.ship.Mesh
import game.model.ship.PlayerShip
import game.model.system.StarSystem
import java.util.*

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
}
