package com.hackerhop.game.core.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import org.jbox2d.dynamics.World;

public class Platforms implements GraphicsElement{

    GeneralPlatformGroup[] platformGroups = new GeneralPlatformGroup[4];
    int tracker;
    private static final String TAG = PlatformGroup.class.getName();

    // Number of units between each platform
    // (something with the camera is limiting it to only 20, 75 or greater is desired)
    // change value in test every time you touch this value
    private static final float gridSeparation = 20;

    // Number of platforms that can fit in the scene vertically and horizontally
    // at the given separation
//    private static final int xCount = (int) Math.floor(60 / gridSeparation);

    // Maximum deviation from grid center
    // change value in test every time you touch this value
    private static final int wiggleRoom = 8;

    public Platforms(World world) {
        platformGroups[0] = new BaseGroup(world);
        for (int i = 1; i < 4; ++i) {
            platformGroups[i] = new PlatformGroup(world, 20, (int) gridSeparation * i, wiggleRoom);
        }
    }

    public void update(){

    }

    public int getCount() {
        int count = 0;
        for(GeneralPlatformGroup g : platformGroups){
            count += g.getCount();
        }
        return count;
    }

    @Override
    public void loadGraphics() {
        for(GeneralPlatformGroup g : platformGroups){
           g.loadGraphics();
        }
    }

    @Override
    public void dispose() {
        for(GeneralPlatformGroup g : platformGroups){
            g.dispose();
        }
    }

    public void render(SpriteBatch batch) {
        for(GeneralPlatformGroup g : platformGroups){
            g.render(batch);
        }
    }


}
