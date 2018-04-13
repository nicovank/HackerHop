package com.hackerhop.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.utils.Constants;

public class GameDesktop implements Constants {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = 900;
		config.width = 700;
		config.resizable = false;
		new LwjglApplication(new MainController(), config);
	}
}
