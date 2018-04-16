package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.handlers.ContactHandler;
import com.hackerhop.game.core.objects.Coin;
import com.hackerhop.game.core.objects.Space;
import com.hackerhop.game.core.objects.obstacles.ObstacleGenerator;
import com.hackerhop.game.core.objects.platforms.Platforms;
import com.hackerhop.game.core.player.Character;
import com.hackerhop.game.core.player.Direction;
import com.hackerhop.game.core.player.Player;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

import static com.hackerhop.game.core.utils.Constants.PHYSICS_RATIO;
import static com.hackerhop.game.core.utils.Constants.SCREEN_HEIGHT;
import static com.hackerhop.game.core.utils.Constants.SCREEN_WIDTH;

//This will be between MainMenu and GameScene, explaining controlls and other Game Mechanics
//Will be very simple, The constructor carrys over the Character picked in main menu to GameScene so it should be easily implemented.

public class HelpScene extends Scene {
    private static final String TAG = GameScene.class.getName();
    private final Character character;

    private Space space = new Space(77, 50);

    public HelpScene(MainController controller, Character character) {
        super(controller);
        this.character = character;
    }

    @Override
    public void update() {

        space.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            getController().setScene(new GameScene(getController(), character));
        }
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
        space.loadResources();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        space.render(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        space.dispose();
    }
}
