package model.system.parts;

public class AsteroidBelt {

	private double radius;

	private double thickness;
	
	public AsteroidBelt(double radius, double thickness) {
		this.radius = radius;
		this.thickness = thickness;
	}

	public double getRadius() {
		return radius;
	}

	public void setRadius(double radius) {
		this.radius = radius;
	}

	public double getThickness() {
		return thickness;
	}

	public void setThickness(double thickness) {
		this.thickness = thickness;
	}
}