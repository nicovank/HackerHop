package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
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
import com.hackerhop.game.core.utils.blinkers.SpriteBlinker;
import javafx.scene.layout.Background;
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
    private Texture background;
    private Space space = new Space(77, 50);
    private Sprite player;
    private Texture clouds;
    private SpriteBlinker blinker1;
    private SpriteBlinker blinker2;
    private SpriteBlinker blinker3;
    private SpriteBlinker blinker4;
    private Sprite arrow;

    public HelpScene(MainController controller, Character character) {
        super(controller);
        this.character = character;

    }

    @Override
    public void update() {
        blinker1.update();
        blinker2.update();
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
        arrow = new Sprite(new Texture("mainScreen/Arrow.png"));
        arrow.setPosition(0,0);
        blinker1 = new SpriteBlinker(arrow, 1f, .5f);
        blinker2 = new SpriteBlinker(arrow, 1f, .5f);

        clouds = new Texture("background/cloud.png");
        background = new Texture("HelpScreen/HelpScreen.png");
        if (character == Character.ROB) {
            player = new Sprite(new Texture("player/rob.png"));
        }
        if (character == Character.KATIE) {
            player = new Sprite(new Texture("player/Katie.png"));
        }
        if (character == Character.NICK) {
            player = new Sprite(new Texture("player/Nick.png"));
        }
        if(character ==Character.YE){
            player = new Sprite(new Texture("player/Ye.png"));
        }
        player.setPosition(405,630);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(clouds,0,0);
        space.render(batch);
        batch.draw(background, 0, 0);
        blinker1.render(batch, -210,50);
        blinker2.render(batch,-130,50);
        player.draw(batch);
        batch.end();

    }

    @Override
    public void dispose() {
        space.dispose();
    }
}
