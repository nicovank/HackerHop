package com.hackerhop.game.core.utils.toggleable;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.function.Supplier;

public class ToggleableSprite extends Toggleable {
	private Sprite activeSprite;
	private Sprite inactiveSprite;

	public ToggleableSprite(Supplier<Boolean> condition, Sprite activeSprite, Sprite inactiveSprite) {
		super(condition);
		this.activeSprite = activeSprite;
		this.inactiveSprite = inactiveSprite;
	}

	@Override
	public void render(SpriteBatch batch) {
		if (super.isActive()) {
			activeSprite.draw(batch);
		} else {
			inactiveSprite.draw(batch);
		}
	}

	@Override
	public void dispose() {
		activeSprite.getTexture().dispose();
		inactiveSprite.getTexture().dispose();
	}
}
