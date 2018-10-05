package hr.fer.zemris.lsystems.impl;

import java.awt.Color;

import hr.fer.zemris.java.custom.collections.Dictionary;
import hr.fer.zemris.lsystems.LSystem;
import hr.fer.zemris.lsystems.LSystemBuilder;
import hr.fer.zemris.lsystems.Painter;
import hr.fer.zemris.lsystems.impl.commands.DrawCommand;
import hr.fer.zemris.lsystems.impl.commands.SkipCommand;
import hr.fer.zemris.lsystems.impl.commands.ColorCommand;
import hr.fer.zemris.lsystems.impl.commands.PushCommand;
import hr.fer.zemris.lsystems.impl.commands.RotateCommand;
import hr.fer.zemris.lsystems.impl.commands.ScaleCommand;
import hr.fer.zemris.lsystems.impl.commands.PopCommand;
import hr.fer.zemris.math.Vector2D;

/**
 * Class that represents the main actions that build the Lindermayer's systems.
 * The class is consisted of two dictionaries that store commands and
 * productions, general basic elements of the systems like length of the drawing
 * unit, degree scaler, origin vector, angle and the starting axiom. In the end,
 * the class helps in drawing the full desired Lindermayer's system.
 * 
 * @author Dinz
 *
 */
public class LSystemBuilderImpl implements LSystemBuilder {
	/**
	 * Dictionary filled with commands.
	 */
	private Dictionary registeredCommands = new Dictionary();
	/**
	 * Dictionary filled with productions.
	 */
	private Dictionary registeredActions = new Dictionary();

	/**
	 * Length of the one step of the turtle.
	 */
	private double unitLength = 0.1;
	/**
	 * Scaler for the length of the step of the turtle.
	 */
	private double unitLengthDegreeScaler = 1;
	/**
	 * Origin vector that starts in the center of the coordinate system.
	 */
	private Vector2D origin = new Vector2D(0, 0);
	/**
	 * Angle to modify the direction of the turtle
	 */

	private double angle = 0;
	/**
	 * Starting axiom.
	 */
	private String axiom = "";

	@Override
	/**
	 * Method that builds the Lindermayer's system. It uses an anonymus class
	 * derived from the LSystem interface with its draw and generate methods.
	 */
	public LSystem build() {
		return new LSystem() {

			/**
			 * Method that generate the array of the characters which is triggered by
			 * productions based on the level given and fills the command dictionary. Then,
			 * based on the commands the method draws the picture to the drawing board.
			 * 
			 * @throws IllegalArgumentException
			 *             if the command is invalid.
			 */
			@Override
			public void draw(int level, Painter painter) {
				Context ctx = new Context();
				TurtleState state = new TurtleState(origin, new Vector2D(1, 0).rotated(angle), Color.BLACK,
						unitLength * Math.pow(unitLengthDegreeScaler, level));
				ctx.pushState(state);

				String generated = generate(level);
				char[] generatedChars = generated.toCharArray();

				for (int i = 0; i < generatedChars.length; i++) {
					if (!registeredCommands.containsKey(generatedChars[i])) {
						continue;
					}

					String command = registeredCommands.get(generatedChars[i]).toString();
					String commandName = command.split(" +")[0];

					if (commandName.toUpperCase().equals("DRAW")) {
						double commandValue = Double.parseDouble(command.split(" +")[1]);
						new DrawCommand(commandValue).execute(ctx, painter);
					} else if (commandName.toUpperCase().equals("SKIP")) {
						double commandValue = Double.parseDouble(command.split(" +")[1]);
						new SkipCommand(commandValue).execute(ctx, painter);
					} else if (commandName.toUpperCase().equals("SCALE")) {
						double commandValue = Double.parseDouble(command.split(" +")[1]);
						new ScaleCommand(commandValue).execute(ctx, painter);
					} else if (commandName.toUpperCase().equals("ROTATE")) {
						double commandValue = Double.parseDouble(command.split(" +")[1]);
						new RotateCommand(commandValue).execute(ctx, painter);
					} else if (commandName.toUpperCase().equals("PUSH")) {
						new PushCommand().execute(ctx, painter);
					} else if (commandName.toUpperCase().equals("POP")) {
						new PopCommand().execute(ctx, painter);
					} else if (commandName.toUpperCase().equals("COLOR")) {
						String commandValue = command.split(" +")[1];
						Color colorValue = Color.decode("0x" + commandValue);
						new ColorCommand(colorValue).execute(ctx, painter);
					} else {
						throw new IllegalArgumentException("Wrong command!");
					}
				}

			}

			/**
			 * Generates the string of characters depending on the given level.
			 * 
			 * @throws IllegalArgumentException
			 *             if the level is less then 0.
			 */
			@Override
			public String generate(int level) {
				if (level < 0) {
					throw new IllegalArgumentException("Level must not be less then 0.");
				}
				if (level == 0) {
					return axiom;
				}
				String k = generate(level - 1);
				StringBuilder sb = new StringBuilder();
				// za svaki character u axiomu, provedi projekciju
				char[] charArray = k.toCharArray();
				for (int i = 0; i < charArray.length; i++) {
					if (registeredActions.containsKey(charArray[i])) {
						sb.append((String) (registeredActions.get(charArray[i])));
					} else {
						sb.append(charArray[i]);
					}
				}

				return sb.toString();
			}

		};
	}

	/**
	 * Method that configures the LSystemBuilder from the given text using the parse
	 * method.
	 */
	@Override
	public LSystemBuilder configureFromText(String[] arg0) {
		parse(arg0);
		return this;
	}

	/**
	 * Method that registers a command to the dictionary.
	 */
	@Override
	public LSystemBuilder registerCommand(char symbol, String action) {
		registeredCommands.put(symbol, action);
		return this;
	}

	/**
	 * Method that registers a production to the dictionary.
	 */
	@Override
	public LSystemBuilder registerProduction(char symbol, String production) {
		registeredActions.put(symbol, production);
		return this;
	}

	/**
	 * Method that sets the angle of the direction vector.
	 */
	@Override
	public LSystemBuilder setAngle(double angle) {
		this.angle = angle;
		return this;
	}

	/**
	 * Method that sets the Axiom.
	 */
	@Override
	public LSystemBuilder setAxiom(String axiom) {
		if (axiom == null) {
			throw new IllegalArgumentException("Axiom cannot be null.");
		}
		this.axiom = axiom;
		return this;
	}

	/**
	 * Method that sets the origin vector of the system.
	 */
	@Override
	public LSystemBuilder setOrigin(double x, double y) {
		Vector2D assist = new Vector2D(x, y);
		this.origin = assist;
		return this;
	}

	/**
	 * Method that sets the step length.
	 */
	@Override
	public LSystemBuilder setUnitLength(double unitLength) {
		this.unitLength = unitLength;
		return this;
	}

	/**
	 * Method that sets the step length scaler.
	 */
	@Override
	public LSystemBuilder setUnitLengthDegreeScaler(double unitLengthDegreeScaler) {
		this.unitLengthDegreeScaler = unitLengthDegreeScaler;
		return this;
	}

	/**
	 * Method that parses the given string array according to the given commands in
	 * the fields of the string array.
	 * 
	 * @param array
	 *            Given array of strings.
	 * @throws IllegalArgumentException
	 *             if the input is wrong.
	 */
	public void parse(String[] array) {
		for (int i = 0; i < array.length; i++) {

			if (array[i].length() == 0) {
				continue;
			}

			if (array[i].trim() == "") {
				continue;
			} else if (array[i].trim().toUpperCase().startsWith("ORIGIN")) {
				originDirective(array[i]);
			} else if (array[i].trim().toUpperCase().startsWith("ANGLE")) {
				angleDirective(array[i]);
			} else if (array[i].trim().toUpperCase().startsWith("UNITLENGTHDEGREESCALER")) {
				unitLengthDegreeScalerDirective(array[i]);
			} else if (array[i].trim().toUpperCase().startsWith("UNITLENGTH")) {
				unitLengthDirective(array[i]);
			} else if (array[i].trim().toUpperCase().startsWith("COMMAND")) {
				commandDirective(array[i]);
			} else if (array[i].trim().toUpperCase().startsWith("AXIOM")) {
				axiomDirective(array[i]);
			} else if (array[i].trim().toUpperCase().startsWith("PRODUCTION")) {
				productionDirective(array[i]);
			} else {
				throw new IllegalArgumentException("Wrong input.");
			}

		}
	}

	/**
	 * Method that checks the validation of the origin command and sets the class
	 * origin according to the input.
	 * 
	 * @param str
	 *            Input string
	 * @throws IllegalArgumentException
	 *             if the input of the origin is invalid.
	 */
	private void originDirective(String str) {
		str = str.replaceAll("(\\r|\\n|\\t)", "");
		String[] split = str.split(" +");
		// origin = 0, x = 1, y = 2//

		if (split.length != 3) {
			throw new IllegalArgumentException("Wrong input of the origin.");
		}

		if (!(isDouble(split[1])) || !(isDouble(split[2]))) {
			throw new IllegalArgumentException("Wrong input of the origin.");
		}

		double x = Double.parseDouble(split[1]);
		double y = Double.parseDouble(split[2]);

		this.setOrigin(x, y);

	}

	/**
	 * Method that checks the validation of the angle command and sets the class
	 * angle according to the input.
	 * 
	 * @param str
	 *            Input string
	 * @throws IllegalArgumentException
	 *             if the input of the angle is invalid.
	 */
	private void angleDirective(String str) {
		str = str.replaceAll("(\\r|\\n|\\t)", "");
		String[] split = str.split(" +");
		// angleKeyWord = 0, angleValue = 1
		if (split.length != 2) {
			throw new IllegalArgumentException("Wrong input of the angle.");
		}

		if (!(isDouble(split[1]))) {
			throw new IllegalArgumentException("Wrong input of the angle.");
		}
		double angle = Double.parseDouble(split[1]);

		this.setAngle(angle);
	}

	/**
	 * Method that checks the validation of the unit length command and sets the
	 * class unit length according to the input.
	 * 
	 * @param str
	 *            Input string
	 * @throws IllegalArgumentException
	 *             if the input of the unit length is invalid.
	 */
	private void unitLengthDirective(String str) {
		str = str.replaceAll("(\\r|\\n|\\t)", "");
		String[] split = str.split(" +");
		// unitLengthKeyWord = 0, unitLengthValue = 1

		if (split.length != 2) {
			throw new IllegalArgumentException("Wrong input of the unitLength");
		}

		if (!(isDouble(split[1]))) {
			throw new IllegalArgumentException("Wrong input of the unitLength");
		}

		double unitLength = Double.parseDouble(split[1]);

		this.setUnitLength(unitLength);
	}

	/**
	 * Method that checks the validation of the unit length scaler command and sets
	 * the class unit length scaler according to the input.
	 * 
	 * @param str
	 *            Input string
	 * @throws IllegalArgumentException
	 *             if the input of the unit length scaler is invalid.
	 */
	public void unitLengthDegreeScalerDirective(String str) {
		str = str.replaceAll("(\\r|\\n|\\t)", "");
		str = str.replaceAll("/", " ");

		// the expression can be a fraction with spaces (1.0 / 3.0)
		// fraction without spaces (1.0/3.0)
		// double number

		String[] split = str.split(" +");
		if (split.length == 2) {
			double unitLengthDegreeScaler = Double.parseDouble(split[1]);

			this.setUnitLengthDegreeScaler(unitLengthDegreeScaler);
		} else if (split.length == 3) {
			double unitLengthDegreeScaler = Double.parseDouble(split[1]) / Double.parseDouble(split[2]);
			this.setUnitLengthDegreeScaler(unitLengthDegreeScaler);
		} else {
			throw new IllegalArgumentException("Wrong input.");
		}

	}

	/**
	 * Method that checks the validation of the input command and registers a
	 * command to the dictionary.
	 * 
	 * @param str
	 *            Input string
	 * @throws IllegalArgumentException
	 *             if the input of the command is invalid.
	 */
	public void commandDirective(String str) {
		str = str.replaceAll("(\\r|\\n|\\t)", "");
		String[] split = str.split(" +");
		// command=0, symbol = 1, expression 2 + 3
		if (split.length == 4) {
			char symbol = split[1].toCharArray()[0];
			String command = split[2] + " " + split[3];

			this.registerCommand(symbol, command);
		} else if (split.length == 3) {
			char symbol = split[1].toCharArray()[0];
			String command = split[2];

			this.registerCommand(symbol, command);
		} else {
			throw new IllegalArgumentException("Wrong input of the command.");
		}

	}

	/**
	 * Method that checks the validation of the axiom command and sets the class
	 * axiom according to the input.
	 * 
	 * @param str
	 *            Input string
	 * @throws IllegalArgumentException
	 *             if the input of the origin is invalid.
	 */
	public void axiomDirective(String str) {
		str = str.replaceAll("(\\r|\\n|\\t)", "");
		String[] split = str.split(" +");

		if (split.length != 2) {
			throw new IllegalArgumentException("Wrong input of the axiom.");
		}

		this.setAxiom(split[1]);

	}

	/**
	 * Method that checks the validation of the production and adds the production
	 * to the dictionary.
	 * 
	 * @param str
	 *            Input string
	 * @throws IllegalArgumentException
	 *             if the input of the origin is invalid.
	 */
	public void productionDirective(String str) {
		str = str.replaceAll("(\\r|\\n|\\t)", "");
		String[] split = str.split(" +");

		if (split.length != 3) {
			throw new IllegalArgumentException("Wrong input of the production.");
		}

		char symbol = split[1].toCharArray()[0];
		String production = split[2];

		this.registerProduction(symbol, production);

	}

	/**
	 * Method that checks if the given string is parseable to double.
	 * 
	 * @param str
	 *            String to be checked.
	 * @return True if the string is parseable, false otherwise.
	 */
	public static boolean isDouble(String str) {
		try {
			Double.parseDouble(str);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

}
