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
    private Sprite coinObject;
    private Texture coin1;
    private Texture coin2;
    private Texture coin3;
    private Texture coin4;
    private Texture coin5;
    private Texture coin6;
    ArrayList<Texture> list = new ArrayList<>();


    public Coin(float x, float y, World world) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void loadResources() {
        coin1 = new Texture("Coin/coin1");
        coin2 = new Texture("Coin/coin2");
        coin3 = new Texture("Coin/coin3");
        coin4 = new Texture("Coin/coin4");
        coin5 = new Texture("Coin/coin5");
        coin6 = new Texture("Coin/coin6");
        list.add(coin1);
        list.add(coin2);
        list.add(coin3);
        list.add(coin4);
        list.add(coin5);
        list.add(coin6);


    }

    @Override
    public void render(SpriteBatch batch) {
    batch.draw(setCoinTexture(),0,0);
    }

    @Override
    public void dispose() {

    }

    public Texture setCoinTexture() {
        if (coinObject.getTexture() == null) {
            coinObject = new Sprite(list.get(0));
            return list.get(0);
        } else {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) == coinObject.getTexture()) {
                    if (i == 5) {
                        coinObject =new Sprite(list.get(0));
                        return list.get(0);
                    } else {
                        coinObject =new Sprite(list.get(i++));
                        return list.get(i++);
                    }
                }

            }
            return list.get(5);
        }
    }
}
