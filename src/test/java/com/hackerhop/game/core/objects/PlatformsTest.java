package com.hackerhop.game.core.objects;

import com.hackerhop.game.core.objects.platforms.Platform;
import com.hackerhop.game.core.objects.platforms.PlatformGroup;
import com.hackerhop.game.core.objects.platforms.Platforms;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.spy;

public class PlatformsTest {

    private World world = new World(new Vec2(0, -50));
    private Platforms platforms = new Platforms(world);

    @Test
    void Should_GenerateAtMost21Platforms_When_PlatformsIsInstantiated() {
        int count = platforms.getCount();
        assertTrue(count <= 21, "at most 21 Platform objects must be generated at Platforms instantiation");
    }

    @Test
    void Should_GenerateAtLeast15Platforms_When_PlatformsIsInstantiated() {
        int count = platforms.getCount();
        assertTrue(count >= 15, "at least 15 Platform objects must be generated at Platforms instantiation");
    }

    @Test
    void Should_GeneratePlatformsInsideBoundary_When_AtAllTimes() {
        for (PlatformGroup platformGroup : platforms.getPlatformGroups()) {
            for (Platform platform : platformGroup.getPlatforms()) {
                if (platform != null) {
                    assertTrue(platform.getBody().getPosition().x <= 480
                                    && platform.getBody().getPosition().x >= 0
                                    && platform.getY() >= 0,
                            "Platform objects must spawn between 0 <= x <= 480, y >= 0");
                }
            }
        }
    }

    @Test
    void Should_ReplaceLowestPlatformGroup_When_CameraMovesUp(){
        Platforms spy = spy(new Platforms(world));
        doNothing().when(spy).loadPlatformGroup(any(PlatformGroup.class));
        doNothing().when(spy).destroyPlatformGroup(any(PlatformGroup.class));

        spy.update(505);    // float value high enough to delete base PlatformGroup

        assertTrue(spy.getLowestY() > 0, "Base PlatformGroup must no longer exist");

        PlatformGroup[] platformGroups = spy.getPlatformGroups();

        assertTrue(platformGroups[0].getY() >= 5, "Highest PlatformGroup must replace Base PlatformGroup");
    }

}
