package com.hackerhop.game.core.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;
import java.util.Random;

public class PlatformGroup extends GeneralPlatformGroup {

    private int gridSeparation, wiggleRoom, y;
    private static int xCount;

    /**
     * Creates a new set of platforms, generating initial ones randomly.
     *
     * @param world The Box2D world of the platforms.
     */
    public PlatformGroup(World world, int gridSeparation, int y, int w) {
        super(world);
        this.gridSeparation = gridSeparation;
        this.y = y;
        this.wiggleRoom = w;
        xCount = (int) Math.floor(60 / gridSeparation);
    }

    @Override
    public Platform[] generatePlatforms(World w) {
        Platform[] h = new Platform[xCount];
        Random r = new Random();

        for (int j = 0; j < xCount; ++j) {
            if (r.nextInt(3) < 2) {
                h[j] = new Platform((gridSeparation * (j)) + 3.5f + (wiggleRoom - r.nextInt(2 * wiggleRoom)),
                        y + (wiggleRoom - r.nextInt(2 * wiggleRoom)), w);
            }
        }

        return h;
    }
}
