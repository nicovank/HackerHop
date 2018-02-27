package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.hackerhop.game.core.Game;
import com.hackerhop.game.core.player.Player;
import org.jbox2d.common.Vec2;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;
import java.util.Random;
import java.util.HashSet;


/**
 * This scene is the "main" game, with the scrolling platforms and the player.
 */
public class GameScene extends Scene {

    private final OrthographicCamera camera;
    private final Player player;


    //ShapeRenderer
    ShapeRenderer renderer = new ShapeRenderer();
    private HashSet<Platform> platforms = genPlats(7);


    // Our physics world
    World world = new World(new Vec2(0, -25));

    // Frame time accumulator
    private float accumulator = 0.0f;

    // Some world constants
    private static final float TIME_STEP = 1 / 60f;
    private static final int VELOCITY_ITERATIONS = 2;
    private static final int POSITION_ITERATIONS = 6;




    /**
     * Creates a new Game Scene.
     * TODO: Instantiate a new player, some platforms, etc.
     *
     * @param controller The Game controller. Used when we need to change scenes for example.
     */
    public GameScene(Game controller) {
        super(controller);

        player = new Player(world, new Vec2(10,0));
        player.getBody().applyLinearImpulse(new Vec2(0, 40), player.getBody().getLocalCenter());
        camera = new OrthographicCamera(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
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
        batch.setProjectionMatrix(camera.combined);

        //Rectangles are filled shapes
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        //Render each platform in the platform array
        for (Platform p : platforms) {
            p.rectRender(renderer);
        }
        renderer.end();

        batch.begin();
        player.render(batch);
        batch.end();
    }

    /**
     * Randomly generates HashSet of n platforms.
     * @param n The number of platforms to be generated.
     */
    private HashSet<Platform> genPlats(int n){
        // use custom HashSet
        HashSet<Platform> plat = new HashSet<Platform>();
        Random r = new Random();

        while (n > 0){
            Platform e = new Platform((70+r.nextInt(300)), 60, 20, (1+r.nextInt(300)));
            if (plat.add(e)) {
                --n;
            }
        }
        return plat;
    }

    /**
     * Disposes of all the textures and other objects used in the scene.
     */
    @Override
    public void dispose() {

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
            player.getBody().applyForceToCenter(new Vec2(-5f, 0f));
        }

        if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            player.getBody().applyForceToCenter(new Vec2(5f, 0f));
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
            player.getBody().applyForceToCenter(new Vec2(5f, 0f));
        }

        if (keycode == Input.Keys.D || keycode == Input.Keys.RIGHT) {
            player.getBody().applyForceToCenter(new Vec2(-5f, 0f));
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
