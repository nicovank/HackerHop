package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.MainController;

/**
 * Menu scene.
 *
 * InputProcessor's JavaDoc has been copied from LibGDX's Documentation for easier access.
 */
public class Menu extends Scene implements InputProcessor {


    private static final String TAG = Scene.class.getName();

    public Menu(MainController controller) {
        super(controller);
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void dispose() {

    }


    @Override
    public void loadGraphics() {

    }

    /**
     * Called when a key is pressed.
     *
     * @param keycode The code of the pressed key.
     * @return whether the input was processed.
     */
    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    /**
     * Called when a key is released.
     *
     * @param keycode The code of the released key.
     * @return whether the input was processed.
     */
    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    /**
     * Called when a key is typed.
     *
     * @param character The character typed.
     * @return whether the input was processed.
     */
    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was pressed.
     *
     * @param screenX the x-coordinate.
     * @param screenY the y-coordinate.
     * @param pointer the pointer for the event.
     * @param button which button was pressed.
     * @return whether the input was processed.
     */
    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when the screen was touched or a mouse button was released.
     *
     * @param screenX the x-coordinate.
     * @param screenY the y-coordinate.
     * @param pointer the pointer for the event.
     * @param button which button was pressed.
     * @return whether the input was processed.
     */
    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    /**
     * Called when a finger or the mouse was dragged.
     *
     * @param screenX the x-coordinate.
     * @param screenY the y-coordinate.
     * @param pointer the pointer for the event.
     * @return whether the input was processed.
     */
    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    /**
     * Called when the mouse was moved without any buttons being pressed. Will not be called on iOS.
     *
     * @param screenX the x-coordinate.
     * @param screenY the y-coordinate.
     * @return whether the input was processed.
     */
    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    /**
     * Called when the mouse wheel was scrolled. Will not be called on iOS.
     *
     * @param amount the scroll amount, -1 or 1 depending on the direction the wheel was scrolled.
     * @return whether the input was processed.
     */
    @Override
    public boolean scrolled(int amount) {
        return false;
    }
}
