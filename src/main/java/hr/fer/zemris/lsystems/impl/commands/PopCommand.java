package hr.fer.zemris.lsystems.impl.commands;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;

/**
 * Class that represents a pop command of the System. It pops and deletes the
 * state at the top of the given context. Implements a Command interface and its
 * execute method.
 * 
 * @author Dinz
 *
 */
public class PopCommand implements Command {
	/**
	 * Executes the PopCommand class and pops/deletes the item at the top of the
	 * context.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		ctx.popState();
	}
}
