package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Class that represents a push command - copies the current state at the top of
 * the context and pushes it once more on the top. Implements a command
 * interface.
 * 
 * @author Dinz
 *
 */
public class PushCommand implements Command {
	/**
	 * Method that executes the PushCommand class and pushes the copied state
	 * element to the top of the context.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState copy = ctx.getCurrentState().copy();
		ctx.pushState(copy);
	}

}
