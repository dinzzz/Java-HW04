package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;
import hr.fer.zemris.math.Vector2D;

/**
 * Class that represents the action of drawing the line in the Lindermayer's
 * system. It takes the old position given by the state at the top of the
 * context then calculates the new position of the turtle and connects those two
 * position with the line.
 * 
 * @author Dinz
 *
 */
public class DrawCommand implements Command {
	/**
	 * Number of steps to draw.
	 */
	double step;

	/**
	 * Constructs a new instance of the DrawCommand class with the desired number of
	 * steps.
	 * 
	 * @param step
	 *            Number of steps.
	 */
	public DrawCommand(double step) {
		this.step = step;
	}

	/**
	 * Method that executes the class and draws the line between old position and
	 * new calcuated position.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState newState = ctx.getCurrentState();
		Vector2D oldPosition = newState.getPosition();
		Vector2D turtleDirection = new Vector2D(newState.getDirection().getX(), newState.getDirection().getY());
		double stepLength = newState.getStepLength();
		double newStep = stepLength * this.step;
		Color color = newState.getColor();

		Vector2D newPosition = new Vector2D(oldPosition.getX(), oldPosition.getY());
		newPosition.translate(turtleDirection.scaled(newStep));
		newState.setPosition(newPosition);

		painter.drawLine(oldPosition.getX(), oldPosition.getY(), newPosition.getX(), newPosition.getY(), color, 1);
		ctx.popState();
		ctx.pushState(newState);
	}

}
