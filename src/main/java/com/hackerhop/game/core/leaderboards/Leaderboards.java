package com.hackerhop.game.core.leaderboards;

import com.badlogic.gdx.Gdx;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.net.URL;
import java.net.HttpURLConnection;

public class Leaderboards {

	private static final String TAG = Leaderboards.class.getName();

	public static final String LIST_URL = "http://pi.cs.oswego.edu:38249/leaderboard";
	public static final String ADD_URL = "http://pi.cs.oswego.edu:38249/add";

	public static void saveScore(Score score) throws IOException {

		Gdx.app.debug(TAG, "Trying to save score...");

		ObjectMapper mapper = new ObjectMapper();

		URL url = new URL(ADD_URL);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true);
		connection.setRequestMethod("POST");
		mapper.writeValue(connection.getOutputStream(), score);

		Gdx.app.debug(TAG, "Response from the server: " + connection.getResponseCode());
	}
}
