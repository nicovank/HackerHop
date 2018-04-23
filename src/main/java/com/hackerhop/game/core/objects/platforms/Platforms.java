package com.hackerhop.game.core.objects.platforms;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.utils.Constants;
import org.jbox2d.dynamics.World;

public class Platforms implements GraphicsElement, Constants {

    // used in update(World world) method
    private static final String TAG = PlatformGroup.class.getName();
    // tracks lowest PlatformGroup
    private static int tracker;
    // only 4 PlatformGroup objects, cycled through as player advances
    private PlatformGroup[] platformGroups = new PlatformGroup[GROUP_COUNT];

    public Platforms(World world) {
        platformGroups[0] = new PlatformGroup(world, 0, WIGGLE_ROOM);
        for (int i = 1; i < 5; ++i) {
            platformGroups[i] = new PlatformGroup(world, i, WIGGLE_ROOM);
        }
        tracker = 0;
    }

    /**
     * <p>Checks if the y-value of the PlatformGroup at <code>tracker</code> is below
     * the y-value of the <code>Camera</code> by more than <code>THRESHOLD</code> units.
     * </p>
     * <p>If the PlatformGroup is lower than the specified threshold, then it is replaced
     * with a new PlatformGroup object above the current highest PlatformGroup object,
     * and increments the tracker by 1. All Platform objects in the old PlatformGroup are
     * destroyed from <code>world</code> before PlatformGroup is replaced.
     * </p>
     *
     * @param cameraPositionY the y-value of the camera used to check against the y-value
     *                        of PlatformGroup objects
     * @param world           the physics world from where Platform objects reside
     */
    public void update(float cameraPositionY, World world) {
        if (platformGroups[tracker].getY() <= (cameraPositionY - THRESHOLD) / 10) {
            float tmpY = platformGroups[tracker].getY() / 20 + GROUP_COUNT;
            platformGroups[tracker].destroy();
            PlatformGroup p = new PlatformGroup(world, tmpY, WIGGLE_ROOM);
            p.loadResources();
            platformGroups[tracker] = p;


            tracker = (tracker < GROUP_COUNT - 1) ? ++tracker : 0;
        }
    }

    /**
     * Does what it says on the tin - destroys all physical objects from <code>world</code>.
     */
    public void destroyAll() {
        for (PlatformGroup p : platformGroups) {
            p.destroy();
        }
    }

    public float getLowestY() {
        return platformGroups[tracker].getY();
    }

    /**
     * Counts the total number of Platform objects in the world
     *
     * @return number of Platform objects in the physics world
     */
    public int getCount() {
        int count = 0;
        for (PlatformGroup g : platformGroups) {
            count += g.getCount();
        }
        return count;
    }

    @Override
    public void loadResources() {
        for (PlatformGroup p : platformGroups) {
            p.loadResources();
        }
    }

    @Override
    public void dispose() {
        for (PlatformGroup g : platformGroups) {
            g.dispose();
        }
    }

    @Override
    public void render(SpriteBatch batch) {
        for (PlatformGroup g : platformGroups) {
            g.render(batch);
        }
    }

    public PlatformGroup[] getPlatformGroups(){
        return platformGroups;
    }
}
