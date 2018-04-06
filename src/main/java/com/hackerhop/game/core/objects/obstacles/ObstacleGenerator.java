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

    public void generate(float playerX, float cameraY, World world) {
        cameraY += 400;

        Random r = new Random();
        if (r.nextBoolean()) {
//            int offset = 0;
        int offset = (int) (((playerX * 10 - 260) < 0) ? playerX : 260);
        offset = (offset < 0) ? 0 : offset;
        offset = ((playerX * 10 + 260) > 520) ? (int) (playerX) : offset;
        offset = (offset > 520) ? 0 : offset;
        if (obstacles[tracker] != null) {
            obstacles[tracker].destroy();
        }
        if (offset < 0) {
            System.out.println();
        }
        obstacles[tracker] = new Obstacle(((playerX - offset) + r.nextInt(1 + (2 * offset)) / 10), cameraY / 10, world);
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
