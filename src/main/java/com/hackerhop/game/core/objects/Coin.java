package com.hackerhop.game.core.objects;

import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.utils.Constants;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;
import com.badlogic.gdx.Graphics;

import java.util.ArrayList;

public class Coin extends PhysicalObject implements GraphicsElement, Constants {

    private float x, y;
    private Sprite coinSprite;
    private Texture coin1;
    private Texture coin2;
    private Texture coin3;
    private Texture coin4;
    private Texture coin5;
    private Texture coin6;
    private Texture coin15;
    private Texture coin25;
    private Texture coin35;
    private Texture coin45;
    private Texture coin55;
    private Texture coin65;
    ArrayList<Texture> list = new ArrayList<>();


    public Coin(World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;
        bodyDef.position.set(new Vec2(x, y));

        super.setBody(world.createBody(bodyDef));
        super.getBody().setUserData("coin");

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(3, -1);
        fixtureDef.shape = rectangle;
        super.getBody().createFixture(fixtureDef);
    }

    @Override
    public void loadResources() {
        coinSprite = new Sprite(new Texture("Coin/coin1.png"));
        coin1 = new Texture("Coin/coin1.png");
        coin2 = new Texture("Coin/coin2.png");
        coin3 = new Texture("Coin/coin3.png");
        coin4 = new Texture("Coin/coin4.png");
        coin5 = new Texture("Coin/coin5.png");
        coin6 = new Texture("Coin/coin6.png");
        coin15 = new Texture("Coin/coin1.png");
        coin25 = new Texture("Coin/coin2.png");
        coin35 = new Texture("Coin/coin3.png");
        coin45 = new Texture("Coin/coin4.png");
        coin55 = new Texture("Coin/coin5.png");
        coin65 = new Texture("Coin/coin6.png");
        list.add(coin1);
        list.add(coin15);
        list.add(coin2);
        list.add(coin25);
        list.add(coin3);
        list.add(coin35);
        list.add(coin4);
        list.add(coin45);
        list.add(coin5);
        list.add(coin55);
        list.add(coin6);
        list.add(coin65);



    }

    @Override
    public void render(SpriteBatch batch) {
        coinSprite = new Sprite(setCoinTexture());
        coinSprite.setPosition(200, 360);
        coinSprite.draw(batch);
    }

    @Override
    public void dispose() {
        coin1.dispose();
        coin15.dispose();
        coin2.dispose();
        coin25.dispose();
        coin3.dispose();
        coin35.dispose();
        coin4.dispose();
        coin45.dispose();
        coin5.dispose();
        coin55.dispose();
        coin6.dispose();
        coin65.dispose();

    }

    public Texture setCoinTexture() {
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i) == coinSprite.getTexture()) {
                if (i == 11) {
                    coinSprite = new Sprite(list.get(0));
                    return list.get(0);
                } else {
                    coinSprite = new Sprite(list.get(i++));
                    return list.get(i++);
                }
            }
        }
        return list.get(11);
    }
    public float getCoinPositionX(){
        return coinSprite.getX();
    }
    public float getCoinPositionY(){
        return coinSprite.getY();
    }
}
