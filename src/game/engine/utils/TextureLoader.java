package game.engine.utils;

import com.jogamp.opengl.util.texture.Texture;
import com.jogamp.opengl.util.texture.TextureIO;

import javax.media.opengl.GL2;
import java.io.File;
import java.io.IOException;

/**
 * Utility class that creates texture from given filepath and {@link GL2}.
 */
public final class TextureLoader {

    /* ========== PUBLIC ========== */

    /* ========== PRIVATE ========== */
    private TextureLoader() {
    }

    /**
     * Loads PNG texture from given {@link String} fileName and {@link GL2} gl profile from res/textures/ directory. May
     * throw an exception when given file does not exist.
     *
     * @throws RuntimeException
     */
    public static Texture getTexture(GL2 gl, String fileName) {
        try {
            return TextureIO.newTexture(TextureIO.newTextureData(gl.getGLProfile(), new File("res/textures/" + fileName
                    + ".png"), true, "png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}