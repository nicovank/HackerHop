package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.Game;

public class MainMenu extends Scene{

    Game Game;

    //Main Screen Textures
    Texture logo;
    Texture background;
    Texture sprite1;
    Texture sprite2;
    Texture sprite3;
    Texture sprite4;
    Texture highScoreButton;
    Texture textDisplay;


    public MainMenu(Game controller) {
        super(controller);
        this.Game = Game;
        logo = new Texture("mainScreen/Logo.png");
        background = new Texture("background/ShinemanPixel.png");
        sprite1 = new Texture("");
        sprite2 = new Texture("");
        sprite3 = new Texture("");
        sprite4 = new Texture("");
        highScoreButton = new Texture("");
        textDisplay = new Texture("");

    }

    @Override
    public void update() {

    }

    @Override
    public void loadGraphics() {

    }

    @Override
    public void render(SpriteBatch batch) {

        Gdx.gl.glClearColor(1, .5f, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        // Draw next frame (current scene)
        batch.begin();
        //BACKGROUND IMPORT
        batch.draw(background, 0, 0);
        batch.draw(logo,0,0);
        batch.end();
        //ADD BATCH.DRAW FOR ALL OTHER TEXTURES AS WELL!
//        batch.begin();
//        player.render(batch);
//        batch.end();

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

    }
}
