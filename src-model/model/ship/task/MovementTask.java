package model.ship.task;

import model.Coords;
import model.ship.Ship;

/**
 * Marks destination for {@link Ship}.
 */
public class MovementTask extends Task {

	private static final double PRECISION = 1000;
	private Coords destination;

	/* ========== PUBLIC ========== */
	public MovementTask(Coords destination) {
		this.destination = destination;
	}

	@Override
	public boolean execute(Ship ship) {

		// boolean rotationChange = rotationChange(ship, destination);
		// ship.getControl().setTurbo(rotationChange);
		boolean finishedTask = accelerationChange(ship, destination, PRECISION);

		return finishedTask;
	}
}
