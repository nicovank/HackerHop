package com.hackerhop.game.core.utils;

import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlatformGroupTest {
//    @Test
//    void platformMinTest() {
//        World world = new World(new Vec2(0, -50));
//        PlatformGroup pm = new PlatformGroup(world);
//        int i = pm.getCount();
//        // change these values every time you mess with gridSeparation and wiggleroom in PlatformGroup
//        assertTrue(i >= 13, "Insufficient number of platforms generated");
//    }
//
//    @Test
//    void platformMaxTest() {
//        World world = new World(new Vec2(0, -50));
//        PlatformGroup pm = new PlatformGroup(world);
//        int i = pm.getCount();
//        // change these values every time you mess with gridSeparation and wiggleroom in PlatformGroup
//        assertTrue(i <= 21, "Too many platforms generated");
//    }
//
//    @Test
//    void withinBoundsTest() {
//        World world = new World(new Vec2(0, -50));
//        PlatformGroup pm = new PlatformGroup(world);
//
//        for (Platform p : pm.getPlatforms()) {
//            float x = p.getBody().getPosition().x;
//            float y = p.getBody().getPosition().y;
//            assertTrue(x >= 0 && x <= 54);
//            assertTrue(y >= 0 && y <= 72);
//        }
//    }

//    @Test
//    // checks if number of platforms at the floor position are greater than 0 and less than 10
//    // Why 10? idk
//    void floorTest() {
//        World world = new World(new Vec2(0, -50));
//        PlatformGroup pm = new PlatformGroup(world);
//        int i = (int) Arrays.stream(pm.getPlatforms()).filter(p -> p.getY() == 0).count();
//        assertTrue(i > 0 && i < 10);
//    }
}
