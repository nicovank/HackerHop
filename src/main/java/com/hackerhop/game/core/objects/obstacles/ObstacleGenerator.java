package com.hackerhop.game.core.objects.obstacles;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.scenes.GameScene;
import com.hackerhop.game.core.utils.Constants;
import org.jbox2d.dynamics.World;

import static com.hackerhop.game.core.utils.Methods.randomFloat;

public class ObstacleGenerator implements GraphicsElement, Constants {
	private static final String TAG = GameScene.class.getName();

	// Never more than 10 obstacles (arbitrary)
	private static Obstacle[] obstacles = new Obstacle[10];

	// private static int tracker;
	private World world;

	public ObstacleGenerator(World world) {
		this.world = world;
		// tracker = 0;
	}

	/**
	 * Will check if obstacles need deletion, and maybe spawn new ones.
	 *
	 * @param camera the camera associated with the scene.
	 */
	public void update(Camera camera) {
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
				}
			}
		}
	}

	/**
	 * Generates a new obstacle if we have not yet reached the maximum number of obstacles.
	 *
	 * @param camera the camera associated with the scene.
	 */
	public void generateObstacle(Camera camera) {
		float x = randomFloat(5f, (SCREEN_WIDTH / 10f) - 5f);
		float y = 70;

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
		for (Obstacle obstacle : obstacles) {
			if (obstacle != null) {
				obstacle.loadResources();
			}
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		for (Obstacle obstacle : obstacles) {
			if (obstacle != null) {
				obstacle.render(batch);
			}
		}
	}

	@Override
	public void dispose() {
		for (Obstacle obstacle : obstacles) {
			if (obstacle != null) {
				obstacle.destroy();
				obstacle.dispose();
			}
		}
	}
}
