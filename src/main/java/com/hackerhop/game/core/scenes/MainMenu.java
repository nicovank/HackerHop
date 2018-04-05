package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.hackerhop.game.core.MainController;


public class MainMenu extends Scene {

    private static final String TAG = MainMenu.class.getName();

    //Main Screen Textures
    Texture logo;
    Texture background;
    Sprite sprite1;
    Sprite sprite2;
    Sprite sprite3;
    Sprite sprite4;
    Sprite highScoreButton;
    Sprite gitHubButton;
    Music music;

    public MainMenu(MainController controller) {
        super(controller);
    }

    @Override
    public void update() {

    }

    @Override
    public void loadGraphics() {
        logo = new Texture("mainScreen/Logo.png");
        background = new Texture("background/ShinemanPixel.png");
        sprite1 = new Sprite(new Texture("player/rob.png"));
        sprite1.setPosition(100, 75);
        sprite2 = new Sprite(new Texture("player/Nick.png"));
        sprite2.setPosition(200, 75);
        sprite3 = new Sprite(new Texture("player/Katie.png"));
        sprite3.setPosition(300, 75);
        sprite4 = new Sprite(new Texture("player/Ye.png"));
        sprite4.setPosition(400, 75);
        highScoreButton = new Sprite(new Texture("mainScreen/HighScoreButton.png"));
        highScoreButton.setPosition(75, 325);
        gitHubButton = new Sprite(new Texture("mainScreen/GitHubButton.png"));
        gitHubButton.setPosition(285, 325);
        music = Gdx.audio.newMusic(Gdx.files.internal("Audio/waves.mp3"));

        music.setLooping(true);
        music.play();
    }

    @Override
    public void render(SpriteBatch batch) {

        Gdx.gl.glClearColor(1, .5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Draw next frame (current scene)
        batch.begin();

        batch.draw(background, 0, 0);
        batch.draw(logo, 0, 50);
        sprite1.draw(batch);
        sprite2.draw(batch);
        sprite3.draw(batch);
        sprite4.draw(batch);
        highScoreButton.draw(batch);
        gitHubButton.draw(batch);
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
    public boolean touchDown(int x, int y, int pointer, int button) {
        y = Gdx.graphics.getHeight() - y;
        if (sprite1.getBoundingRectangle().contains(x, y)) {

            MainController controller = super.getController();
            controller.setScene(new GameScene(controller));
        }

        return true;
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
        logo.dispose();
        background.dispose();
        sprite1.getTexture().dispose();
        sprite2.getTexture().dispose();
        sprite3.getTexture().dispose();
        sprite4.getTexture().dispose();
        highScoreButton.getTexture().dispose();
        gitHubButton.getTexture().dispose();

        music.stop();
        music.dispose();
    }


}
