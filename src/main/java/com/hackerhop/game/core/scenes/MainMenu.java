package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.player.Character;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.utils.Options;
import com.hackerhop.game.core.utils.blinkers.SpriteBlinker;
import com.hackerhop.game.core.utils.toggleable.ToggleableSprite;


import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;

import static com.hackerhop.game.core.utils.GDXUtils.*;


public class MainMenu extends Scene {

	private static final String TAG = MainMenu.class.getName();

	private static final float CHARACTER_ZOOM = 1.2f;

	// Main Screen Textures
	private Texture logo;
	private Texture background;

	private Sprite rob;
	private Sprite nick;
	private Sprite kate;
	private Sprite ye;

	private ToggleableSprite highScoreButton;
	private ToggleableSprite gitHubButton;

	private Sprite textDisplay;
	private SpriteBlinker blinker;

	private ToggleableSprite soundButton;
	private Music music;

	public MainMenu(MainController controller) {
		super(controller);
	}

	@Override
	public void update() {
		blinker.update();
	}

	@Override
	public void loadResources() {
		logo = new Texture("mainScreen/Logo.png");
		background = new Texture("background/ShinemanPixel.png");

		rob = new Sprite(new Texture("player/rob.png"));
		rob.setPosition(100, 75);

		nick = new Sprite(new Texture("player/Nick.png"));
		nick.setPosition(200, 75);

		kate = new Sprite(new Texture("player/Katie.png"));
		kate.setPosition(300, 75);

		ye = new Sprite(new Texture("player/Ye.png"));
		ye.setPosition(400, 75);

		final Sprite highScoreButton = new Sprite(new Texture("mainScreen/HighScoreButton.png"));
		final Sprite highScoreButtonHover = new Sprite(new Texture("mainScreen/UnderConstruction.png"));
		highScoreButton.setPosition(75, 325);
		highScoreButtonHover.setPosition(highScoreButton.getX(), highScoreButton.getY());

		this.highScoreButton = new ToggleableSprite(
				() -> highScoreButton.getBoundingRectangle().contains(mouseX(), mouseY()),
				highScoreButtonHover,
				highScoreButton
		);

		final Sprite gitHubButton = new Sprite(new Texture("mainScreen/GitHubButton.png"));
		gitHubButton.setPosition(285, 325);
		final Sprite gitHubButtonHover = new Sprite(new Texture("mainScreen/GitHubButtonHover.png"));
		gitHubButtonHover.setPosition(gitHubButton.getX(), gitHubButton.getY());

		this.gitHubButton = new ToggleableSprite(
				() -> gitHubButton.getBoundingRectangle().contains(mouseX(), mouseY()),
				gitHubButtonHover,
				gitHubButton
		);

		Sprite arrow = new Sprite(new Texture("mainScreen/Arrow.png"));
		arrow.setPosition(0, 0);
		blinker = new SpriteBlinker(arrow, 1f, .5f);

		textDisplay = new Sprite(new Texture("mainScreen/textDisplay.png"));

		soundButton = new ToggleableSprite(
				Options::sounds,
				new Sprite(new Texture("mainScreen/soundButton.png")),
				new Sprite(new Texture("mainScreen/soundButtonOff.png"))
		);

		music = Gdx.audio.newMusic(Gdx.files.internal("audio/waves.mp3"));
		music.setLooping(true);
		music.setVolume(Options.sounds() ? 1f : 0f);
		music.play();
	}

	@Override
	public void render(SpriteBatch batch) {

		Gdx.gl.glClearColor(1, .5f, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		// Draw next frame (current scene)
		batch.begin();

		batch.draw(background, 0, 0);
		batch.draw(logo, 0, 50);

		if (rob.getBoundingRectangle().contains(mouseX(), mouseY())) {
			batch.draw(rob, rob.getX(), rob.getY(), rob.getWidth() * CHARACTER_ZOOM, rob.getHeight() * CHARACTER_ZOOM);
		} else {
			rob.draw(batch);
		}

		if (nick.getBoundingRectangle().contains(mouseX(), mouseY())) {
			batch.draw(nick, nick.getX(), nick.getY(), nick.getWidth() * CHARACTER_ZOOM, nick.getHeight() * CHARACTER_ZOOM);
		} else {
			nick.draw(batch);
		}
		if (kate.getBoundingRectangle().contains(mouseX(), mouseY())) {
			batch.draw(kate, kate.getX(), kate.getY(), kate.getWidth() * CHARACTER_ZOOM, kate.getHeight() * CHARACTER_ZOOM);
		} else {
			kate.draw(batch);
		}
		if (ye.getBoundingRectangle().contains(mouseX(), mouseY())) {
			batch.draw(ye, ye.getX(), ye.getY(), ye.getWidth() * CHARACTER_ZOOM, ye.getHeight() * CHARACTER_ZOOM);
		} else {
			ye.draw(batch);
		}

		gitHubButton.render(batch);
		highScoreButton.render(batch);
		soundButton.render(batch);

		blinker.render(batch);
		textDisplay.draw(batch);

		batch.end();
	}

	@Override
	public boolean keyDown(int i) {
		return false;
	}

	@Override
	public boolean keyUp(int i) {
		return false;
	}

	@Override
	public boolean keyTyped(char c) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		int y = Gdx.graphics.getHeight() - screenY;
		MainController controller = super.getController();

		if (soundButton.getActiveSprite().getBoundingRectangle().contains(screenX, y)) {
			try {
				Options.toggleSounds();
			} catch (IOException ignored) {

			}

			music.setVolume(Options.sounds() ? 1f : 0f);
		}

		if (rob.getBoundingRectangle().contains(screenX, y)) {
			controller.setScene(new GameScene(controller, Character.ROB));

		} else if (nick.getBoundingRectangle().contains(screenX, y)) {
			controller.setScene(new GameScene(controller, Character.NICK));

		} else if (kate.getBoundingRectangle().contains(screenX, y)) {
			controller.setScene(new GameScene(controller, Character.KATIE));

		} else if (ye.getBoundingRectangle().contains(screenX, y)) {
			controller.setScene(new GameScene(controller, Character.YE));

		} else if (gitHubButton.getActiveSprite().getBoundingRectangle().contains(screenX, y)) {
			try {
				openWebpage(new URL("https://github.com/nicovank/HackerHop"));
			} catch (MalformedURLException ignored) {

			}
		}

		return true;
	}

	@Override
	public boolean touchUp(int i, int i1, int i2, int i3) {
		return false;
	}

	@Override
	public boolean touchDragged(int i, int i1, int i2) {
		return false;
	}

	@Override
	public boolean mouseMoved(int i, int i1) {
		return false;
	}

	@Override
	public boolean scrolled(int i) {
		return false;
	}

	@Override
	public void dispose() {
		logo.dispose();
		background.dispose();

		rob.getTexture().dispose();
		nick.getTexture().dispose();
		kate.getTexture().dispose();
		ye.getTexture().dispose();

		highScoreButton.dispose();
		gitHubButton.dispose();

		blinker.dispose();

		textDisplay.getTexture().dispose();

		soundButton.dispose();

		music.stop();
		music.dispose();
	}

	private static boolean openWebpage(URI uri) {
		Desktop desktop = Desktop.isDesktopSupported() ? Desktop.getDesktop() : null;
		if (desktop != null && desktop.isSupported(Desktop.Action.BROWSE)) {
			try {
				desktop.browse(uri);
				return true;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private static boolean openWebpage(URL url) {
		try {
			return openWebpage(url.toURI());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return false;
	}
}
