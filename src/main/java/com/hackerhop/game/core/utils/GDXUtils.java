package com.hackerhop.game.core.utils;

import com.badlogic.gdx.Gdx;

public class GDXUtils {
	/**
	 * Returns the x-position of the mouse on screen.
	 * @return the x-position of the mouse on screen.
	 */
	public static int mouseX() {
		return Gdx.input.getX();
	}

	/**
	 * Returns the x-position of the mouse on screen.
	 * @return the x-position of the mouse on screen.
	 */
	public static int mouseY() {
		return Gdx.graphics.getHeight() - Gdx.input.getY();
	}
}
