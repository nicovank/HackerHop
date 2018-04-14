package com.hackerhop.game.core.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.utils.Constants;
import com.hackerhop.game.core.utils.Options;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.contacts.ContactEdge;

public class Player implements GraphicsElement, Constants {
	private Body body;
	private Direction direction;
	private Sprite sprite;
	private Sound jumpSound;
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
		sprite.setFlip(direction == Direction.LEFT, false);
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

		jumpSound = Gdx.audio.newSound(Gdx.files.internal("audio/jump.mp3"));
	}

	@Override
	public void dispose() {
		sprite.getTexture().dispose();
		jumpSound.dispose();
	}

	public Texture getPlayerTexture(){
		return sprite.getTexture();
	}

	public Sprite getSprite(){
		return sprite;
	}

	public void jump() {
		 if (canJump()) {
			body.applyForceToCenter(new Vec2(0f, 5000f));
			jumpSound.play(Options.sounds() ? 1f : 0f);
		 }
	}

	private boolean canJump() {
		for (ContactEdge edge = body.getContactList(); edge != null; edge = edge.next) {
			if (edge.other.getUserData().equals("platform") && edge.contact.isTouching()) {
				return true;
			}
		}

		return false;
	}
}



