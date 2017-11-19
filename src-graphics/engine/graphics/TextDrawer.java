package engine.graphics;

import game.GameRunner;

import java.awt.Color;
import java.awt.Font;

import com.jogamp.opengl.util.awt.TextRenderer;

public class TextDrawer {

    private final TextRenderer textr = new TextRenderer(new Font("SansSerif", Font.PLAIN, 12));

    public void text2D(String text, int x, int y) {
        textr.setColor(Color.WHITE);
        textr.beginRendering(GameRunner.FRAME_WIDTH, GameRunner.FRAME_HEIGHT);
        textr.draw(text, x, y);
        textr.endRendering();
    }
}
