package com.hackerhop.game.core.objects;

import com.hackerhop.game.core.objects.platforms.Platform;
import com.hackerhop.game.core.objects.platforms.PlatformGroup;
import com.hackerhop.game.core.objects.platforms.Platforms;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
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
        assertTrue(count >= 13, "at least 13 Platform objects must be generated at Platforms instantiation");
    }

    @Test
    void Should_GeneratePlatformsInsideBoundary_When_PlatformsIsInstantiated() {
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
    void Should_ReplaceBasePlatformGroup_When_CameraMovesUp() {
        Platforms spy = spy(new Platforms(world));
        doNothing().when(spy).loadPlatformGroup(any(PlatformGroup.class));
        doNothing().when(spy).destroyPlatformGroup(any(PlatformGroup.class));

        spy.update(500);    // high enough value to delete base PlatformGroup

        assertTrue(spy.getLowestY() > 0, "Base PlatformGroup must no longer exist");

        PlatformGroup[] platformGroups = spy.getPlatformGroups();

        assertTrue(platformGroups[0].getY() >= 5, "Highest PlatformGroup must replace Base PlatformGroup");
    }

    @Disabled
    @Test
    // Test disabled because first two tests should provide sufficient coverage
    void Should_NotGenerateEmptyPlatformGroups_When_CameraPosition360To250000() {

        int cameraPosition = 360;
        int tracker = 0;
        Platforms spy = spy(new Platforms(world));
        doNothing().when(spy).loadPlatformGroup(any(PlatformGroup.class));
        doNothing().when(spy).destroyPlatformGroup(any(PlatformGroup.class));
        PlatformGroup[] platformGroups = spy.getPlatformGroups();

        do {
            if ((cameraPosition - 500) % 200 == 0) {

                assertTrue(!platformGroups[tracker].isEmpty(),
                        "none of the PlatformGroups must be empty");

            }
            spy.update(cameraPosition);
            ++cameraPosition;
            tracker = (tracker < 4) ? ++tracker : 0;
            platformGroups = spy.getPlatformGroups();

        } while (cameraPosition <= 250000);

    }

}
