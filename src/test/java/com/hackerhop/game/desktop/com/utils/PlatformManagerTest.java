package com.hackerhop.game.desktop.com.utils;

import com.hackerhop.game.core.objects.Platform;
import com.hackerhop.game.core.utils.PlatformManager;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Assertions;

import java.util.HashSet;


public class PlatformManagerTest {

    @Test
    public void capacityTest(){
        World world = new World(new Vec2(0, -50));
        PlatformManager pm = new PlatformManager();
        Assertions.assertTrue(pm != null);
        HashSet<Platform> platforms = pm.generatePlatforms(world);
        Assertions.assertTrue(platforms.size() <= 10,
                "PlatformManager does not match expected size");
    }

    @Test
    public void basePlatformInclusionTest(){
        World world = new World(new Vec2(0, -50));
        PlatformManager pm = new PlatformManager();
        HashSet<Platform> platforms = pm.generatePlatforms(world);
        Platform p = new Platform(world);
        Assertions.assertTrue(platforms.contains(p), "PlatformManager not contain base platform");
    }

}
