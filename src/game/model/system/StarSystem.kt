package game.model.system

import game.architecture.Entity
import game.graphics.drawers.OpenGL
import game.model.celestials.CelestialBody
import game.model.ship.PlayerShip
import game.model.ship.Ship
import game.model.system.parts.SkyBox
import javax.media.opengl.GL2

class StarSystem(
        private var skyBox: SkyBox,
        var ships: List<Ship>,
        var celestialBodies: List<CelestialBody>
) : Entity(StarSystem::class.java) {

    fun draw(openGL: OpenGL, playerShip: PlayerShip) {
        drawCurrentSkyBox(openGL)
        drawSystemElements(openGL, playerShip)
    }

    private fun drawSystemElements(openGL: OpenGL, playerShip: PlayerShip) {
        openGL.pushMatrix()

        openGL.translate(-playerShip.position.coords)
        drawObjects(openGL.gl)

        openGL.popMatrix()
    }


    private fun drawCurrentSkyBox(openGL: OpenGL) {
        skyBox.draw(openGL)
    }

    private fun drawObjects(gl: GL2) {
        celestialBodies.forEach { it.draw(gl) }
    }
}