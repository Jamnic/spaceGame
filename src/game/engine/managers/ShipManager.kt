package game.engine.managers

import game.architecture.Constants
import game.architecture.GameComponentContainer
import game.architecture.Manager
import game.engine.calculators.VelocityCalculator
import game.engine.math.Distance
import game.engine.math.Radius
import game.engine.math.ScaleUnit
import game.model.ship.PlayerShip
import game.model.ship.Ship
import game.model.ship.parts.Engine
import game.model.ship.parts.Position
import game.model.type.DrawableResolution
import javax.media.opengl.GL2

/**
 * Manages the [Ship] objects. Updates positions and draws them.
 */
class ShipManager : Manager<Ship>() {

    var playerShip: PlayerShip? = null

    /* ========== PUBLIC ========== */
    /**
     * Draws the given [Ship] list using given [GL2] graphic context.
     *
     * @param gl - [GL2] graphical context of drawing.
     * @param ships - [List] of [Ship]s to be drawn.
     */
    fun draw(gl: GL2, ships: List<Ship>) {
        for (ship in ships) {
            GameComponentContainer.shipDrawer.draw(gl, ship)
        }
    }

    /**
     * Executes [Ship]s tasks, checks collisions and resolution and updates position.
     *
     * @param ships - [List] of [Ship]s to process.
     * @param playerShip - used to calculate resolution and collisions.
     */
    fun tick(ships: List<Ship>, playerShip: PlayerShip) {
        updateCalculations(playerShip)

        for (ship in ships) {
            updateDistance(ship, playerShip)
            updateCalculations(ship)
        }
    }

    /* ========== PRIVATE ========== */
    private fun updateCalculations(ship: Ship) {

        // Parameters
        val position = ship.position
        val control = ship.control
        val engine = ship.engine

        // Calculations
        rotateShip(position, engine)
        VelocityCalculator.setVelocity(engine, control)
        setShipPosition(position, engine)
        VelocityCalculator.decreaseAcceleration(engine)
    }

    fun rotateShip(position: Position, engine: Engine) {
        position.rotationX = position.rotationX.plus(engine.rotationXChange)
        position.rotationY = position.rotationY.plus(engine.rotationYChange)
    }

    private fun updateDistance(ship: Ship, playerShip: PlayerShip) {

        // Parameters
        val playerShipCoords = playerShip.position.coords
        val shipCoords = ship.position.coords

        // Distance
        val distance = shipCoords.distanceTo(playerShipCoords)
        setResolution(ship, distance)

        // TODO collisions
    }

    private fun setResolution(ship: Ship, distance: Double) {
        val radius = Radius((ship.size * Constants.SHIP_SIZE_MAGNIFIER), ScaleUnit.KM)
        val distance1 = Distance(distance, game.engine.math.ScaleUnit.AU)
        val resolution = DrawableResolution.determineResolution(distance1.div(radius))
        ship.resolution = resolution
    }

    private fun setShipPosition(position: Position, engine: Engine) {
        val velocity = engine.velocity
        val coords = position.coords

        val rotationX = position.rotationX.toRadians()
        val rotationY = position.rotationY.toRadians().negate()

        coords.x = coords.x + velocity * Math.cos(rotationX.value().toDouble()).toFloat()
        coords.y = coords.y + velocity * Math.sin(rotationY.value().toDouble()).toFloat()
        coords.z = coords.z + velocity * Math.sin(rotationX.value().toDouble()).toFloat()
    }
}