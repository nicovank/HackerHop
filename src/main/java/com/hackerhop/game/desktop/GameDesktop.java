package com.hackerhop.game.desktop;

import com.badlogic.gdx.Files;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.utils.Constants;

public class GameDesktop implements Constants {
	public static void main(String[] args) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.height = SCREEN_HEIGHT;
		config.width = SCREEN_WIDTH;
		config.resizable = false;
		config.title = "HackerHop";
		config.addIcon("icon.png", Files.FileType.Internal);
		new LwjglApplication(new MainController(), config);
	}
}
