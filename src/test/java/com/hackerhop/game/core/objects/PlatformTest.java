package com.hackerhop.game.core.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlatformTest {
  //TODO: Figure out null pointer exception when instantiating a platform
    Vec2 worldVec;
    World w;
    float x1, y1, x2, y2;
    Platform a, b;
    double distance;

    @Before
    void setUp(){
        worldVec = new Vec2(0, -50);
        w = new World(worldVec);
        x1 = 10;
        y1 = 20;
        x2 = 30;
        y2 = 40;
        b = new Platform(x2, y2, w);
        a = new Platform(x1, y1, w);
        distance = Math.sqrt(Math.pow((30 - 10), 2) + Math.pow((40 - 20), 2));
    }

    @Test
    void distanceTest() {
        setUp();
        assertEquals(distance, b.distanceTo(a));
    }
}
