package model.ship;

import model.DrawableResolution;
import model.interfaces.Drawable;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Ship extends Drawable {

    private Position position;
    private Engine engine;
    private Control control;
    private Mesh mesh;

    private double size;

    /* ========== PUBLIC ========== */
    public Ship(Position position, Engine engine, Mesh mesh, double size) {
        super(Ship.class, "");

        this.position = position;
        this.engine = engine;
        this.control = new Control();
        this.mesh = mesh;

        this.size = size;
        this.setResolution(DrawableResolution.VERY_FAR);
    }

    /* ========== PRIVATE ========== */
    @JsonIgnore
    private DrawableResolution resolution;

    /* ========== PROPERTIES ========== */
    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public Engine getEngine() {
        return engine;
    }

    public void setEngine(Engine engine) {
        this.engine = engine;
    }

    public Control getControl() {
        return control;
    }

    public void setControl(Control control) {
        this.control = control;
    }

    public Mesh getMesh() {
        return mesh;
    }

    public void setMesh(Mesh mesh) {
        this.mesh = mesh;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public DrawableResolution getResolution() {
        return resolution;
    }

    public void setResolution(DrawableResolution resolution) {
        this.resolution = resolution;
    }
}