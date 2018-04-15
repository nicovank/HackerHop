package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.handlers.ContactHandler;
import com.hackerhop.game.core.objects.obstacles.ObstacleGenerator;
import com.hackerhop.game.core.objects.platforms.Platforms;
import com.hackerhop.game.core.objects.Coin;
import com.hackerhop.game.core.player.Character;
import com.hackerhop.game.core.player.Direction;
import com.hackerhop.game.core.player.Player;
import com.hackerhop.game.core.utils.Constants;
import com.hackerhop.game.core.utils.Options;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import java.io.IOException;

/**
 * This scene is the "main" game, with the scrolling platforms and the player.
 */
public class GameScene extends Scene implements Constants {

	private static final String TAG = GameScene.class.getName();

	// Some world constants
	private static final float TIME_STEP = 1 / 60f;
	private static final int VELOCITY_ITERATIONS = 2;
	private static final int POSITION_ITERATIONS = 6;
	private static final int CAMERA_MOVEMENT_THRESHOLD = - 100;

	// General variables for the game scene
	private World world = new World(new Vec2(0, -50));
	private OrthographicCamera camera = new OrthographicCamera(SCREEN_WIDTH, SCREEN_HEIGHT);

	private long score = 0;
	private Sprite highScoreBorder;
	private Player player;

	private Platforms platforms = new Platforms(world);
	private Coin coin = new Coin(10, 10, world);
	private ObstacleGenerator obstacleGenerator = new ObstacleGenerator(world, camera);

	// Resources for the scene
	private Music music;
	private TextureRegion background;
	private BitmapFont font;
	private SpriteBatch ui;

	// Frame time accumulator
	private float accumulator = 0.0f;
	private Sprite cloudBackground;


	/**
	 * Creates a new MainController Scene.
	 *
	 * @param controller The MainController controller. Used when we need to change scenes for example.
	 */
	public GameScene(MainController controller, Character character) {
		super(controller);
		world.setContactListener(new ContactHandler(this));

		player = new Player(world, new Vec2((SCREEN_WIDTH / (2 * PHYSICS_RATIO)), 10), character);

		camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
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

		// move camera only if the player is outside a threshold

		float oldY = camera.position.y;

		if (player.getBody().getPosition().y * PHYSICS_RATIO > oldY + CAMERA_MOVEMENT_THRESHOLD) {
			float newY = (player.getBody().getPosition().y * PHYSICS_RATIO) - CAMERA_MOVEMENT_THRESHOLD;
			camera.position.set(camera.position.x, newY, camera.position.z);
			platforms.update(camera.position.y, world);
			score += newY - oldY;
		}

		// go to game over scene if player falls below bounds
		if (player.getBody().getPosition().y * PHYSICS_RATIO < camera.position.y - 900) {
			playerDeath();
		}

		player.update();
		camera.update();
		obstacleGenerator.update();

		coin.update();
	}

	/**
	 * We basically need to render each body in the game, as well as a background,
	 * and other UI elements (score, lives, etc.)
	 *
	 * @param batch where the scene will be rendered
	 */
	@Override
	public void render(SpriteBatch batch) {
		batch.setProjectionMatrix(camera.combined);

		batch.begin();

		batch.draw(cloudBackground, 0, camera.position.y - 500);
		batch.draw(background, 0, -50);
		platforms.render(batch);
		coin.render(batch);
		obstacleGenerator.render(batch);
		player.render(batch);
		batch.end();


		ui.begin();

		ui.draw(highScoreBorder,0, 0);
		font.draw(ui, String.format("Score: %s", score()), 10, 25);

		ui.end();
	}

	/**
	 * Transforms the current score into a nice displayable String.
	 *
	 * @return A String representation of the current score.
	 */
	public String score() {
		return String.valueOf(score);
	}

	@Override
	public void loadResources() {

		player.loadResources();
		platforms.loadResources();
		coin.loadResources();
		background = new TextureRegion(new Texture("background/ShinemanPixel.png"));
		music = Gdx.audio.newMusic(Gdx.files.internal("audio/DkIslandSwing.mp3"));
		cloudBackground = new Sprite(new Texture("background/cloud.png"));
		highScoreBorder = new Sprite(new Texture("background/HighScoreBorder.png"));
		font = new BitmapFont(Gdx.files.internal("fonts/pixels/pixels.fnt"));
		font.setScale(0.1f);
		ui = new SpriteBatch();

		music.setLooping(true);
		music.setVolume(Options.sounds() ? 1f : 0f);
		music.play();

		obstacleGenerator.loadResources();
	}

	/**
	 * Disposes of all the textures and other objects used in the scene.
	 */
	@Override
	public void dispose() {
		player.dispose();
		platforms.dispose();
		music.dispose();
		font.dispose();
		ui.dispose();
		coin.dispose();

		highScoreBorder.getTexture().dispose();
		cloudBackground.getTexture().dispose();

		obstacleGenerator.dispose();
	}

	/**
	 * Called when a key is pressed.
	 *
	 * @param keycode The code of the pressed key.
	 * @return whether the input was processed.
	 */
	@Override
	public boolean keyDown(int keycode) {
		if (keycode == Input.Keys.SPACE || keycode == Input.Keys.UP) {
			player.jump();
		} else if (keycode == Input.Keys.ESCAPE) {
			MainController controller = super.getController();
			controller.setScene(new MainMenu(controller));
		} else if(keycode == Input.Keys.R){
			Character character = player.getCharacter();
			MainController controller = super.getController();
			controller.setScene(new GameScene(controller,character));
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
		} else if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
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

	public Player getPlayer() {
		return player;
	}

	public void playerDeath(){
//		Character character = player.getCharacter();
//		float x = player.getBody().getPosition().x;
//		float y = player.getBody().getPosition().y;
		getController().setScene(new GameOverScene(getController(), score(), player));

	}

}


