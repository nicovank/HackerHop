package com.hackerhop.game.core.utils.toggleable;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

import java.util.function.Supplier;

public abstract class Toggleable implements Disposable {

	private Supplier<Boolean> condition;

	public Toggleable(Supplier<Boolean> condition) {
		this.condition = condition;
	}

	public boolean isActive() {
		return condition.get();
	}

	public abstract void render(SpriteBatch batch);
}
