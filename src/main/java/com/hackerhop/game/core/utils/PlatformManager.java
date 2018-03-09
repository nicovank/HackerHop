package com.hackerhop.game.core.utils;

import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;
import java.util.Random;

public class PlatformManager {


    private static final String TAG = PlatformManager.class.getName();
    // Number of units between each platform
    // (something with the camera is limiting it to only 20, 75 or greater is desired)
    private static final float gridSeparation = 20;

    // Number of platforms that can fit in the scene vertically and horizontally
    // at the given separation
    private static final int xCount = (int) Math.floor(60/gridSeparation);
    private static final int yCount = (int) Math.floor(80/gridSeparation);

    // Maximum deviation from grid center
    private static final int wiggleroom = 7;

    public PlatformManager(){}

    /**
     * Randomly generates Platform objects to be used in GameScreen.
     * Platforms are generated based on grid points.
     * Also generates the base platform.
     * @param w world
     * @return HashSet containing Platform objects
     */
    public HashSet<Platform> generatePlatforms(World w){
        HashSet<Platform> h = new HashSet<>();
        Random r = new Random();
        Platform base = new Platform(w);
        h.add(base);

        for (int i = 1; i < yCount; ++i){

            for (int j = 0; j < xCount; ++j){
                if (r.nextBoolean()) {
                    Platform p = new Platform( (gridSeparation * (j)) + 3.5f + r.nextInt(wiggleroom),
                            gridSeparation * (i) + r.nextInt(wiggleroom), w);
                    h.add(p);
                }
            }
        }

        return h;
    }

//    private class Grid{
//        private float x, y;
//        Grid(float x, float y){
//            this.x = x;
//            this.y = y;
//        }
//
//        float getX(){
//            return x;
//        }
//
//        float getY(){
//            return y;
//        }
//    }

}
