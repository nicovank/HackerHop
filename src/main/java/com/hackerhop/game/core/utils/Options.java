package com.hackerhop.game.core.utils;

import com.badlogic.gdx.Gdx;

import java.io.*;
import java.util.Properties;

public class Options {
	private static Properties options;

	static {
		options = new Properties();

		try {
			options.load(new FileInputStream("options.properties"));
		} catch (IOException ignored) {

		}
	}

	public static float getMusicVolume() {
		if (options.getProperty("sounds") == null) {
			return 1f;
		}

		return Boolean.valueOf(options.getProperty("sounds")) ? 1f : 0f;
	}

	public static void playSounds(boolean sounds) throws IOException {
		options.setProperty("sounds", Boolean.toString(sounds));
		options.store(new FileOutputStream("options.properties"), "Ya Yeet");
	}
}
