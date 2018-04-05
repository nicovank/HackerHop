package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.Game;
import com.hackerhop.game.core.graphics.GraphicsElement;

/**
 * This class is an interface for creating more scenes.
 */
public abstract class Scene implements GraphicsElement, InputProcessor {
	private static final String TAG = Scene.class.getName();

	private Game controller;

	public Scene(Game controller) {
		this.controller = controller;
		Gdx.input.setInputProcessor(this);
	}

	public Game getController() {
		return controller;
	}

	/**
	 * Updates the physics, character positions, etc.
	 */
	public abstract void update();

	/**
	 * Renders the scenes to be drawn
	 *
	 * @param batch where the scene will be rendered
	 */
	public abstract void render(SpriteBatch batch);
}
