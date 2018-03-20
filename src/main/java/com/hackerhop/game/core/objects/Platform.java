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

	/**
	 * Width is the distance between the left and right sides
	 * Height is the difference between the top and the bottom
	 * libgdx uses the coordinates of bottom left corner and width and height to construct a rectangle
	 * Color currently gets set to Teal--obviously this can be changed
	 **/
	public void rectRender(SpriteBatch batch) {
		batch.draw(texture,
				super.getBody().getPosition().x * 10,
				super.getBody().getPosition().y * 10,
				WIDTH,
				HEIGHT
		);
	}

	/**
	 * Calculates the distance between two Platforms <code>p</code>.
	 *
	 * @param p the target Platform
	 * @return distance between <code>this</code> and <code>p</code>
	 */
	public double distanceTo(Platform p) {
		float a = p.x;
		float b = p.y;
		return Math.sqrt(Math.pow((this.x - a), 2) + Math.pow((this.y - b), 2));
	}

	@Override
	public void dispose() {
		texture.dispose();
	}

	@Override
	public void loadGraphics() {
		texture = new Texture("platform/bricks.png");
	}
}
