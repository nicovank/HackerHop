package com.hackerhop.game.core.objects;

import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class PlatformTest {
    @Test
    void distanceTest() {
        World w = new World(new Vec2(0, -50));
        float x = 10;
        float y = 20;
        float x2 = 30;
        float y2 = 40;
        Platform a = new Platform(x, y, w);
        Platform b = new Platform(x2, y2, w);
        double distance = Math.sqrt(Math.pow((30 - 10), 2) + Math.pow((40 - 20), 2));
        assertEquals(distance, b.distanceTo(a));
    }
}
