package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.MainController;

public class GameOverScene extends Scene {

    private TextureRegion background;

    public GameOverScene(MainController controller) {
        super(controller);
    }

    @Override
    public void update() {

    }

    @Override
    public void loadGraphics() {
        background = new TextureRegion(new Texture("background/GameOver.png"));
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(background, 0, 300, 540, 700 );
        batch.end();
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
    public void dispose() {
        background.getTexture().dispose();
    }
}
