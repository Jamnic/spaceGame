package model.celestials.parts;

import model.celestials.CelestialBody;
import model.interfaces.Drawable;
import model.type.DrawableResolution;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jogamp.opengl.util.texture.Texture;

/**
 * Object which stores the clouds parameters for {@link Sphere} part of {@link CelestialBody} entity.
 */
public class Clouds implements Drawable {

    private String textureFile;
    private double radius;

    /* ========== PUBLIC ========== */
    public Clouds(String textureFile, double radius) {
        this.textureFile = textureFile;
        this.radius = radius;
        
        this.resolution = DrawableResolution.VERY_FAR;
    }

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private Texture texture;
    @JsonIgnore
    private DrawableResolution resolution;

    /* ========== PROPERTIES ========== */
    public String getTextureFile() {
        return textureFile;
    }

    public void setTextureFile(String textureFile) {
        this.textureFile = textureFile;
    }

    public double getRadius() {
        return radius;
    }

    public void setRadius(double radius) {
        this.radius = radius;
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