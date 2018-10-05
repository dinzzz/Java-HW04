package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.zemris.math.Vector2D;

/**
 * Class that represents the TurtleState - the element that remembers the
 * position, direction, color and length of the step of the drawing tool. It
 * uses twodimensional vectors to remember the position and direction.
 * 
 * @author Dinz
 *
 */
public class TurtleState {
	/**
	 * Position of the turtle.
	 */
	private Vector2D position;
	/**
	 * Direction of the turtle.
	 */
	private Vector2D direction;
	/**
	 * Color that the turtle draws with.
	 */
	private Color color;
	/**
	 * Length of the step of the turtle.
	 */
	private double stepLength;

	/**
	 * Constructs a new instance of the TurtleState with the given position,
	 * direction, color and the stepLength.
	 * 
	 * @param position
	 *            Position of the turtle.
	 * @param direction
	 *            Direction of the turtle.
	 * @param color
	 *            Color that the turtle draws with.
	 * @param step
	 *            Length of the step of the turtle.
	 */
	public TurtleState(Vector2D position, Vector2D direction, Color color, double step) {
		this.position = position;
		this.direction = direction;
		this.color = color;
		this.stepLength = step;
	}

	/**
	 * Copies the TurtleState into the new instance of the same class.
	 * 
	 * @return New TurtleState.
	 */
	public TurtleState copy() {
		return new TurtleState(this.position, this.direction, this.color, this.stepLength);
	}

	/**
	 * Gets the current position of the turtle as a twodimensional vector.
	 * 
	 * @return Current position of the turtle.
	 */
	public Vector2D getPosition() {
		return position;
	}

	/**
	 * Sets the position of the turtle.
	 * 
	 * @param position
	 *            Vector that represents the new position of the turtle.
	 */
	public void setPosition(Vector2D position) {
		this.position = position;
	}

	/**
	 * Gets the direction of the turtle.
	 * 
	 * @return Direction of the turtle.
	 */
	public Vector2D getDirection() {
		return direction;
	}

	/**
	 * Sets the direction of the turtle.
	 * 
	 * @param direction
	 *            Desired direction of the turtle.
	 */
	public void setDirection(Vector2D direction) {
		this.direction = direction;
	}

	/**
	 * Gets the color of the drawing tool.
	 * 
	 * @return Color of the drawing tool.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * Sets the color of the drawing tool.
	 * 
	 * @param color
	 *            Color a
	 */
	public void setColor(Color color) {
		this.color = color;
	}

	/**
	 * Gets the length of the step of the turtle.
	 * 
	 * @return Length of the step.
	 */
	public double getStepLength() {
		return stepLength;
	}

	/**
	 * Sets the length of the step of the turtle.
	 * 
	 * @param step
	 *            Desired length of the step.
	 */
	public void setStepLength(double step) {
		this.stepLength = step;
	}

}
