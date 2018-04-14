package com.hackerhop.game.core.objects;

/**
 * Any object that will update itself during rendering.
 * That is, any non-static object.
 */
public interface DynamicObject {
	void update();
}
