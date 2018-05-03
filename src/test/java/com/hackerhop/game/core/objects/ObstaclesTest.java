package com.hackerhop.game.core.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hackerhop.game.core.objects.obstacles.Obstacle;
import com.hackerhop.game.core.objects.obstacles.ObstacleGenerator;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static com.hackerhop.game.core.utils.Constants.*;
import static com.hackerhop.game.core.utils.Random.randomFloat;
import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.*;


@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ObstaclesTest {

    World world;
    OrthographicCamera camera;
    ObstacleGenerator spy;

    @BeforeEach
    public void setup() {
        world = new World(new Vec2(0, -50));
        camera = new OrthographicCamera();
        camera.position.set(SCREEN_WIDTH / 2f, SCREEN_HEIGHT / 2f, 0);
        spy = spy(new ObstacleGenerator(world, camera));
        doNothing().when(spy).updateBlinker();
        doNothing().when(spy).loadObstacle(any(Obstacle.class));
        doNothing().when(spy).spawnCoin(anyFloat(), anyFloat());
    }

//    @Disabled
    @Test
    public void Should_CallGenerateObstacle_When_UpdateMethodIsCalled() {
        spy.update();
        verify(spy, atLeastOnce()).generateObstacle();
        verify(spy, atMost(4)).generateObstacle();
    }

//    @Disabled
    @Test
    public void Should_GenerateAtMost4ObstaclesWithinBounds_When_RandomCameraAltitude() {
        camera.position.set(SCREEN_WIDTH / 2, randomFloat(SCREEN_HEIGHT / 2, 5000), 0);
        spy.update();
        Obstacle[] obstacles = spy.getObstacles();
        int count = 0;
        for (Obstacle obstacle : obstacles) {
            if (obstacle != null) {
                ++count;
                assertEquals(obstacle.getBody().getPosition().y
                        , (camera.position.y / PHYSICS_RATIO) + 500
                        , "Obstacles must generate 500 units above current camera position\n" +
                                "(NOTE: Obstacle y-value is calculated by (camera.position.y / PHYSICS_RATIO) + 500)");

                assertTrue(obstacle.getBody().getPosition().x >= 2
                                && obstacle.getBody().getPosition().x <= 47
                        , "Obstacles must generate within the width of the screen");
            }
        }
        assertTrue(count <= 4, "at most 4 Obstacles must be generated");
    }

    //    @Disabled
    @Test
    public void Should_DeleteOutOfBoundObstacles_When_CameraMovesUp() {
        Obstacle[] empty = new Obstacle[10];
        spy.update();
        Obstacle[] obstacles = spy.getObstacles();
        int count = 0;
        for (Obstacle obstacle : obstacles) {
            if (obstacle != null) {
                ++count;
            }
        }
        assertTrue(count > 0, "To delete an Obstacle, first there must BE an Obstacle\n" +
                "That's some Zen shit right there");

        camera.position.set(SCREEN_WIDTH / 2, 10000, 0);
        doNothing().when(spy).generateObstacle();
        doNothing().when(spy).destroyObstacle(any(Obstacle.class));
        spy.update();
        obstacles = spy.getObstacles();

        assertArrayEquals(empty, obstacles, "All out-of-bounds Obstacles must be deleted");

    }

}

