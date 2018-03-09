package com.hackerhop.game.core.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.utils.Direction;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.shapes.PolygonShape;

public class Player {
    private Body body;
    private Direction direction;
    private Texture texture;
    private OrthographicCamera camera;


    /**
     * Returns the player's physics body.
     * Call to add forces or move the player.
     *
     * @return the player's body.
     */
    public Body getBody() {
        return body;
    }

    /**
     * Creates a new player in the given world.
     *
     * @param world    the physics world where the player will be.
     * @param position the initial position of the player.
     */
    public Player(World world, Vec2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(position);

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(3, 3);
        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

        texture = new Texture("player/rob.png");
    }

    public void render(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x * 10, body.getPosition().y * 10);

    }
}
