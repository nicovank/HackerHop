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

    private World world;

    public Platforms(World world) {
        this.world = world;
        platformGroups[0] = new PlatformGroup(world, 0, WIGGLE_ROOM);
        for (int i = 1; i < 5; ++i) {
            platformGroups[i] = new PlatformGroup(world, i, WIGGLE_ROOM);
        }

        // regenerate the third highest PlatformGroup if it is empty
        // used later to make sure Platform objects are not spawned too far apart
        while (platformGroups[2].isEmpty()){
            destroyPlatformGroup(platformGroups[2]);
            platformGroups[2] = new PlatformGroup(world, 2, WIGGLE_ROOM);
            loadPlatformGroup(platformGroups[2]);
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
     */
    public void update(float cameraPositionY) {
        if (platformGroups[tracker].getY() <= (cameraPositionY - THRESHOLD) / PHYSICS_RATIO) {
            float tmpY = platformGroups[tracker].getY() / 20 + GROUP_COUNT;
            destroyPlatformGroup(platformGroups[tracker]);
            PlatformGroup p = new PlatformGroup(world, tmpY, WIGGLE_ROOM);
            loadPlatformGroup(p);
            platformGroups[tracker] = p; // potential new highest PlatformGroup

            // indexes of PlatformGroups in platformGroups array
            int g1, g2, g3, g4;
            g1 = ((tracker + 1 > 4)) ? tracker - 4 : tracker + 1; // new lowest PlatformGroup
            g2 = ((g1 + 1 > 4)) ? g1 - 4 : g1 + 1;
            g3 = ((g2 + 1 > 4)) ? g2 - 4 : g2 + 1;
            g4 = ((g3 + 1 > 4)) ? g3 - 4 : g3 + 1;

            // regenerate the new PlatformGroup if the 3 highest PlatformGroups are empty
            // ensures that there is no more than 2 empty PlatformGroups between each non-empty PlatformGroup
            if (!platformGroups[g2].isEmpty()){
                if (platformGroups[g3].isEmpty() && platformGroups[g4].isEmpty()){
                    // regenerate new highest PlatformGroup while top 3 PlatformGroups are empty
                    while(platformGroups[tracker].isEmpty()){
                        destroyPlatformGroup(platformGroups[tracker]);
                        platformGroups[tracker] = new PlatformGroup(world, tmpY, WIGGLE_ROOM); // new highest PlatformGroup
                        loadPlatformGroup(platformGroups[tracker]);
                    }
                }
            }

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

    public void loadPlatformGroup(PlatformGroup platformGroup){
        platformGroup.loadResources();
    }

    public void destroyPlatformGroup(PlatformGroup platformGroup){
//        platformGroup.dispose();
        platformGroup.destroy();
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
        destroyAll();
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
