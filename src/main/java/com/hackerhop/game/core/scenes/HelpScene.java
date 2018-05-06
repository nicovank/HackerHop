package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.objects.Coin;
import com.hackerhop.game.core.objects.Space;
import com.hackerhop.game.core.player.Character;
import com.hackerhop.game.core.utils.blinkers.SpriteBlinker;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.World;

//This will be between MainMenu and GameScene, explaining controls and other Game Mechanics
//Will be very simple, The constructor carries over the Character picked in main menu to GameScene so it should be easily implemented.

public class HelpScene extends Scene {
    private static final String TAG = GameScene.class.getName();
    private final Character character;
    private Texture background;
    private Space space = new Space(77, 30);
    private Sprite player;
    private Texture clouds;
    private SpriteBlinker blinker1;
    private SpriteBlinker blinker2;
    private SpriteBlinker blinker3;
    private SpriteBlinker blinker4;
    private Sprite arrow;
    private Sprite arrowR;
    private Sprite player2;
    private SpriteBlinker blinker5;
    private SpriteBlinker blinker6;
    private SpriteBlinker blinker7;
    private Music music;
    private Coin coin;
    private World world = new World(new Vec2(0, -50));

    public HelpScene(MainController controller, Character character) {
        super(controller);
        this.character = character;

    }

    @Override
    public void update() {
        blinker1.update();
        blinker2.update();
        blinker3.update();
        blinker4.update();
        blinker5.update();
        blinker6.update();
        blinker7.update();
        space.update();
    }

    @Override
    public boolean keyDown(int keycode) {
        if (keycode == Input.Keys.SPACE) {
            getController().setScene(new GameScene(getController(), character));
        }
        if (keycode == Input.Keys.ESCAPE) {
            getController().setScene(new MainMenu(getController()));
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
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        int y = Gdx.graphics.getHeight() - screenY;
        if(screenX > 77 && screenX < 461 && y >50 && y< 116){
            getController().setScene(new GameScene(getController(), character));
        }
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
        arrow = new Sprite(new Texture("mainMenuScene/Arrow.png"));
        arrowR = new Sprite(new Texture("helpScene/arrowR.png"));
        blinker1 = new SpriteBlinker(arrow, 1f, .5f);
        blinker2 = new SpriteBlinker(arrow, 1f, .5f);
        blinker3 = new SpriteBlinker(arrowR, 1f, .5f);
        blinker4 = new SpriteBlinker(arrowR, 1f, .5f);
        blinker7 = new SpriteBlinker(arrowR,1f,.5f);


        clouds = new Texture("background/cloud.png");
        background = new Texture("helpScene/HelpScreen.png");
        if (character == Character.ROB) {
            player = new Sprite(new Texture("player/rob.png"));
            player2 = new Sprite(new Texture("player/rob.png"));

        }
        if (character == Character.KATIE) {
            player = new Sprite(new Texture("player/Katie.png"));
            player2 = new Sprite(new Texture("player/Katie.png"));
        }
        if (character == Character.NICK) {
            player = new Sprite(new Texture("player/Nick.png"));
            player2 = new Sprite(new Texture("player/Nick.png"));
        }
        if (character == Character.YE) {
            player = new Sprite(new Texture("player/Ye.png"));
            player2 = new Sprite(new Texture("player/Ye.png"));
        }
        player.setPosition(405, 630);
        blinker5 = new SpriteBlinker(player2,.75f,1f);
        blinker6 = new SpriteBlinker(player2,1f,.75f,false); //blinker 6 with new case
        coin = new Coin(45,15, world);
        coin.loadResources();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.begin();
        batch.draw(clouds, 0, 0);
        space.render(batch);
        batch.draw(background, 0, 0);
        blinker1.render(batch, -210, 50);
        blinker2.render(batch, -130, 50);
        blinker3.render(batch, 330, 630);
        blinker4.render(batch, 355, 325);
        blinker5.render(batch,407,400);
        blinker6.render(batch,407,352); //This one needs to start hidden, and alternate with blinker 5
        blinker7.render(batch,380,145);
        player.draw(batch);
        coin.update();
        coin.render(batch);

        batch.end();

    }

    @Override
    public void dispose() {
        space.dispose();
        blinker4.dispose();
        blinker3.dispose();
        blinker2.dispose();
        blinker1.dispose();
        blinker5.dispose();
        blinker6.dispose();
        blinker7.dispose();
        clouds.dispose();
        background.dispose();
        player.getTexture().dispose();
        player2.getTexture().dispose();
        arrow.getTexture().dispose();
        arrowR.getTexture().dispose();
        coin.dispose();
    }


}