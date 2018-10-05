package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.lsystems.Painter;

/**
 * Interface that defines the command. Depending on the type of the command, it
 * executes the action on the given context in the Lindermayer's systems.
 * 
 * @author Dinz
 *
 */
public interface Command {
	/**
	 * Method that executes the action of the command.
	 * 
	 * @param ctx
	 *            Given context.
	 * @param painter
	 *            Painter.
	 */
	void execute(Context ctx, Painter painter);
}
