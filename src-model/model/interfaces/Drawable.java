package model.interfaces;

import game.architecture.Entity;
import model.DrawableResolution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jogamp.opengl.util.texture.Texture;

/**
 * Determines if given object is visible or not.
 */
public abstract class Drawable extends Entity {

    private String textureFile;

    private boolean visible = true;

    /* ========== PUBLIC ========== */
    @SuppressWarnings("rawtypes")
    public Drawable(Class clazz, String textureFile) {
        super(clazz);
        this.textureFile = textureFile;

        this.resolution = DrawableResolution.VERY_FAR;
    }

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private Texture texture;
    @JsonIgnore
    private DrawableResolution resolution;

    /* ========== PROPERTIES ========== */
    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean isVisible) {
        this.visible = isVisible;
    }

    public String getTextureFile() {
        return textureFile;
    }

    public void setTextureFile(String textureFile) {
        this.textureFile = textureFile;
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public DrawableResolution getResolution() {
        return resolution;
    }

    public void setResolution(DrawableResolution resolution) {
        this.resolution = resolution;
    }
}