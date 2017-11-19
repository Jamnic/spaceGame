package model.system.parts;

import model.celestials.parts.Sphere;
import model.interfaces.Drawable;
import model.system.StarSystem;
import model.type.DrawableResolution;

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
public class SkyBox implements Drawable {

    private static final DrawableResolution SKY_BOX_RESOLUTION = DrawableResolution.MEDIUM;

    /* ========== PUBLIC ========== */
    public SkyBox() {
    }

    public SkyBox(Sphere skyBox) {
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