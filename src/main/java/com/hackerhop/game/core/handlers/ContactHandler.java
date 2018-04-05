package com.hackerhop.game.core.handlers;

import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.scenes.GameOverScene;
import org.jbox2d.callbacks.ContactImpulse;
import org.jbox2d.callbacks.ContactListener;
import org.jbox2d.collision.Manifold;
import org.jbox2d.dynamics.Body;
import org.jbox2d.dynamics.contacts.Contact;

public class ContactHandler implements ContactListener {

	private static final class Collision {

		private Body body1;
		private Body body2;

		public boolean isBetween(String label1, String label2) {

			if (body1.getUserData() == null || body2.getUserData() == null) {
				return false;
			}

			return (body1.getUserData().equals(label1) && body2.getUserData().equals(label2)) ||
					(body1.getUserData().equals(label2) && body2.getUserData().equals(label1));
		}

		public Collision(Body body1, Body body2) {
			this.body1 = body1;
			this.body2 = body2;
		}
	}

	private MainController controller;

	public ContactHandler(MainController controller) {
		this.controller = controller;
	}

	@Override
	public void beginContact(Contact contact) {
		Body body = contact.getFixtureA().getBody();
		Body otherBody = contact.getFixtureB().getBody();

		Collision collision = new Collision(body, otherBody);

		//Quit game if player and obstacle collide
		if (collision.isBetween("player", "obstacle")) {
			controller.setScene(new GameOverScene(controller));
		}
	}

	@Override
	public void endContact(Contact contact) {

	}

	@Override
	public void preSolve(Contact contact, Manifold manifold) {

	}

	@Override
	public void postSolve(Contact contact, ContactImpulse contactImpulse) {

	}
}
