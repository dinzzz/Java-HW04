package hr.fer.zemris.lsystems.impl;

import hr.fer.zemris.java.custom.collections.ObjectStack;

/**
 * Class that represents the context of the Lindermayer's systems. It is
 * actually an instance of the ObjectStack - stack-like representation of the
 * collection of objects. It stores states of the Turtle that is a tool for
 * drawing pictures in these systems.
 * 
 * @author Dinz
 *
 */
public class Context {
	/**
	 * Instance of the ObjectStack to store TurtleStates.
	 */
	private ObjectStack stack = new ObjectStack();

	/**
	 * Return the TurtleStates that is currently on the top of the stack.
	 * 
	 * @return Currently top turtle state on the stack.
	 */
	public TurtleState getCurrentState() {
		return (TurtleState) stack.peek();
	}

	/**
	 * Pushes a turtle state to the top of the stack.
	 * 
	 * @param state
	 *            TurtleState to be pushed.
	 */
	public void pushState(TurtleState state) {
		stack.push(state);
	}

	/**
	 * Pops and deletes the TurtleState at the top of the stack.
	 */
	public void popState() {
		stack.pop();
	}

}
