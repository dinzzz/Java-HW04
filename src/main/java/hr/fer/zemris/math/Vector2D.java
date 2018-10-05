package hr.fer.zemris.math;

import static java.lang.Math.cos;
import static java.lang.Math.sin;

/**
 * Class that represents the use of the twodimensional vectors. The class covers
 * the actions with vectors such as translation, rotation and scaling. The
 * vectors are represented as the origin vectors with their x and y variables.
 * 
 * @author Dinz
 *
 */
public class Vector2D {
	/**
	 * Abscissa of the vector.
	 */
	private double x;
	/**
	 * Ordinate of the vector.
	 */
	private double y;

	/**
	 * Constructs a new twodimensional vector from given abscissa and ordinate.
	 * 
	 * @param x
	 *            Abscissa of the vector.
	 * @param y
	 *            Ordinate of the vector.
	 */
	public Vector2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Returns the abscissa of the vector.
	 * 
	 * @return Abscissa of the vector.
	 */
	public double getX() {
		return x;
	}

	/**
	 * Returns the ordinate of the vector.
	 * 
	 * @return Ordinate of the vector.
	 */
	public double getY() {
		return y;
	}

	/**
	 * Translate the vector for a given offset represented by another vector.
	 * 
	 * @param offset
	 *            Vector that serves as an offset.
	 * @throws IllegalArgumentException
	 *             if the offset is null.
	 */
	public void translate(Vector2D offset) {
		if (offset == null) {
			throw new IllegalArgumentException("Offset vector cannot be null.");
		}
		this.x += offset.getX();
		this.y += offset.getY();
	}

	/**
	 * Method that translates the vector for a given offset represented by another
	 * vector and store it as a new Vector element.
	 * 
	 * @param offset
	 *            Vector that serves as an offset.
	 * @return New vector element.
	 */
	public Vector2D translated(Vector2D offset) {
		if (offset == null) {
			throw new IllegalArgumentException("Offset vector cannot be null.");
		}
		Vector2D assist = new Vector2D(this.x, this.y);
		assist.translate(offset);

		return assist;
	}

	/**
	 * Method that scales the vector for a given scaler.
	 * 
	 * @param scaler
	 *            Scaler for the vector.
	 */
	public void scale(double scaler) {
		this.x *= scaler;
		this.y *= scaler;
	}

	/**
	 * Method that scales the vector for a given scaler and stores it as a vector
	 * element.
	 * 
	 * @param scaler
	 *            Scaler for the vector.
	 * @return New vector element.
	 */
	public Vector2D scaled(double scaler) {
		Vector2D assist = new Vector2D(this.x, this.y);
		assist.scale(scaler);

		return assist;
	}

	/**
	 * Method that rotates the vector for a given angle.
	 * 
	 * @param angle
	 *            Angle used for rotation.
	 */
	public void rotate(double angle) {
		double rotatedX = cos(Math.toRadians(angle)) * this.x - sin(Math.toRadians(angle)) * this.y;
		double rotatedY = sin(Math.toRadians(angle)) * this.x + cos(Math.toRadians(angle)) * this.y;

		this.x = rotatedX;
		this.y = rotatedY;
	}

	/**
	 * Method that rotates the vector for a given angle and stores it as a new
	 * vector element.
	 * 
	 * @param angle
	 *            Angle used for rotation.
	 * @return New vector elements.
	 */
	public Vector2D rotated(double angle) {
		Vector2D assist = new Vector2D(this.x, this.y);
		assist.rotate(angle);

		return assist;
	}

	/**
	 * Method that checks if the vectors are equal.
	 */
	@Override
	public boolean equals(Object o) {
		if (o == null) {
			return false;
		}
		if (!(o instanceof Vector2D)) {
			return false;
		}

		Vector2D vector = (Vector2D) o;

		return (vector.getX() == this.x) && (vector.getY() == this.y);
	}
}
