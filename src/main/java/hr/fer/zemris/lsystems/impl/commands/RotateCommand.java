package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Class that represents a rotate command - command that rotates the direction
 * of the turtle based on the given angle. Implements a Command interface.
 * 
 * @author Dinz
 *
 */
public class RotateCommand implements Command {
	/**
	 * Angle that is used in a command.
	 */
	double angle;

	/**
	 * Constructs a new rotate command with the given angle.
	 * 
	 * @param angle
	 */
	public RotateCommand(double angle) {
		this.angle = angle;
	}

	/**
	 * Method that executes the RotateCommand class and rotates the direction of the
	 * turtle for the given angle.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState newState = ctx.getCurrentState();
		newState.getDirection().rotate(this.angle);
		ctx.popState();
		ctx.pushState(newState);
	}

}
