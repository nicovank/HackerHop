package com.hackerhop.game.core.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;
import java.util.Random;

public class PlatformGroup{

    private static final String TAG = PlatformGroup.class.getName();
    private static final float gridSeparation = 20;

    // Set of platforms
    private Platform[] platforms;
    private float y;
    private int xCount, wiggleRoom;


    /**
     * Creates a new set of platforms, generating initial ones randomly.
     *
     * @param world The Box2D world of the platforms.
     */
    public PlatformGroup(World world, float y, int wiggleRoom) {
        this.wiggleRoom = wiggleRoom;
        this.y = y;
        this.xCount = (int) (60 / gridSeparation);

        platforms = generatePlatforms(world);
    }

    /**
     * <p>Generates Platform objects for this PlatformGroup object.</p>
     * <p>If this PlatformObject is at the base position, i.e. if <code>this.y == 0</code>,
     * 9 Platform objects are generated to be used as the base platform.
     * </p>
     * <p>For all other cases, a maximum of 3 Platform objects are created at random
     * within a circle of radius <code>wiggleRoom</code>. The center of each circle is separated from
     * another by <code>gridSeparation</code> units.
     * </p>
     *
     * @param w the physics world where Platform objects are to be generated
     * @return
     */
    private Platform[] generatePlatforms(World w) {
        Platform[] h;
        Random r = new Random();
        if (y != 0) {
            h = new Platform[xCount];
            for (int i = 0; i < xCount; ++i) {
                if (r.nextBoolean() || r.nextBoolean()) {
                    h[i] = new Platform((gridSeparation * i) + 3.5f + (wiggleRoom - r.nextInt(2 * wiggleRoom)),
                            (y * gridSeparation) + (wiggleRoom - r.nextInt(2 * wiggleRoom)), w);
                }
            }
        } else {
            h = new Platform[9];
            for (int i = 0; i < 54; i += 6) {
                h[i / 6] = new Platform(i, 0, w);
            }
        }
        return h;
    }

    /**
     * Returns the y-value of the central grid
     *
     * @return y-value of the central grid
     */
    public float getY() {
        return y * gridSeparation;
    }

    public Platform[] getPlatforms() {
        return platforms;
    }

    public int getCount() {
        return platforms.length;
    }

    public void loadResources() {
        for (Platform p : platforms) {
            if (p != null) {
                p.loadResources();
            }
        }
    }

    public void dispose() {
        for (Platform p : platforms) {
            if (p != null) p.dispose();
        }
    }

    public void destroy() {
        for (Platform p : platforms) {
            if (p != null) {
                p.destroy();
                p.dispose();
            }
        }
    }

    public void render(SpriteBatch batch) {
        for (Platform p : platforms) {
            if (p != null) p.render(batch);
        }
    }
}
