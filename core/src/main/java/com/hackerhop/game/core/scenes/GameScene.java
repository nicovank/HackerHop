package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.hackerhop.game.core.Game;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

/**
 * This scene is the "main" game, with the scrolling platforms and the player.
 */
public class GameScene extends Scene {

    //ShapeRenderer
    ShapeRenderer renderer = new ShapeRenderer();

    private Array<Platform> platforms;


    // Our physics world
    private World world;

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

        platforms = new Array<Platform>();
        platforms.add(new Platform(70, 20, 10, 10));

        //Rectangles are filled shapes
        renderer.begin(ShapeRenderer.ShapeType.Filled);
        //Render each platform in the platform array
        for(Platform p: platforms){
            p.rectRender(renderer);
        }
        renderer.end();

    }

    /**
     * Disposes of all the textures and other objects used in the scene.
     */
    @Override
    public void dispose() {

    }
}
