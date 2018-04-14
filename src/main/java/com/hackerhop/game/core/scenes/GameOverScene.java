package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.player.Player;
import com.hackerhop.game.core.utils.Constants;

public class GameOverScene extends Scene implements Constants {


    //Background
    private TextureRegion background;
    //Game Over
    private TextureRegion gameOver;
    // Final Score variables
    private BitmapFont font;
    private SpriteBatch ui;
    private String finalScore;
    //Buttons
    private Sprite restartButton;
    private Sprite menuButton;
    //Character
    private Texture playerTexture;

    public GameOverScene(MainController controller, String score, Player player) {
        super(controller);
        finalScore = score;
        playerTexture = player.getPlayerTexture();
    }

    @Override
    public void update() {

    }

    @Override
    public void loadResources() {
        background = new TextureRegion((new Texture("background/ShinemanPixel.png")));
        gameOver = new TextureRegion(new Texture("gameOver/GameOver.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/pixels/pixels.fnt"));
        font.setScale(0.15f);
        font.setColor(0, 0, 0, 1);
        ui = new SpriteBatch();
        restartButton = new Sprite(new Texture("gameOver/Restart.png"));
        menuButton = new Sprite(new Texture("gameOver/MainMenu.png"));

    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batch.draw(gameOver, 15, 300, 500, 400);
        batch.draw(menuButton, 90, 75, 125, 45);
        batch.draw(restartButton, 330, 75, 125, 45);
        batch.end();

        ui.begin();
        font.draw(ui, String.format("Final Score: %s", finalScore), 120, 700);
        ui.end();

    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.ESCAPE || keycode == Input.Keys.ENTER) {
            MainController controller = super.getController();
            controller.setScene(new MainMenu(controller));
        }
        return true;
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
        font.dispose();
        ui.dispose();
        background.getTexture().dispose();
        gameOver.getTexture().dispose();
        menuButton.getTexture().dispose();
        restartButton.getTexture().dispose();
    }
}
