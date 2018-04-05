package com.hackerhop.game.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;

public class Platform extends PhysicalObject implements GraphicsElement {

	private static final String TAG = Platform.class.getName();

	private float x, y;
	private static final float WIDTH = 60;
	private static final float HEIGHT = 20;

	private Texture texture;

	public Platform(float x, float y, World world) {
		this.x = x;
		this.y = y;

		BodyDef bodyDef = new BodyDef();
		bodyDef.type = BodyType.STATIC;
		bodyDef.position.set(new Vec2(x, y));

		super.setBody(world.createBody(bodyDef));

		FixtureDef fixtureDef = new FixtureDef();
		PolygonShape rectangle = new PolygonShape();
		rectangle.setAsBox(3, -1);
		fixtureDef.shape = rectangle;
		super.getBody().createFixture(fixtureDef);
	}

	public void render(SpriteBatch batch) {
		batch.draw(texture,
				super.getBody().getPosition().x * 10,
				super.getBody().getPosition().y * 10,
				WIDTH,
				HEIGHT
		);
	}

//	public void destroy(World world){
//		world.destroyBody(super.getBody());
//	}

	/**
<<<<<<< HEAD
	 * Calculates the distance between two PlatformGroup <code>p</code>.
=======
	 * Calculates the distance between this platform and another one.
>>>>>>> 826cc1420e78181ff0c47dce481921761f173528
	 *
	 * @param other the other platform to calculate the distance to.
	 * @return distance between this platform and the other one.
	 */
	public double distanceTo(Platform other) {
		float a = other.x;
		float b = other.y;
		return Math.sqrt(Math.pow((x - a), 2) + Math.pow((y - b), 2));
	}

	public float getY(){
		return y;
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

	@Override
	public void loadResources() {
		texture = new Texture("platform/bricks.png");
	}
}
