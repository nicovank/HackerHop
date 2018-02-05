package com.csc380.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

/**
 * This class is an interface for creating more scenes.
 */
public abstract class Scene implements Disposable {

    private static final String TAG = Scene.class.getName();

    /**
     * Updates the physics, character positions, etc.
     */
    public abstract void update();

    /**
     * Renders the scenes to be drawn
     * @param batch where the scene will be rendered
     */
    public abstract void render(SpriteBatch batch);

}
