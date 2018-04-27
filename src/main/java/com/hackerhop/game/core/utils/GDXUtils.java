package com.hackerhop.game.core.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.math.Vector2;

import java.util.function.Consumer;

public class GDXUtils {

	private static final class SimpleInputListener implements Input.TextInputListener {
		private Consumer<String> onSubmit;

		public SimpleInputListener(Consumer<String> onSubmit) {
			this.onSubmit = onSubmit;
		}

		@Override
		public void input(String s) {
			onSubmit.accept(s);
		}

		@Override
		public void canceled() {
			onSubmit.accept(null);
		}
	}

	/**
	 * Returns the x-position of the mouse on screen.
	 * @return the x-position of the mouse on screen.
	 */
	public static int getMouseX() {
		return Gdx.input.getX();
	}

	/**
	 * Returns the x-position of the mouse on screen.
	 * @return the x-position of the mouse on screen.
	 */
	public static int getMouseY() {
		return Gdx.graphics.getHeight() - Gdx.input.getY();
	}

	/**
	 * Returns the position of the mouse on the screen.
	 * @return a Vector2 representing the position of the mouse on screen.
	 */
	public static Vector2 getMousePosition() {
		return new Vector2(getMouseX(), getMouseY());
	}

	public static void getTextInput(String question, String initialValue, Consumer<String> onSubmit) {
		Gdx.input.getTextInput(new SimpleInputListener(onSubmit), question, initialValue);
	}
}
