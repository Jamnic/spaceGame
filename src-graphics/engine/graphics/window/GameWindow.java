package engine.graphics.window;

import java.awt.Container;

import javax.swing.JFrame;

/**
 * Component class used to render the game window and to capture mouse motion.<br>
 * <br>
 * <b>Class responsibilites</b>
 * <ul>
 * <li>Capturing mouse motion.</li>
 * <li>Preparing the parameters of the game window.</li>
 * </ul>
 * 
 * @author Jamnic
 */
public class GameWindow extends JFrame {

	private static final boolean IS_RESIZEABLE = false;
	private static final boolean IS_VISIBLE = true;
	private static final int X_LOCATION = 50;
	private static final int Y_LOCATION = 50;

	/* ========== PUBLIC ========== */
	/**
	 * Instantinates the {@link GameWindow} class with given parameters and sets constant values.
	 * 
	 * @param windowTitle - title of the game window.
	 * @param graphicsPane - {@link Container} where graphics are drawn. {@link GameWindow} must have it to display in
	 * window.
	 */
	public GameWindow(String windowTitle, Container graphicsPane) {
		super(windowTitle);

		setContentPane(graphicsPane);

		setWindowParameters();
		pack();
	}

	/* ========== PRIVATE ========== */
	private static final long serialVersionUID = 9102033512048383786L;

	/**
	 * Method sets the window parameters with constant field values.
	 */
	private void setWindowParameters() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocation(X_LOCATION, Y_LOCATION);
		setResizable(IS_RESIZEABLE);
		setVisible(IS_VISIBLE);
	}
}