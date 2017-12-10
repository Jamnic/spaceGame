package game.graphics.graphics;

import com.jogamp.opengl.util.awt.TextRenderer;

import java.awt.*;

import static game.graphics.graphics.window.GameWindow.FRAME_HEIGHT;
import static game.graphics.graphics.window.GameWindow.FRAME_WIDTH;

public class TextDrawer {

    private final TextRenderer textr = new TextRenderer(new Font("SansSerif", Font.PLAIN, 12));

    public void text2D(String text, int x, int y) {
        textr.setColor(Color.WHITE);
        textr.beginRendering(FRAME_WIDTH, FRAME_HEIGHT);
        textr.draw(text, x, y);
        textr.endRendering();
    }
}
