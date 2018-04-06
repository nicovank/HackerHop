package com.hackerhop.game.core.objects.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.PhysicalObject;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import java.io.File;
import java.util.Random;

public class Obstacle extends PhysicalObject implements GraphicsElement {

    private static final String TAG = Obstacle.class.getName();
    private static final float WIDTH = 50;
    private static final float HEIGHT = 50;

    private static String[] textures;
    private Texture texture;

    public Obstacle(float x, float y, World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(new Vec2(x, y));

        super.setBody(world.createBody(bodyDef));
        super.getBody().setUserData("obstacle");

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(3, 3);
        fixtureDef.shape = rectangle;
        super.getBody().createFixture(fixtureDef);

        // Ye broke this, Ye will fix this
//        textures = new File("deadline").list();
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(texture, super.getBody().getPosition().x * 10,
                super.getBody().getPosition().y * 10,
                WIDTH,
                HEIGHT);
    }

    @Override
    public void loadResources() {
        texture = new Texture("deadline/" + textures[new Random().nextInt(textures.length)]);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
