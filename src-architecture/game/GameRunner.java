package game;

import java.awt.Dimension;

import javax.media.opengl.GLCapabilities;

import model.Coords;
import model.ship.PlayerShip;
import engine.graphics.GraphicsPane;
import engine.graphics.window.GameWindow;

/**
 * Class that should be used only to run game. It connects all it's components and launches game processes.<br>
 * <br>
 * <b>Class responsibilities</b>
 * <ul>
 * <li>Connecting the game components.</li>
 * <li>Launching all the game processes.</li>
 * </ul>
 * 
 * @author Jamnic
 */
public final class GameRunner {

	public static final int FRAME_WIDTH = 1280;
	public static final int FRAME_HEIGHT = 768;
	private static final String TITLE = "Space game";

	/* ========== PUBLIC ========== */
	/**
	 * Starts the game.
	 * 
	 * @param args
	 *            - no arguments are parsed at the moment.
	 */
	public static void main(String[] args) {
		new GameRunner();
	}

	/* ========== PRIVATE ========== */
	private GameRunner() {
		Dimension dimension = new Dimension(FRAME_WIDTH, FRAME_HEIGHT);

		PlayerShip ship = new PlayerShip(new Coords(10000, 0, 0));
		GraphicsPane graphicsPane = createGraphicsPaneComponent(ship, dimension);
		ship.setPane(graphicsPane);

		new GameWindow(TITLE, graphicsPane);
	}

	private GraphicsPane createGraphicsPaneComponent(PlayerShip ship, Dimension dimension) {
		GLCapabilities capabilities = new GLCapabilities(null);
		capabilities.setDoubleBuffered(true);

		GraphicsPane graphicsPane = new GraphicsPane(capabilities, ship, dimension);
		graphicsPane.requestFocusInWindow();

		return graphicsPane;
	}
}