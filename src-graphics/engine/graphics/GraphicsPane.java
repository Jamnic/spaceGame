package engine.graphics;

import java.awt.Dimension;

import javax.media.opengl.GL2;
import javax.media.opengl.GLAutoDrawable;
import javax.media.opengl.GLCapabilities;
import javax.media.opengl.GLEventListener;
import javax.media.opengl.awt.GLJPanel;

import model.ship.Engine;
import model.ship.PlayerShip;
import model.ship.Position;
import engine.graphics.listeners.MainKeyListener;
import engine.graphics.listeners.MainMouseListener;
import engine.thread.GameTickThread;
import engine.utils.LightLoader;
import engine.utils.TextLogger;
import game.GameLoader;
import game.GameRunner;
import game.architecture.GameComponentContainer;

/**
 * Class that should be used to render scene.
 */
public final class GraphicsPane extends GLJPanel implements GLEventListener {

    /* ========== PUBLIC ========== */
    public GraphicsPane(GLCapabilities capabilities, PlayerShip ship, Dimension dimension) {
        super(capabilities);

        setPreferredSize(dimension);
        addGLEventListener(this);

        addKeyListener(new MainKeyListener(ship));
        addMouseMotionListener(new MainMouseListener(ship));

        this.ship = ship;
    }

    @Override
    public void init(GLAutoDrawable drawable) {

        final GL2 gl = drawable.getGL().getGL2();

        // MIPMAPPING PARAMETERS
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_GENERATE_MIPMAP, GL2.GL_TRUE);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_BASE_LEVEL, 0);
        gl.glTexParameteri(GL2.GL_TEXTURE_2D, GL2.GL_TEXTURE_MAX_LEVEL, 5);

        gl.glClearColor(0.0f, 0.0f, 0.0f, 0.0f);
        gl.glClearDepth(1f);

        gl.glEnable(GL2.GL_DEPTH_TEST);
        gl.glDepthFunc(GL2.GL_LEQUAL);
        gl.glShadeModel(GL2.GL_SMOOTH);
        gl.glHint(GL2.GL_PERSPECTIVE_CORRECTION_HINT, GL2.GL_NICEST);

        systemLoader.loadSystem(gl, ship);

        createGameTickCounter(ship, this);
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();

        fpsCounter.tick();

        if (shaker.getCooldown())
            perspectiveCooldown();

        gl.glClear(GL2.GL_COLOR_BUFFER_BIT | GL2.GL_DEPTH_BUFFER_BIT);

        rotationOfShip(gl);
        rotationOfShaking(gl);

        LightLoader.sunLight(gl);
        drawSkyBox(gl);
        drawSystem(gl);
        drawMessages();

        gl.glPushMatrix();

        Position position = ship.getPosition();

        gl.glRotated(90, 0, 1, 0);
        gl.glRotated(-position.getRotationY(), 0, 0, 1);
        gl.glRotated(-position.getRotationX(), 0, 1, 0);

//        marker.target(gl, ship);

        gl.glPopMatrix();

        camera.setCamera(gl, perspective);

        drawable.swapBuffers();
    }

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {
    }

    public void shake() {
        if (perspective < 70) {
            perspective += 1;
        }

        shaker.shake();
    }

    public void endOfShake() {
        shaker.endOfShake();
    }

    /* ========== PRIVATE ========== */
    private static final long serialVersionUID = -7419599978736850207L;

    private PlayerShip ship;
    private static double perspective = 60;
    private Shaker shaker = new Shaker();
    private Camera camera = new Camera();
    private TextDrawer textDrawer = new TextDrawer();
    private FPSCounter fpsCounter = new FPSCounter();
    private GameLoader systemLoader = GameComponentContainer.systemLoader;

    private void createGameTickCounter(PlayerShip playerShip, GraphicsPane graphicsPane) {
        GameTickThread gameTickThread = new GameTickThread(graphicsPane);
        gameTickThread.addTickable(systemLoader);

        new Thread(gameTickThread).start();
    }

    private void rotationOfShip(GL2 gl) {
        Position position = ship.getPosition();

        gl.glRotated(position.getRotationY(), 0, 0, 1);
        gl.glRotated(position.getRotationX(), 0, 1, 0);
    }

    private void rotationOfShaking(GL2 gl) {
        gl.glRotated(shaker.getXShake(), 0, 1, 0);
        gl.glRotated(shaker.getYShake(), 0, 0, 1);
    }

    private void drawSystem(GL2 gl) {
        gl.glPushMatrix();

        Position position = ship.getPosition();
        double x = position.getCoords().getX();
        double y = position.getCoords().getY();
        double z = position.getCoords().getZ();

        gl.glTranslated(-x, -y, -z);
        systemLoader.drawObjects(gl);

        gl.glPopMatrix();
    }

    private void drawSkyBox(GL2 gl) {
        gl.glPushMatrix();

        gl.glRotated(90, 1, 0, 0); // Because the milky way graphic is rotated initially
        systemLoader.drawCurrentSkyBox(gl);

        gl.glPopMatrix();
    }

    private void drawMessages() {
        Position position = ship.getPosition();
        Engine engine = ship.getEngine();

        textDrawer.text2D("FPS: " + fpsCounter.fps(), 0, GameRunner.FRAME_HEIGHT - 10);
        textDrawer.text2D("Velocity: " + TextLogger.twoDecimal(engine.getVelocity() * 100), 0,
                GameRunner.FRAME_HEIGHT - 20);
        textDrawer.text2D("Acceleration: " + TextLogger.twoDecimal(engine.getAcceleration() * 100), 0,
                GameRunner.FRAME_HEIGHT - 30);
        textDrawer.text2D(
                "X: " + TextLogger.twoDecimal(position.getCoords().getX()) + ", Y: "
                        + TextLogger.twoDecimal(position.getCoords().getY()) + ", Z: "
                        + TextLogger.twoDecimal(position.getCoords().getZ()), 0, GameRunner.FRAME_HEIGHT - 40);
    }

    private void perspectiveCooldown() {
        if (perspective > 60) {
            perspective -= 1.0;
        } else {
            shaker.setCooldown(false);
        }
    }
}