package hr.fer.zemris.math;

import org.junit.Test;

import junit.framework.Assert;

@SuppressWarnings("deprecation")
public class Vector2DTest {

	@Test
	public void translateTest() {
		Vector2D vector = new Vector2D(5, 5);

		Vector2D assist = new Vector2D(3, 3);

		Vector2D expected = new Vector2D(8, 8);
		vector.translate(assist);

		Assert.assertEquals(expected, vector);
	}

	@Test
	public void translateNegativeTest() {
		Vector2D vector = new Vector2D(5, 5);

		Vector2D assist = new Vector2D(-3, -2);

		Vector2D expected = new Vector2D(2, 3);
		vector.translate(assist);

		Assert.assertEquals(expected, vector);
	}

	@Test
	public void translateZeroTest() {
		Vector2D vector = new Vector2D(5, 5);

		Vector2D assist = new Vector2D(0, 0);

		Vector2D expected = new Vector2D(5, 5);
		vector.translate(assist);

		Assert.assertEquals(expected, vector);
	}

	@Test
	public void translatedTest() {
		Vector2D vector = new Vector2D(5, 5);

		Vector2D assist = new Vector2D(10, 2);

		Vector2D expected = new Vector2D(15, 7);
		Vector2D actual = vector.translated(assist);

		Assert.assertEquals(expected, actual);
	}

	@Test(expected = IllegalArgumentException.class)
	public void translateException() {
		Vector2D vector = new Vector2D(5, 5);

		Vector2D assist = null;

		vector.translate(assist);
	}

	@Test(expected = IllegalArgumentException.class)
	public void translatedException() {
		Vector2D vector = new Vector2D(5, 5);

		Vector2D assist = null;

		@SuppressWarnings("unused")
		Vector2D actual = vector.translated(assist);
	}

	@Test
	public void scaleTest() {
		Vector2D vector = new Vector2D(5, 5);
		double scaler = 2;

		Vector2D expected = new Vector2D(10, 10);
		vector.scale(scaler);

		Assert.assertEquals(expected, vector);
	}
	
	@Test
	public void scaleRealTest() {
		Vector2D vector = new Vector2D(6, 6);
		double scaler = 1.5;

		Vector2D expected = new Vector2D(9, 9);
		vector.scale(scaler);

		Assert.assertEquals(expected, vector);
	}

	@Test
	public void scaleZeroTest() {
		Vector2D vector = new Vector2D(6, 6);
		double scaler = 0;

		Vector2D expected = new Vector2D(0, 0);
		vector.scale(scaler);

		Assert.assertEquals(expected, vector);
	}

	@Test
	public void scaleNegativeTest() {
		Vector2D vector = new Vector2D(6, 6);
		double scaler = -1.5;

		Vector2D expected = new Vector2D(-9, -9);
		vector.scale(scaler);

		Assert.assertEquals(expected, vector);
	}

	@Test
	public void scaledTest() {
		Vector2D vector = new Vector2D(6, 6);
		double scaler = 1.5;

		Vector2D expected = new Vector2D(9, 9);
		Vector2D actual = vector.scaled(scaler);

		Assert.assertEquals(expected, actual);
	}

	@Test
	public void rotateTest() {
		Vector2D vector = new Vector2D(6, 6);
		double angle = 60;

		Vector2D expected = new Vector2D(-2.19615242271, 8.19615242271);
		Vector2D actual = vector.rotated(angle);

		Assert.assertEquals(expected.getX(), actual.getX(), 0.001);
		Assert.assertEquals(expected.getY(), actual.getY(), 0.001);
	}

	@Test
	public void rotateNegativeTest() {
		Vector2D vector = new Vector2D(6, 6);
		double angle = -60;
		
		Vector2D expected = new Vector2D(8.19615242271, -2.19615242271);
		Vector2D actual = vector.rotated(angle);

		Assert.assertEquals(expected.getX(), actual.getX(), 0.001);
		Assert.assertEquals(expected.getY(), actual.getY(), 0.001);
	}

	@Test
	public void rotateFullCircle() {
		Vector2D vector = new Vector2D(6, 6);
		double angle = 420;

		Vector2D expected = new Vector2D(-2.19615242271, 8.19615242271);
		Vector2D actual = vector.rotated(angle);

		Assert.assertEquals(expected.getX(), actual.getX(), 0.001);
		Assert.assertEquals(expected.getY(), actual.getY(), 0.001);
	}

	@Test
	public void rotateZero() {
		Vector2D vector = new Vector2D(6, 6);
		double angle = 0;

		Vector2D expected = new Vector2D(6, 6);
		Vector2D actual = vector.rotated(angle);

		Assert.assertEquals(expected.getX(), actual.getX(), 0.001);
		Assert.assertEquals(expected.getY(), actual.getY(), 0.001);
	}

}
