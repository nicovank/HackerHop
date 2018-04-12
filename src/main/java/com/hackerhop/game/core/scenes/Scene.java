package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.graphics.GraphicsElement;

/**
 * This class is an interface for creating more scenes.
 */
public abstract class Scene implements GraphicsElement, InputProcessor {
	private static final String TAG = Scene.class.getName();

	private MainController controller;

	public Scene(MainController controller) {
		this.controller = controller;
		Gdx.input.setInputProcessor(this);
	}

	public MainController getController() {
		return controller;
	}

	public String getTag(){
		return TAG;
	}

	/**
	 * Updates the physics, character positions, etc.
	 */
	public abstract void update();
}
