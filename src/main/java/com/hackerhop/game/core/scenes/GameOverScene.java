package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.*;
import com.badlogic.gdx.math.Rectangle;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.objects.ui.Button;
import com.hackerhop.game.core.player.Player;
import com.hackerhop.game.core.utils.Constants;
import org.jbox2d.common.Vec2;

import static com.hackerhop.game.core.utils.GDXUtils.*;

public class GameOverScene extends Scene implements Constants {

    private static final String TAG = GameScene.class.getName();

    //Background
    private TextureRegion background;
    //Game Over
    private TextureRegion gameOver;

    // Final Score variables
    private BitmapFont font;
    private SpriteBatch ui;
    private String finalScore;

    //Buttons
    private Button restartButton;
    private Button menuButton;

    //Character
    private Player userPlayer;
    private Sprite playerSprite;

    public GameOverScene(MainController controller, String score, Player player) {
        super(controller);
        finalScore = score;
        userPlayer = player;
    }

    @Override
    public void update() {
        playerSprite.rotate(3);
    }

    @Override
    public void loadResources() {
        userPlayer.loadResources();
        background = new TextureRegion((new Texture("background/ShinemanPixel.png")));
        gameOver = new TextureRegion(new Texture("gameOver/GameOver.png"));
        font = new BitmapFont(Gdx.files.internal("fonts/pixels/pixels.fnt"));
        font.setScale(0.15f);
        font.setColor(0, 0, 0, 1);
        ui = new SpriteBatch();

        restartButton = new Button(
                "gameOver/Restart.png",
                "gameOver/RestartHover.png",
                new Vec2(330, 75),
                new Vec2(125, 45)
        );

        menuButton = new Button(
                "gameOver/MainMenu.png",
                "gameOver/MainMenuHover.png",
                new Vec2(90, 75),
                new Vec2(125, 45)
        );

        playerSprite = userPlayer.getSprite();
        playerSprite.setSize(100, 120);
        playerSprite.setPosition(215, 160);
        playerSprite.setOriginCenter();
    }

    @Override
    public void render(SpriteBatch batch) {
        Gdx.gl.glClearColor(0, 0, 0, 0);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0);
        batch.draw(gameOver, 15, 300, 500, 400);

        menuButton.render(batch);
        restartButton.render(batch);

        playerSprite.draw(batch);
        batch.end();

        ui.begin();
        font.draw(ui, String.format("Final Score: %s", finalScore), 115, 700);
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int y = Gdx.graphics.getHeight() - screenY;
        MainController controller = super.getController();

        Rectangle r = restartButton.getBoundingRectangle();
        if(restartButton.getBoundingRectangle().contains(screenX, y)){
            controller.setScene(new GameScene(controller, userPlayer.getCharacter()));
        } else if(menuButton.getBoundingRectangle().contains(screenX, y)){
            controller.setScene(new MainMenu(controller));
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
        font.dispose();
        ui.dispose();
        background.getTexture().dispose();
        gameOver.getTexture().dispose();
        menuButton.dispose();
        restartButton.dispose();
        userPlayer.dispose();
        playerSprite.getTexture().dispose();
    }
}
