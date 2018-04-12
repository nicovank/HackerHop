package com.hackerhop.game.core.objects.obstacles;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.scenes.GameScene;
import com.hackerhop.game.core.utils.Constants;
import com.hackerhop.game.core.utils.blinkers.SpriteBlinker;
import org.jbox2d.dynamics.World;

import static com.hackerhop.game.core.utils.Methods.randomFloat;
import static com.hackerhop.game.core.utils.Methods.randomInt;

public class ObstacleGenerator implements GraphicsElement, Constants {
	private static final String TAG = GameScene.class.getName();

	// Never more than 5 obstacles (arbitrary)
	private Obstacle[] obstacles = new Obstacle[5];
	private int obstacleCount = 0;

	private World world;
	private Camera camera;

	private SpriteBlinker blinker;
	private Sprite arrow;

	public ObstacleGenerator(World world, Camera camera) {
		this.world = world;
		this.camera = camera;
	}

	/**
	 * Will check if obstacles need deletion, and spawn new ones.
	 */
	public void update() {

		blinker.update();

		// 1. Check if obstacles need deletion
		for (int i = 0; i < obstacles.length; ++i) {
			Obstacle obstacle = obstacles[i];

			if (obstacle != null) {
				float obstacleY = obstacle.getBody().getPosition().y * PHYSICS_RATIO;
				float boundary = camera.position.y - SCREEN_HEIGHT;

				if (obstacleY < boundary) {
					obstacle.dispose();
					obstacle.destroy();
					obstacles[i] = null;
					--obstacleCount;
				}
			}
		}

		// 2. generate between 1 and 4 obstacles.
		if (obstacleCount == 0) {
			for (int i = randomInt(5); i > 0; --i) {
				generateObstacle();
			}
		}
	}

	/**
	 * Generates a new obstacle if we have not yet reached the maximum number of obstacles.
	 */
	public void generateObstacle() {
		++obstacleCount;
		float x = randomFloat(5f, (SCREEN_WIDTH / PHYSICS_RATIO) - 5f);
		float y = (SCREEN_HEIGHT / PHYSICS_RATIO) + 500;

		Obstacle obstacle = new Obstacle(x, y, world);
		obstacle.loadResources();

		for (int i = 0; i < obstacles.length; ++i) {
			if (obstacles[i] == null) {
				obstacles[i] = obstacle;
				break;
			}
		}
	}

	@Override
	public void loadResources() {
		arrow = new Sprite(new Texture("obstacles/arrow.png"));
		blinker = new SpriteBlinker(arrow, .5f, .5f);
	}

	@Override
	public void render(SpriteBatch batch) {
		for (Obstacle obstacle : obstacles) {
			if (obstacle != null) {
				if (obstacle.getBody().getPosition().y > ((camera.position.y + (SCREEN_HEIGHT / 2)) / PHYSICS_RATIO)) {
					blinker.render(batch,
							obstacle.getBody().getPosition().x * PHYSICS_RATIO,
							camera.position.y + (SCREEN_HEIGHT / 2) - 50
					);
				} else {
					obstacle.render(batch);
				}
			}
		}
	}

	@Override
	public void dispose() {

		arrow.getTexture().dispose();

		for (Obstacle obstacle : obstacles) {
			if (obstacle != null) {
				obstacle.destroy();
				obstacle.dispose();
			}
		}
	}
}
