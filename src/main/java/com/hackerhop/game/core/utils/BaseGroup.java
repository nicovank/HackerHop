package com.hackerhop.game.core.utils;

import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;

public class BaseGroup extends GeneralPlatformGroup {
    /**
     * Creates a new set of platforms, generating initial ones randomly.
     *
     * @param world The Box2D world of the platforms.
     */
    public BaseGroup(World world) {
        super(world);
    }

    @Override
    public Platform[] generatePlatforms(World w) {
        Platform[] h = new Platform[9];
        for (int i = 0; i < 54; i += 6) {
            h[i/6] = new Platform(i, 0, w);
        }
        return h;
    }
}
