package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.Game;
import com.hackerhop.game.core.player.Player;
import com.hackerhop.game.core.utils.Platforms;
import org.jbox2d.common.Vec2;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;
import com.hackerhop.game.core.utils.HashTable.LinkedList;

import java.util.Random;
import java.util.HashSet;


/**
 * This scene is the "main" game, with the scrolling platforms and the player.
 */
public class GameScene extends Scene {

	private final Player player;

	// Our physics world
	private World world = new World(new Vec2(0, -50));

	private Platforms platforms = new Platforms(world);

	// Frame time accumulator
	private float accumulator = 0.0f;

	// Some world constants
	private static final float TIME_STEP = 1 / 60f;
	private static final int VELOCITY_ITERATIONS = 2;
	private static final int POSITION_ITERATIONS = 6;


	/**
	 * Creates a new Game Scene.
	 *
	 * @param controller The Game controller. Used when we need to change scenes for example.
	 */
	public GameScene(Game controller) {
		super(controller);

		player = new Player(world, new Vec2(0, 10));

		// TODO: Remove that initial jump.
		player.getBody().applyLinearImpulse(new Vec2(0, 60), player.getBody().getLocalCenter());
	}

	/**
	 * Runs Box2D's physics step, making the objects move if needed.
	 * See https://github.com/libgdx/libgdx/wiki/box2d#stepping-the-simulation
	 */
	@Override
	public void update() {
		float deltaTime = Gdx.graphics.getDeltaTime();
		float frameTime = Math.min(deltaTime, 0.25f);
		accumulator += frameTime;

		while (accumulator > TIME_STEP) {
			world.step(TIME_STEP, VELOCITY_ITERATIONS, POSITION_ITERATIONS);
			accumulator -= TIME_STEP;
		}
	}

	/**
	 * We basically need to render each body in the game, as well as a background,
	 * and other UI elements (score, lives, etc.)
	 *
	 * @param batch where the scene will be rendered
	 */
	@Override
	public void render(SpriteBatch batch) {
		batch.begin();
		platforms.render(batch);
		batch.end();

		batch.begin();
		player.render(batch);
		batch.end();
	}

	@Override
	public void loadGraphics() {
		player.loadGraphics();
		platforms.loadGraphics();
	}

	/**
	 * Disposes of all the textures and other objects used in the scene.
	 */
	@Override
	public void dispose() {
		player.dispose();
		platforms.dispose();
	}

	/**
	 * Called when a key is pressed.
	 *
	 * @param keycode The code of the pressed key.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
			player.getBody().applyForceToCenter(new Vec2(-5000f, 0f));
		}

		if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
			player.getBody().applyForceToCenter(new Vec2(5000f, 0f));
		}
		if (keycode == Input.Keys.SPACE || keycode == Input.Keys.UP) {
			player.getBody().applyForceToCenter(new Vec2(0f, 5000f));
		}

		return true;
	}

	/**
	 * Called when a key is released.
	 *
	 * @param keycode The code of the released key.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean keyUp(int keycode) {
		if (keycode == Input.Keys.A || keycode == Input.Keys.LEFT) {
			player.getBody().applyForceToCenter(new Vec2(5000f, 0f));
		}

		if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
			player.getBody().applyForceToCenter(new Vec2(-5000f, 0f));
		}

		return true;
	}


	/**
	 * Called when a key is typed.
	 *
	 * @param character The character typed.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	/**
	 * Called when the screen was touched or a mouse button was pressed.
	 *
	 * @param screenX the x-coordinate.
	 * @param screenY the y-coordinate.
	 * @param pointer the pointer for the event.
	 * @param button  which button was pressed.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Called when the screen was touched or a mouse button was released.
	 *
	 * @param screenX the x-coordinate.
	 * @param screenY the y-coordinate.
	 * @param pointer the pointer for the event.
	 * @param button  which button was pressed.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	/**
	 * Called when a finger or the mouse was dragged.
	 *
	 * @param screenX the x-coordinate.
	 * @param screenY the y-coordinate.
	 * @param pointer the pointer for the event.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	/**
	 * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
	 *
	 * @param screenX the x-coordinate.
	 * @param screenY the y-coordinate.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	/**
	 * Called when the mouse wheel was scrolled. Will not be called on iOS.
	 *
	 * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean scrolled(int amount) {
		return false;
	}
}

