package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.math.Vector2D;

/**
 * Class that represents a skip command. Command that skips to the new position
 * without drawing the line. Implements a Command interface.
 * 
 * @author Dinz
 *
 */
public class SkipCommand implements Command {
	/**
	 * Number of steps to skip.
	 */
	double step;

	/**
	 * Constructs a new SkipCommand with the given number of steps.
	 * 
	 * @param step
	 *            Number of steps.
	 */
	public SkipCommand(double step) { // int step?
		this.step = step;
	}

	/**
	 * Method that executes the skip command and translates the turtle to the new
	 * position.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState newState = ctx.getCurrentState();

		Vector2D oldPosition = newState.getPosition();
		Vector2D turtleDirection = newState.getDirection();
		double stepLength = newState.getStepLength();
		double newStep = stepLength * this.step;

		Vector2D newPosition = oldPosition.translated(turtleDirection.scaled(newStep));

		newState.setPosition(newPosition);
		ctx.popState();
		ctx.pushState(newState);
	}

}
