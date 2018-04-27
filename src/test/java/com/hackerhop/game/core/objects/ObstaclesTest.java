package com.hackerhop.game.core.objects;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.hackerhop.game.core.objects.obstacles.ObstacleGenerator;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.Test;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.TestInstance;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.*;

//@RunWith(MockitoJUnitRunner.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class ObstaclesTest {
//
//    World world ;
//    OrthographicCamera camera;
//    ObstacleGenerator obstacleGenerator;
//    ObstacleGenerator spy;

    World world = new World(new Vec2(0, -50));
    OrthographicCamera camera = new OrthographicCamera();
    ObstacleGenerator obstacleGenerator = new ObstacleGenerator(world, camera);
    ObstacleGenerator spy = spy(obstacleGenerator);

    @BeforeAll
    public void setupMock(){
//        world = new World(new Vec2(0, -50));
//        camera = new OrthographicCamera();
//        obstacleGenerator = new ObstacleGenerator(world, camera);
        System.out.println("Before all");
    }

    @Test
    public void updateMethodShouldCallGenerateObstacles() {
        System.out.println("test actual");
        spy = spy(obstacleGenerator);
        doNothing().when(spy).updateBlinker();
        doNothing().when(spy).generateObstacle();
        spy.update();
        verify(spy, atLeastOnce()).generateObstacle();
    }

}
