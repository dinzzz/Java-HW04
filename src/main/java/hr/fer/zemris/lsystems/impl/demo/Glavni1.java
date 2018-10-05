package hr.fer.zemris.lsystems.impl.demo;

import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilderProvider;
import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * Class that represents the execution of the Lindermayer's system example.
 * Precisely it draws the Koch curve using the given implementations of the
 * LSystem, LsystemViewer, LSystemBuilder and LSystemBuilderProvider. This
 * examples gets the data by directly giving it to the provider in the
 * createKochCurve method.
 * 
 * @author Dinz
 *
 */
public class Glavni1 {
	/**
	 * Method that executes the class and draws the picture.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		LSystemViewer.showLSystem(createKochCurve(LSystemBuilderImpl::new));
	}

	/**
	 * Method that fills the data for the Lindermayer's system directly using
	 * methods.
	 * 
	 * @param provider
	 *            Provider.
	 * @return New intance of the LSystem class.
	 */
	private static LSystem createKochCurve(LSystemBuilderProvider provider) {
		return provider.createLSystemBuilder().registerCommand('F', "draw 1").registerCommand('+', "rotate 60")
				.registerCommand('-', "rotate -60").setOrigin(0.05, 0.4).setAngle(0).setUnitLength(0.9)
				.setUnitLengthDegreeScaler(1.0 / 3.0).registerProduction('F', "F+F--F+F").setAxiom("F").build();

	}
}
