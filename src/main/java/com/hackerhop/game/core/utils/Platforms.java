package com.hackerhop.game.core.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;
import java.util.Random;

public class Platforms implements GraphicsElement {

    private static final String TAG = Platforms.class.getName();

    // Number of units between each platform
    // (something with the camera is limiting it to only 20, 75 or greater is desired)
    private static final float gridSeparation = 20;

    // Number of platforms that can fit in the scene vertically and horizontally
    // at the given separation
    private static final int xCount = (int) Math.floor(60 / gridSeparation);
    private static final int yCount = (int) Math.floor(80 / gridSeparation);

    // Maximum deviation from grid center
    private static final int wiggleroom = 7;

    // Set of platforms
    private HashSet<Platform> platforms;

    /**
     * Creates a new set of platforms, generating initial ones randomly.
     *
     * @param world The Box2D world of the platforms.
     */
    public Platforms(World world) {
        platforms = generatePlatforms(world);
    }

    /**
     * Randomly generates Platform objects to be used in GameScreen.
     * Platforms are generated based on grid points.
     * Also generates the base platform.
     *
     * @param w world
     * @return HashSet containing Platform objects
     */
    private static HashSet<Platform> generatePlatforms(World w) {
        HashSet<Platform> h = new HashSet<>();
        Random r = new Random();
        // "hacky", floor creation, let's find a better way later.
//        do {
            for (int i = 0; i < 54; i += 6) {
                Platform base = new Platform(i, 0, w);
                h.add(base);
            }

            for (int i = 1; i < yCount; ++i) {

                for (int j = 0; j < xCount; ++j) {
                    if (r.nextBoolean() || i%3 == 0) {
                        Platform p = new Platform((gridSeparation * (j)) + 3.5f + r.nextInt(wiggleroom),
                                gridSeparation * (i) + r.nextInt(wiggleroom), w);
                        h.add(p);
                    }
                }
            }
//        } while (h.size() < 13);

        return h;
    }

    public Platform[] getPlatforms() {
        Platform[] platforms = new Platform[this.platforms.size()];
        this.platforms.toArray(platforms);
        return platforms;
    }

    public int getCount() {
        return platforms.size();
    }

    @Override
    public void loadGraphics() {
        for (Platform p : platforms) {
            p.loadGraphics();
        }
    }

    @Override
    public void dispose() {
        for (Platform p : platforms) {
            p.dispose();
        }
    }

    public void render(SpriteBatch batch) {
        for (Platform p : platforms) {
            p.rectRender(batch);
        }
    }
}
