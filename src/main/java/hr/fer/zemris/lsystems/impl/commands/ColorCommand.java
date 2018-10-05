package hr.fer.zemris.lsystems.impl.commands;

import java.awt.Color;

import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.Command;
import hr.fer.zemris.lsystems.impl.Context;
import hr.fer.zemris.lsystems.impl.TurtleState;

/**
 * Class that represents a command that sets the color of the system. It
 * implements the basic Command interface with its execute method.
 * 
 * @author Dinz
 *
 */
public class ColorCommand implements Command {
	/**
	 * Color that is used for drawing.
	 */
	Color color;

	/**
	 * Constructs a new ColorCommand class with desired color.
	 * 
	 * @param color
	 *            Desired color.
	 */
	public ColorCommand(Color color) {
		this.color = color;
	}

	/**
	 * Class that sets the color of the turtle state and replaces the state at the
	 * top of the context with a new updated state.
	 */
	@Override
	public void execute(Context ctx, Painter painter) {
		TurtleState newState = ctx.getCurrentState();
		newState.setColor(color);
		ctx.popState();
		ctx.pushState(newState);

	}

}
