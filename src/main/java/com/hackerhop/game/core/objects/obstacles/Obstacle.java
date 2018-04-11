package com.hackerhop.game.core.objects.obstacles;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.objects.PhysicalObject;
import com.hackerhop.game.core.utils.Constants;

import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

import static com.hackerhop.game.core.utils.Methods.randomInt;

public class Obstacle extends PhysicalObject implements GraphicsElement, Constants {

	private static final float VELOCITY = 50f;

	private static final String TAG = Obstacle.class.getName();
	private static final float WIDTH = 50;
	private static final float HEIGHT = 50;


	private static String[] textures = {"homework.png", "textbooks.png"};
	private Texture texture;

	public Obstacle(float x, float y, World world) {

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.KINEMATIC;
		bodyDef.position.set(new Vec2(x, y));

		super.setBody(world.createBody(bodyDef));
		super.getBody().setUserData("obstacle");

		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(3, 3);
		fixtureDef.shape = rectangle;
		super.getBody().createFixture(fixtureDef);

		super.getBody().setLinearVelocity(new Vec2(0, -VELOCITY));
	}

	@Override
	public void render(SpriteBatch batch) {
		batch.draw(texture,
				super.getBody().getPosition().x * PHYSICS_RATIO,
				super.getBody().getPosition().y * PHYSICS_RATIO,
				WIDTH,
				HEIGHT);
	}

	@Override
	public void loadResources() {
		texture = new Texture("obstacles/" + textures[randomInt(textures.length)]);
	}

	@Override
	public void dispose() {
		texture.dispose();
	}
}
