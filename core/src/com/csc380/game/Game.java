package com.csc380.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

/**
 * "Main" class, used by DesktopLauncher and AndroidLauncher.
 * Starts the game with the initial scene.
 */
public class Game extends ApplicationAdapter {

	// We will have this TAG in every class to easily use LibGDX's logging system.
	private static final String TAG = Game.class.getName();

	private SpriteBatch batch;


	@Override
	public void create () {

		Gdx.app.setLogLevel(Application.LOG_DEBUG);

		batch = new SpriteBatch();

		// To log information or errors use:

		// Gdx.app.log(TAG, "This is an info log.");
        // Gdx.app.debug(TAG, "This is a debug log.");
        // Gdx.app.error(TAG, "This is an error log.");
	}

	@Override
	public void render () {
		// Clear frame
		Gdx.gl.glClearColor(1, .5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draw next frame
		batch.begin();
		// Draw sprites and textures (or the scene) to the batch.
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
