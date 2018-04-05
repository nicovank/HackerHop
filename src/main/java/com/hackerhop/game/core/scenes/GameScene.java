package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.handlers.ContactHandler;
import com.hackerhop.game.core.objects.HomeworkObstacle;
import com.hackerhop.game.core.objects.TextbookObstacle;
import com.hackerhop.game.core.player.Player;
import com.hackerhop.game.core.utils.Direction;
import com.hackerhop.game.core.utils.Platforms;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.collision.Manifold;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.Fixture;
import org.jbox2d.dynamics.World;
import org.jbox2d.dynamics.contacts.Contact;
import com.hackerhop.game.core.utils.Character;


/**
 * This scene is the "main" game, with the scrolling platforms and the player.
 */
public class GameScene extends Scene {

	private static final String TAG = GameScene.class.getName();

	private final Player player;

	// Our physics world
	private World world = new World(new Vec2(0, -50));

	private Platforms platforms = new Platforms(world);
	private TextureRegion background;

	//Some obstacle objects
	private HomeworkObstacle deadline = new HomeworkObstacle(world);
	private TextbookObstacle textbook = new TextbookObstacle(world);

	// Frame time accumulator
	private float accumulator = 0.0f;

	private OrthographicCamera camera;

	// Some world constants
	private static final float TIME_STEP = 1 / 60f;
	private static final int VELOCITY_ITERATIONS = 2;
	private static final int POSITION_ITERATIONS = 6;


	/**
	 * Creates a new MainController Scene.
	 *
	 * @param controller The MainController controller. Used when we need to change scenes for example.
	 */
	public GameScene(MainController controller, Character character) {
		super(controller);
		setWorld(world);
		world.setContactListener(listener);

		player = new Player(world, new Vec2(0, 10), character);

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera(w, h);
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

		camera.position.set(camera.position.x, 300 + player.getBody().getPosition().y * 10, camera.position.z);
		camera.update();
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
		batch.draw(background, 0, 0);
		platforms.render(batch);
		batch.end();

		batch.begin();
		player.render(batch);
		batch.end();

		batch.begin();
		deadline.render(batch);
		batch.end();

		batch.begin();
		textbook.render(batch);
		batch.end();

	}

	@Override
	public void loadResources() {
		player.loadResources();
		platforms.loadResources();
		deadline.loadResources();
		textbook.loadResources();
		background = new TextureRegion(new Texture("background/ShinemanPixel.png"));
	}

	/**
	 * Disposes of all the textures and other objects used in the scene.
	 */
	@Override
	public void dispose() {
		player.dispose();
		platforms.dispose();
		deadline.dispose();
		textbook.dispose();
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
			player.setDirection(Direction.LEFT);
		}

		if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
			player.getBody().applyForceToCenter(new Vec2(5000f, 0f));
			player.setDirection(Direction.RIGHT);
		}
		if (keycode == Input.Keys.SPACE || keycode == Input.Keys.UP) {
			player.getBody().applyForceToCenter(new Vec2(0f, 5000f));
		}
		if(keycode == Input.Keys.ESCAPE){
			MainController controller = super.getController();
			controller.setScene(new MainMenu(controller));
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

	//Sets world
	public void setWorld(World world) {
		this.world = world;
	}

	//Handles obstacle collision. TODO://Handle a lot more

	ContactHandler listener = new ContactHandler(super.getController()){
		@Override
		public void beginContact(Contact contact) {
			Fixture fixtureA = contact.getFixtureA();
			Fixture fixtureB = contact.getFixtureB();
			//Quit game if player and obstacle collide
			if(fixtureA.getBody() == deadline.getBody() || fixtureA.getBody() == textbook.getBody()
					&& fixtureB.getBody() == player.getBody()){
				getController().setScene(new GameOverScene(getController()));
			}
		}

		@Override
		public void endContact(Contact contact) {

		}

		@Override
		public void preSolve(Contact contact, Manifold manifold) {

		}

		@Override
		public void postSolve(Contact contact, ContactImpulse contactImpulse) {

		}
	};



}

