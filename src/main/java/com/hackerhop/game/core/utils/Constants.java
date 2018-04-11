package com.hackerhop.game.core.utils;

/**
 * Some constants that classes can implement, making it easy to change configuration everywhere.
 */
public interface Constants {
	int SCREEN_WIDTH = 540;
	int SCREEN_HEIGHT = 720;

	// The ratio between our physics coordinates and screen coordinates.
	float PHYSICS_RATIO = 10f;
}
