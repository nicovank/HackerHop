package com.hackerhop.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.hackerhop.game.core.MainController;

public class GameDesktop {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 720;
		config.width = 540;
		config.resizable = false;
		new LwjglApplication(new MainController(), config);
	}
}
