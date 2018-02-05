package com.csc380.game.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.csc380.game.Game;

/**
 * This class is an interface for creating more scenes.
 */
public abstract class Scene implements Disposable {

    private static final String TAG = Scene.class.getName();

    private Game controller;

    public Scene(Game controller) {
        this.controller = controller;
    }

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
