package com.hackerhop.game.core.objects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.World;

/**
 * Just an abstract class to make sure every object has all the properties it needs.
 */
public abstract class PhysicalObject {

	// The physical body of this object.
	// For more information read https://github.com/libgdx/libgdx/wiki/box2d
	private Body body;

	/**
	 * Returns the physical body of this object.
	 *
	 * @return the physical body of the object.
	 */
	public Body getBody() {
		return body;
	}

	/**
	 * Changes the reference to this object's body.
	 *
	 * @param body the new body to be stored.
	 */
	public void setBody(Body body) {
		this.body = body;
	}
}
