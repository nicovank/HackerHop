package com.hackerhop.game.core.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;

public abstract class GeneralPlatformGroup implements GraphicsElement {
    private static final String TAG = PlatformGroup.class.getName();

    // Number of units between each platform
    // (something with the camera is limiting it to only 20, 75 or greater is desired)
    // change value in test every time you touch this value
//    private static final float gridSeparation;

    // Number of platforms that can fit in the scene vertically and horizontally
    // at the given separation
//    private static final int xCount = (int) Math.floor(60 / gridSeparation);
//    private static final int yCount = (int) Math.floor(80 / gridSeparation);

    // Maximum deviation from grid center
    // change value in test every time you touch this value
//    private static final int wiggleroom = 8;

    // Set of platforms
    private Platform[] platforms;

    /**
     * Creates a new set of platforms, generating initial ones randomly.
     *
     * @param world The Box2D world of the platforms.
     */
    public GeneralPlatformGroup(World world) {
        platforms = generatePlatforms(world);
    }

    abstract Platform[] generatePlatforms(World w);

    public Platform[] getPlatforms() {
//        Platform[] platforms = new Platform[this.platforms.size()];
//        this.platforms.toArray(platforms);
        return platforms;
    }

    public int getCount() {
        return platforms.length;
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
