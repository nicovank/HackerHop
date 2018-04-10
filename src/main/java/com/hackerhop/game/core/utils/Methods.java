package com.hackerhop.game.core.utils;

/**
 * Some utility methods that we can use in different parts of the project.
 */
public class Methods {
	/**
	 * Generates a random integer between min inclusive and max exclusive.
	 *
	 * @param min the minimum bound of the interval where the integer will be generated.
	 * @param max the max bound of the interval where the integer will be generated.
	 * @return a random integer between min and max.
	 */
	public static int randomInt(int min, int max) {
		return ((int) (Math.random() * max)) + min;
	}

	/**
	 * Generates a random integer between 0 and max exclusive.
	 *
	 * @param max the max bound of the interval where the integer will be generated.
	 * @return a random integer between 0 and max.
	 */
	public static int randomInt(int max) {
		return randomInt(0, max);
	}
}
