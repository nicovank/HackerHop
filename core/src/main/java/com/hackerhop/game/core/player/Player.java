package com.hackerhop.game.core.player;

import com.badlogic.gdx.InputProcessor;
import com.hackerhop.game.core.utils.Direction;
import com.hackerhop.game.core.utils.Position;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.shapes.PolygonShape;

public class Player {
    private Body body;
    private Direction direction;

    /**
     * Returns the player's physics body.
     * Call to add forces or move the player.
     *
     * @return the player's body.
     */
    public Body getBody() {
        return body;
    }

    public Player(World world, Vec2 position) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(position);

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(100, 100);
        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);
    }
}
