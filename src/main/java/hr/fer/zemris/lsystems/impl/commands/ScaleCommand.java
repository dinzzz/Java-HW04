package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Class that represents a scale command - command that scales the given turtle
 * step length based on the given factor. Implements the Command interface.
 * 
 * @author Dinz
 *
 */
public class ScaleCommand implements Command {
	/**
	 * Factor for scaling.
	 */
	double factor;

	/**
	 * Constructs a new scale command with the given factor.
	 * 
	 * @param factor
	 *            Given factor.
	 */
	public ScaleCommand(double factor) {
		this.factor = factor;
	}

	/**
	 * Method that executes the ScaleCommand class and scales the step length of the
	 * turtle from the top of the context
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState newState = ctx.getCurrentState();
		newState.setStepLength(newState.getStepLength() * this.factor);
		ctx.popState();
		ctx.pushState(newState);
	}

}
