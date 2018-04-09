package com.hackerhop.game.core.objects.obstacles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import org.jbox2d.dynamics.World;

import java.util.Random;

public class ObstacleGenerator implements GraphicsElement {
    private static Obstacle[] obstacles;
    private static int tracker;

    public ObstacleGenerator() {
        tracker = 0;
        obstacles = new Obstacle[2];
    }

    /**
     * <p>Generates obstacles based on the current position of the Player.</p>
     *
     * <p>The x-value for the obstacle is generated at random, with the bound determined from
     * the x-value of the Player, and the distance from the Player to either of the walls, with
     * an upper bound of 260.The obstacles spawn at least 10 units away from either of the walls.</p>
     *
     * <p>The y-value is determined by the y-value of the camera. The obstacles always spawn 400 units
     * above the camera's current position.</p>
     *
     * <p>How long the obstacles last in the world is determined by the method caller. This method
     * only takes care of spawning and de-spawning the obstacles.</p>
     *
     * @param playerX x-value of the Player
     * @param cameraY y-value of the camera
     * @param world the physics world
     */
    public void generate(float playerX, float cameraY, World world) {
        cameraY += 400;

        Random r = new Random();
        if (r.nextBoolean()) {

            int offset = (int) (((playerX - 260) < 10) ? playerX : 260);
            offset = (offset < 0) ? 0 : offset;
            offset = ((playerX + 260) > 530) ? (int) (530 - playerX) : offset;
            offset = (offset < 0) ? 0 : offset;
            offset = (offset > 520) ? 0 : offset;
            if (obstacles[tracker] != null) {
                obstacles[tracker].destroy();
            }
            obstacles[tracker] = new Obstacle(1 + ((playerX - offset) + r.nextInt(1 + (2 * offset)) / 10), cameraY / 10, world);
            obstacles[tracker].loadResources();
            tracker = (tracker < 1) ? 1 : 0;
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
