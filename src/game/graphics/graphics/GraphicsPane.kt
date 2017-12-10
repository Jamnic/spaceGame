package game.graphics.graphics

import game.GameLoader
import game.architecture.GameComponentContainer
import game.engine.thread.GameTickThread
import game.engine.utils.TextLogger
import game.graphics.drawers.OpenGL
import game.graphics.graphics.listeners.MainKeyListener
import game.graphics.graphics.listeners.MainMouseListener
import game.graphics.graphics.window.GameWindow.FRAME_HEIGHT
import game.graphics.graphics.window.GameWindow.FRAME_WIDTH
import game.model.Coords
import game.model.ship.PlayerShip
import java.awt.Dimension
import javax.media.opengl.GL2
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.GLCapabilities
import javax.media.opengl.GLEventListener
import javax.media.opengl.awt.GLJPanel

class GraphicsPane(
        capabilities: GLCapabilities
) : GLJPanel(capabilities), GLEventListener {

    private val ship = PlayerShip(Coords(-1520.0, 0.0, 0.0))
    private var perspective = 60.0
    private val shaker = Shaker()
    private val textDrawer = TextDrawer()
    private val fpsCounter = FPSCounter()
    private val systemLoader = GameLoader(ship)

    init {
        preferredSize = Dimension(FRAME_WIDTH, FRAME_HEIGHT)
        addGLEventListener(this)

        addKeyListener(MainKeyListener(ship))
        addMouseMotionListener(MainMouseListener(ship))
    }

    override fun init(drawable: GLAutoDrawable) {

        val gl = drawable.gl.gL2

        GameComponentContainer.shipManager.playerShip = ship

        // MIPMAPPING PARAMETERS
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_GENERATE_MIPMAP, GL2.GL_TRUE)
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_BASE_LEVEL, 0)
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAX_LEVEL, 5)

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f)
        gl.glClearDepth(1.0)

        gl.glEnable(GL2.GL_DEPTH_TEST)
        gl.glDepthFunc(GL2.GL_LEQUAL)
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST)

        systemLoader.loadSystem(ship)

        createGameTickCounter(this)
    }

    override fun display(drawable: GLAutoDrawable) {
        val openGL = OpenGL(drawable.gl.gL2)

        fpsCounter.tick()

        if (shaker.cooldown)
            perspectiveCoolDown()

        if (ship.control.isTurbo) {
            shake()
        }

        openGL.clear()

        openGL.rotate(ship.position)
        openGL.shake(shaker)

        ship.starSystem?.draw(openGL, ship)
        drawMessages()

        openGL.camera(perspective)


        drawable.swapBuffers()
    }

    override fun reshape(drawable: GLAutoDrawable, x: Int, y: Int, width: Int, height: Int) {}

    override fun dispose(drawable: GLAutoDrawable) {}

    private fun shake() {
        if (perspective < 40)
            perspective += 1.0

        shaker.shake()
    }

    private fun createGameTickCounter(graphicsPane: GraphicsPane) {
        val gameTickThread = GameTickThread(graphicsPane)
        gameTickThread.addTickable(systemLoader)

        Thread(gameTickThread).start()
    }

    private fun drawMessages() {
        val engine = ship.engine

        textDrawer.text2D("FPS: " + fpsCounter.fps(), 0, FRAME_HEIGHT - 10)
        textDrawer.text2D("Velocity: " + TextLogger.twoDecimal(engine.velocity), 0, FRAME_HEIGHT - 20)
        textDrawer.text2D("Acceleration: " + TextLogger.twoDecimal(engine.acceleration), 0, FRAME_HEIGHT - 30)
        textDrawer.text2D(ship.position.coords.toString(), 0, FRAME_HEIGHT - 40)
        textDrawer.text2D(ship.position.coords.distanceTo(ship.starSystem?.celestialBodies?.find { it.name == "VenusClouds" }?.orbit?.coords!!).toString(), 0, FRAME_HEIGHT - 50)
    }

    private fun perspectiveCoolDown() {
        if (perspective > 30)
            perspective -= 1.0
        else
            shaker.cooldown = false
    }
}