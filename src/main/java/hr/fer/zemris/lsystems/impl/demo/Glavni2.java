package hr.fer.zemris.lsystems.impl.demo;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * Class that represents the execution of the Lindermayer's system example.
 * Precisely it draws the Koch curve using the given implementations of the
 * LSystem, LsystemViewer, LSystemBuilder and LSystemBuilderProvider. This
 * example gets the data by configuring it from the text.
 * 
 * @author Dinz
 *
 */
public class Glavni2 {
	/**
	 * Method that executes the class and draws the picture.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		LSystemViewer.showLSystem(createKochCurve2(LSystemBuilderImpl::new));

	}

	/**
	 * Method that fills the data for the Lindermayer's by configuring it from the
	 * text.
	 * 
	 * @param provider
	 *            Provider.
	 * @return New intance of the LSystem class.
	 */
	private static LSystem createKochCurve2(LSystemBuilderProvider provider) {
		String[] data = new String[] { "origin 0.05 0.4", "angle 0", "unitLength 0.9",
				"unitLengthDegreeScaler 1.0 / 3.0", "", "command F draw 1", "command + rotate 60",
				"command - rotate -60", "", "axiom F", "", "production F F+F--F+F" };
		return provider.createLSystemBuilder().configureFromText(data).build();
	}

}
