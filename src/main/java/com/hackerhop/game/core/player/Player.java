package com.hackerhop.game.core.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.shapes.PolygonShape;

public class Player implements GraphicsElement {
	private Body body;
	private Direction direction;
	private Sprite sprite;
	private Character character;

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
		this(world, position, Character.ROB);
	}

	/**
	 * Creates a new player in the given world.
	 *
	 * @param world     the physics world where the player will be.
	 * @param position  the initial position of the player.
	 * @param character sprite to be loaded.
	 */
	public Player(World world, Vec2 position, Character character) {
		this.character = character;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.DYNAMIC;
		bodyDef.position.set(position);

		body = world.createBody(bodyDef);
		body.setUserData("player");

		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(3, 3);
		fixtureDef.shape = rectangle;
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
		sprite.setFlip(direction == Direction.LEFT, false);
		batch.draw(sprite, body.getPosition().x * 10, body.getPosition().y * 10);
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
	}
}

