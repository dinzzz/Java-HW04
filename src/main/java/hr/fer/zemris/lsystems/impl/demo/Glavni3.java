package hr.fer.zemris.lsystems.impl.demo;

import hr.fer.zemris.lsystems.gui.LSystemViewer;
import hr.fer.zemris.lsystems.impl.LSystemBuilderImpl;

/**
 * Class that represents the execution of the Lindermayer's system example.
 * Precisely it draws the Koch curve using the given implementations of the
 * LSystem, LsystemViewer, LSystemBuilder and LSystemBuilderProvider. This
 * example has a button on the interface to import the configuration from the
 * file.
 * 
 * @author Dinz
 *
 */
public class Glavni3 {
	/**
	 * Method that executes the class and draws the Lindermayer's system.
	 * 
	 * @param args
	 *            Arguments from the command line.
	 */
	public static void main(String[] args) {
		LSystemViewer.showLSystem(LSystemBuilderImpl::new);
	}
}
