package engine.graphics

import engine.graphics.listeners.MainKeyListener
import engine.graphics.listeners.MainMouseListener
import engine.graphics.window.GameWindow.FRAME_HEIGHT
import engine.graphics.window.GameWindow.FRAME_WIDTH
import engine.thread.GameTickThread
import engine.utils.LightLoader
import engine.utils.TextLogger
import game.GameLoader
import model.Coords
import model.ship.PlayerShip
import java.awt.Dimension
import javax.media.opengl.GL2
import javax.media.opengl.GLAutoDrawable
import javax.media.opengl.GLCapabilities
import javax.media.opengl.GLEventListener
import javax.media.opengl.awt.GLJPanel

class GraphicsPane(
        capabilities: GLCapabilities
) : GLJPanel(capabilities), GLEventListener {

    private val ship = PlayerShip(Coords(-1000.0, 0.0, 0.0))
    private var perspective = 60.0
    private val shaker = Shaker()
    private val camera = Camera()
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
        val gl = drawable.gl.gL2

        fpsCounter.tick()

        if (shaker.cooldown)
            perspectiveCooldown()

        if (ship.control.isTurbo) {
            shake()
        }

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT or GL2.GL_DEPTH_BUFFER_BIT)

        rotationOfShip(gl)
        rotationOfShaking(gl)

        drawSkyBox(gl, ship)
        LightLoader.sunLight(gl)
        drawSystem(gl)
        drawMessages()

        camera.setCamera(gl, perspective)

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

    private fun rotationOfShip(gl: GL2) {
        val position = ship.position

        gl.glRotated(position.rotationY.value().toDouble(), 0.0, 0.0, 1.0)
        gl.glRotated(position.rotationX.value().toDouble(), 0.0, 1.0, 0.0)
    }

    private fun rotationOfShaking(gl: GL2) {
        gl.glRotated(shaker.xShake, 0.0, 1.0, 0.0)
        gl.glRotated(shaker.yShake, 0.0, 0.0, 1.0)
    }

    private fun drawSystem(gl: GL2) {
        gl.glPushMatrix()

        val position = ship.position
        val x = position.coords.x
        val y = position.coords.y
        val z = position.coords.z

        gl.glTranslated(-x, -y, -z)
        systemLoader.drawObjects(gl, ship)

        gl.glPopMatrix()
    }

    private fun drawSkyBox(gl: GL2, playerShip: PlayerShip) {
        gl.glPushMatrix()

        gl.glRotated(90.0, 1.0, 0.0, 0.0) // Because the milky way graphic is rotated initially
        systemLoader.drawCurrentSkyBox(gl, playerShip)

        gl.glPopMatrix()
    }

    private fun drawMessages() {
        val position = ship.position
        val engine = ship.engine

        textDrawer.text2D("FPS: " + fpsCounter.fps(), 0, FRAME_HEIGHT - 10)
        textDrawer.text2D("Velocity: " + TextLogger.twoDecimal((engine.velocity * 100).toDouble()), 0, FRAME_HEIGHT - 20)
        textDrawer
                .text2D("Acceleration: " + TextLogger.twoDecimal((engine.acceleration * 100).toDouble()), 0, FRAME_HEIGHT - 30)
        textDrawer.text2D(
                "X: " + TextLogger.twoDecimal(position.coords.x) + ", Y: " + TextLogger.twoDecimal(position.coords.y)
                        + ", Z: " + TextLogger.twoDecimal(position.coords.z), 0, FRAME_HEIGHT - 40)
    }

    private fun perspectiveCooldown() {
        if (perspective > 30)
            perspective -= 1.0
        else
            shaker.cooldown = false
    }
}