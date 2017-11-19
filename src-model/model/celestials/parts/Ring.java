package model.celestials.parts;

import model.celestials.CelestialBody;
import model.interfaces.Drawable;
import model.type.DrawableResolution;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.jogamp.opengl.util.texture.Texture;

/**
 * Object which stores the ring parameters for {@link Sphere} part of {@link CelestialBody} entity.
 */
public class Ring implements Drawable {

    private String textureFile;
    private double innerRadius;
    private double outerRadius;

    /* ========== PUBLIC ========== */
    @JsonCreator
    public Ring(String textureFile, double innerRadius, double outerRadius) {
        this.textureFile = textureFile;
        this.innerRadius = innerRadius;
        this.outerRadius = outerRadius;

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

    public double getInnerRadius() {
        return innerRadius;
    }

    public void setInnerRadius(double innerRadius) {
        this.innerRadius = innerRadius;
    }

    public double getOuterRadius() {
        return outerRadius;
    }

    public void setOuterRadius(double outerRadius) {
        this.outerRadius = outerRadius;
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