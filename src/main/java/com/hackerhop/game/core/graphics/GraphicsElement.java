package com.hackerhop.game.core.graphics;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * Anything that will be displayed on the screen will need the implement this interface.
 * It implements Disposable as any resource loaded will need to be disposed.
 */
public interface GraphicsElement extends Disposable {
	/**
	 * Will loads the graphics associated with the class.
	 */
	void loadGraphics();

	/**
	 * Will render the element on the screen.
	 *
	 * @param batch the batch where to draw the object.
	 */
	void render (SpriteBatch batch);
}
