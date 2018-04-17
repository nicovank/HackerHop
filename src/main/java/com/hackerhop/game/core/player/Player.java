package com.hackerhop.game.core.player;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.PhysicalObject;
import com.hackerhop.game.core.utils.Constants;
import com.hackerhop.game.core.utils.Options;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.dynamics.contacts.ContactEdge;

public class Player extends PhysicalObject implements GraphicsElement, Constants {

	private static final float LATERAL_SPEED = 75f;
	private static final float JUMP_FORCE = 5000f;

	private Direction direction;
	private Sprite sprite;
	private Sound jumpSound;
	private Character character;

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

		super.setBody(world.createBody(bodyDef));
		super.getBody().setUserData("player");

		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(3, 3);
		fixtureDef.shape = rectangle;
		fixtureDef.friction = 0f;
		super.getBody().createFixture(fixtureDef);
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

		batch.draw(
				sprite,
				super.getBody().getPosition().x * PHYSICS_RATIO,
				super.getBody().getPosition().y * PHYSICS_RATIO
		);
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

	public Sprite getSprite() {
		return sprite;
	}

	public void jump() {
		if (canJump()) {
			super.getBody().applyForceToCenter(new Vec2(0f, JUMP_FORCE));
			jumpSound.play(Options.sounds() ? .2f : 0f);
		}
	}

	public void update() {

		if (Gdx.input.isKeyPressed(Input.Keys.LEFT) || Gdx.input.isKeyPressed(Input.Keys.A)) {
			super.getBody().setLinearVelocity(new Vec2(-LATERAL_SPEED, super.getBody().getLinearVelocity().y));
			setDirection(Direction.LEFT);
		} else if (Gdx.input.isKeyPressed(Input.Keys.RIGHT) || Gdx.input.isKeyPressed(Input.Keys.D)) {
			super.getBody().setLinearVelocity(new Vec2(LATERAL_SPEED, super.getBody().getLinearVelocity().y));
			setDirection(Direction.RIGHT);
		} else {
			super.getBody().setLinearVelocity(new Vec2(0f, super.getBody().getLinearVelocity().y));
		}

		float x = super.getBody().getPosition().x;
		float y = super.getBody().getPosition().y;
		float xMax = (SCREEN_WIDTH - sprite.getWidth()) / PHYSICS_RATIO;

		if (x < 0) {
			super.getBody().setTransform(new Vec2(0, y), 0);
		} else if (x > xMax) {
			super.getBody().setTransform(new Vec2(xMax, y), 0);
		}
	}

	private boolean canJump() {
		for (ContactEdge edge = super.getBody().getContactList(); edge != null; edge = edge.next) {
			if (edge.other.getUserData().equals("platform") && edge.contact.isTouching()) {
				return true;
			}
		}

		return false;
	}
}



