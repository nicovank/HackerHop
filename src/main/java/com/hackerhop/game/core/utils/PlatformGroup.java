package com.hackerhop.game.core.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;
import java.util.Random;

public class PlatformGroup implements GraphicsElement {

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

    private Platform[] generatePlatforms(World w) {
        Platform[] h;
        Random r = new Random();
        if (y != 0) {
            h = new Platform[xCount];
            for (int i = 0; i < xCount; ++i) {
                h[i] = new Platform((gridSeparation * i) + 3.5f + (wiggleRoom - r.nextInt(2 * wiggleRoom)),
                        (y * gridSeparation) + (wiggleRoom - r.nextInt(2 * wiggleRoom)), w);
            }
        } else {
            h = new Platform[9];
            for (int i = 0; i < 54; i += 6) {
                h[i / 6] = new Platform(i, 0, w);
            }
        }
        return h;
    }

    public float getY(){
        return y * gridSeparation;
    }

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

    public void destroy(World world){
        for (Platform p : platforms){
            p.destroy(world);
        }
    }

    public void render(SpriteBatch batch) {
        for (Platform p : platforms) {
            p.rectRender(batch);
        }
    }
}
