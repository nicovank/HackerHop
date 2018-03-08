package com.hackerhop.game.core.utils;

import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;
import java.util.Random;

public class PlatformManager {

    private final float gridSeparation = 25;
    private final int xCount = (int) Math.floor(540/gridSeparation);
    private final int yCount = (int) Math.floor(720/gridSeparation);
    private Grid[][] grids = new Grid[xCount][yCount];

    public PlatformManager(){}

    public HashSet<Platform> generatePlatforms(World w){
        HashSet<Platform> h = new HashSet<>();
        Random r = new Random();

        for (int i = 0; i < yCount; ++i){
            for (int j = 0; j < xCount; ++j){
                if (r.nextBoolean()) {
                    Platform p = new Platform(gridSeparation * (j), gridSeparation * (i), w);
                    h.add(p);
                }
            }
        }

//        Platform p = new Platform(0, 0, w);
//        h.add(p);

        return h;
    }

    private class Grid{
        private float x, y;
        Grid(float x, float y){
            this.x = x;
            this.y = y;
        }

        float getX(){
            return x;
        }

        float getY(){
            return y;
        }
    }

}
