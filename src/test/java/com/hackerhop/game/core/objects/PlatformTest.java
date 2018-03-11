package com.hackerhop.game.core.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlatformTest {
  //TODO: Figure out null pointer exception when instantiating a platform
      @Test
    void distanceTest() {
        Vec2 worldVec = new Vec2(0, -50);
        World w = new World(worldVec);
        float x1 = 10;
        float y1 = 20;
        float x2 = 30;
        float y2 = 40;
        Platform b = new Platform(x2, y2, w);
        Platform a = new Platform(x1, y1, w);
        double distance = Math.sqrt(Math.pow((30 - 10), 2) + Math.pow((40 - 20), 2));
        assertEquals(distance, b.distanceTo(a));
    }
}
