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

	public static boolean sounds() {
		if (options.getProperty("sounds") == null) {
			return true;
		}

		return Boolean.valueOf(options.getProperty("sounds"));
	}

	public static void toggleSounds() throws IOException {
		options.setProperty("sounds", Boolean.toString(!Boolean.valueOf(options.getProperty("sounds"))));
		options.store(new FileOutputStream("options.properties"), null);
	}
}
