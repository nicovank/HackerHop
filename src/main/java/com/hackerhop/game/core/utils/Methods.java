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

	/**
	 * Generates a random floating point number between min inclusive and max exclusive.
	 *
	 * @param min the minimum bound of the interval where the float will be generated.
	 * @param max the max bound of the interval where the float will be generated.
	 * @return a random integer float min and max.
	 */
	public static float randomFloat(float min, float max) {
		return ((float) (Math.random() * max)) + min;
	}

	/**
	 * Generates a random integer between 0 and max exclusive.
	 *
	 * @param max the max bound of the interval where the integer will be generated.
	 * @return a random integer between 0 and max.
	 */
	public static float randomFloat(float max) {
		return randomFloat(0f, max);
	}
}
