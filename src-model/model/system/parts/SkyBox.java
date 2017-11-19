package model.system.parts;

import model.DrawableResolution;
import model.celestials.Sphere;
import model.interfaces.Drawable;
import model.system.StarSystem;

import com.fasterxml.jackson.annotation.JsonCreator;

/**
 * Class used to draw Sky Box for given {@link StarSystem} - every system has different star constellations. <br>
 * <br>
 * <b>Class responsibilities</b>
 * <ul>
 * <li>Containing the Sky Box data.</li>
 * <li>Enables to draw Sky Box.
 * </ul>
 * 
 * @author Jamnic
 */
public class SkyBox extends Drawable {

    private static final DrawableResolution SKY_BOX_RESOLUTION = DrawableResolution.MEDIUM;

    /* ========== PUBLIC ========== */
    @JsonCreator
    public SkyBox(Sphere skyBox) {
        super(Sphere.class, skyBox.getTextureFile());

        this.skyBox = skyBox;
        this.skyBox.setResolution(SKY_BOX_RESOLUTION);
    }

    /* ========== PRIVATE ========== */
    private Sphere skyBox;

    /* ========== PROPERTIES ========== */
    public Sphere getSkyBox() {
        return skyBox;
    }

    public void setSkyBox(Sphere skyBox) {
        this.skyBox = skyBox;
    }
}