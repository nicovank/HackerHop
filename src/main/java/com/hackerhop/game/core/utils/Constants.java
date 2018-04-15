package com.hackerhop.game.core.utils;

/**
 * Some constants that classes can implement, making it easy to change configuration everywhere.
 */
public interface Constants {
	int SCREEN_WIDTH = 540;
	int SCREEN_HEIGHT = 720;

	// The ratio between our physics coordinates and screen coordinates.
	float PHYSICS_RATIO = 10f;


	// Constants for everything in com.hackerhop.game.platforms
	int THRESHOLD = 500;    // Platform delete threshold
	int GROUP_COUNT = 5;    // number of PlatformGroups in Platforms object
	int GRID_SEPARATION = 20;    // distance between each PlatformGroup
	int WIGGLE_ROOM = 7;    // maximum distance between a Platform's grid position and actual position
}
