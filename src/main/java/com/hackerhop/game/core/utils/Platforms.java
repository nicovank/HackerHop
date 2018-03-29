package com.hackerhop.game.core.utils;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.Platform;
import org.jbox2d.dynamics.World;

public class Platforms implements GraphicsElement {

    private PlatformGroup[] platformGroups = new PlatformGroup[4];
    private int tracker;
    private static final String TAG = PlatformGroup.class.getName();
//    private World world;

    // Number of units between each platform
    // (something with the camera is limiting it to only 20, 75 or greater is desired)
    // change value in test every time you touch this value
//    private static final float gridSeparation = 20;

    // Number of platforms that can fit in the scene vertically and horizontally
    // at the given separation
//    private static final int xCount = (int) Math.floor(60 / gridSeparation);

    // Maximum deviation from grid center
    // change value in test every time you touch this value
    private static final int wiggleRoom = 8;

    public Platforms(World world) {
//        this.world = world;
        platformGroups[0] = new PlatformGroup(world, 0, wiggleRoom);
        for (int i = 1; i < 4; ++i) {
            platformGroups[i] = new PlatformGroup(world, i, wiggleRoom);
        }
        tracker = 0;
    }

    public void update(float cameraPositionY, World world) {
        if (platformGroups[tracker].getY() <= (cameraPositionY - 370)/10) {
            float tmpY = platformGroups[tracker].getY()/20 + 4;
            platformGroups[tracker].destroy(world);
            PlatformGroup p = new PlatformGroup(world, tmpY, wiggleRoom);
            p.loadGraphics();
            platformGroups[tracker] = p;


            tracker = (tracker < 3) ? ++tracker : 0;
        }
    }

    public void destroyAll(World world){
        for (PlatformGroup p : platformGroups){
            p.destroy(world);
        }
    }

    public int getCount() {
        int count = 0;
        for (PlatformGroup g : platformGroups) {
            count += g.getCount();
        }
        return count;
    }

    @Override
    public void loadGraphics() {
        for (PlatformGroup g : platformGroups) {
            g.loadGraphics();
        }
    }

    @Override
    public void dispose() {
        for (PlatformGroup g : platformGroups) {
            g.dispose();
        }
    }

    public void render(SpriteBatch batch) {

        for (PlatformGroup g : platformGroups) {
            g.render(batch);
        }
    }


}
