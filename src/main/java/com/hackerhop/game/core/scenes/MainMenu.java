package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.Game;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;


public class MainMenu extends Scene{

    Game controller;
    private static final String TAG = MainMenu.class.getName();

    //Main Screen Textures
    Texture logo = new Texture("mainScreen/Logo.png");
    Texture background = new Texture("background/ShinemanPixel.png");
    Texture sprite1 = new Texture("player/rob.png");
    Texture sprite2 = new Texture("player/Nick.png");
    Texture sprite3 = new Texture("player/Katie.png");
    Texture sprite4 = new Texture("player/Ye.png");
    Texture highScoreButton = new Texture("mainScreen/HighScoreButton.png");
    Texture gitHubButton = new Texture("mainScreen/GitHubButton.png");
    Texture textDisplay;
    Music music = Gdx.audio.newMusic(Gdx.files.internal("Audio/waves.mp3"));



    private OrthographicCamera camera;


    public MainMenu(Game controller) {
        super(controller);
        this.controller = controller;
        music.setLooping(true);
        music.play();

        float w = Gdx.graphics.getWidth();
        float h = Gdx.graphics.getHeight();
        camera = new OrthographicCamera(w, h);
        camera = new OrthographicCamera(w, h);
        camera.position.set(camera.viewportWidth / 2f, camera.viewportHeight / 2f, 0);
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
        batch.draw(logo,0,50);
        batch.draw(sprite1, 100,75);
        batch.draw(sprite2, 200,75);
        batch.draw(sprite3, 300,75);
        batch.draw(sprite4, 400,75);
        batch.draw(highScoreButton, 75,325);
        batch.draw(gitHubButton, 285,325);
        batch.end();

    if (Gdx.input.getX() > 100 && Gdx.input.getX() <175 && Gdx.input.getY() >75 && Gdx.input.getY() >150){
        if(Gdx.input.isTouched()){
            this.dispose();
            controller.setScene(new GameScene(controller));
        }
    }
    }


    //Handles obstacle collision. TODO://Handle a lot more

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
    music.stop();
    music.dispose();
    }


}
