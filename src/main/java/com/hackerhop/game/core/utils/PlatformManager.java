package com.hackerhop.game.core.utils;

import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

import java.util.HashSet;

public class PlatformManager {

    private final float gridSeparation = 150;
    private final int xCount = (int) Math.floor(540/gridSeparation);
    private final int yCount = (int) Math.floor(720/gridSeparation);
    private Grid[][] grids = new Grid[xCount][yCount];

    public PlatformManager(){}

    public HashSet<Platform> generatePlatforms(World w){
        HashSet<Platform> h = new HashSet<>();

        for (int i = 0; i < yCount; ++i){
            for (int j = 0; j < xCount; ++j){
                Platform p = new Platform(gridSeparation * (j + 1), gridSeparation * (i + 1), w);
                h.add(p);
            }
        }

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
