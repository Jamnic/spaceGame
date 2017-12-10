package game.graphics.graphics.window;

import game.graphics.graphics.GraphicsPane;

import javax.media.opengl.GLCapabilities;
import javax.swing.*;

public class GameWindow extends JFrame {

    public static final int FRAME_WIDTH = 1200;
    public static final int FRAME_HEIGHT = 700;

    private static final boolean IS_RESIZEABLE = false;
    private static final boolean IS_VISIBLE = true;
    private static final int X_LOCATION = 50;
    private static final int Y_LOCATION = 50;
    private static final String TITLE = "Space game";

    public GameWindow() {
        super(TITLE);
        setContentPane(createGraphicsPaneComponent());
        setWindowParameters();
        pack();
    }

    private GraphicsPane createGraphicsPaneComponent() {
        GLCapabilities capabilities = new GLCapabilities(null);
        capabilities.setDoubleBuffered(true);

        GraphicsPane graphicsPane = new GraphicsPane(capabilities);
        graphicsPane.requestFocusInWindow();

        return graphicsPane;
    }

    private void setWindowParameters() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setLocation(X_LOCATION, Y_LOCATION);
        setResizable(IS_RESIZEABLE);
        setVisible(IS_VISIBLE);
    }
}