package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.MainController;

//This will be between MainMenu and GameScene, explaining controlls and other Game Mechanics
//Will be very simple, The constructor carrys over the Character picked in main menu to GameScene so it should be easily implemented.

public class HelpScene extends Scene {
    public HelpScene(MainController controller,Character character) {
        super(controller);

    }

    @Override
    public void update() {

    }

    @Override
    public boolean keyDown(int i) {
        return false;
    }

    @Override
    public boolean keyUp(int i) {
        return false;
    }

    @Override
    public boolean keyTyped(char c) {
        return false;
    }

    @Override
    public boolean touchDown(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchUp(int i, int i1, int i2, int i3) {
        return false;
    }

    @Override
    public boolean touchDragged(int i, int i1, int i2) {
        return false;
    }

    @Override
    public boolean mouseMoved(int i, int i1) {
        return false;
    }

    @Override
    public boolean scrolled(int i) {
        return false;
    }

    @Override
    public void loadResources() {

    }

    @Override
    public void render(SpriteBatch batch) {

    }

    @Override
    public void dispose() {

    }
}
