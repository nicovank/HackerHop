package com.hackerhop.game.core.objects;

import com.hackerhop.game.core.objects.platforms.Platform;
import com.hackerhop.game.core.objects.platforms.PlatformGroup;
import com.hackerhop.game.core.objects.platforms.Platforms;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PlatformsTest {

    private World world = new World(new Vec2(0, -50));
    private Platforms platforms = new Platforms(world);

    @Test
    void platformsShouldGenerateAtMost21Platforms() {
        int count = platforms.getCount();
        assertTrue(count <= 21, "at most 21 Platform objects must be generated");
    }

    @Test
    void platformsShouldGenerateAtLeast10Platforms() {
        int count = platforms.getCount();
        assertTrue(count >= 15, "at least 15 Platform objects must be generated");
    }

    @Test
    void platformsShouldStayInsideBoundary() {
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

}
