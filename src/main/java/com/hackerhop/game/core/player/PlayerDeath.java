package com.hackerhop.game.core.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.utils.Constants;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.dynamics.contacts.ContactEdge;

public class PlayerDeath implements GraphicsElement, Constants {
    private Body body;
    private Direction direction;
    private Sprite sprite;
    private Sound jumpSound;
    private Character character;

    public PlayerDeath(World world, Vec2 position, Character character) {
        this.character = character;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(position);

        body = world.createBody(bodyDef);
        body.setUserData("playerDeath");

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(3, 3);
        fixtureDef.shape = rectangle;
        fixtureDef.friction = 0f;
        body.createFixture(fixtureDef);
    }
    public Character getCharacter() {
        return character;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void render(SpriteBatch batch) {
        sprite.setRotation(25);
        batch.draw(sprite, body.getPosition().x * PHYSICS_RATIO, body.getPosition().y * PHYSICS_RATIO);
    }

    @Override
    public void loadResources() {
        switch (character) {
            case ROB:
                sprite = new Sprite(new Texture("player/rob.png"));
                break;
            case YE:
                sprite = new Sprite(new Texture("player/Ye.png"));
                break;
            case NICK:
                sprite = new Sprite(new Texture("player/Nick.png"));
                break;
            case KATIE:
                sprite = new Sprite(new Texture("player/Katie.png"));
                break;
        }

    }

    @Override
    public void dispose() {
        sprite.getTexture().dispose();
        jumpSound.dispose();
    }


}