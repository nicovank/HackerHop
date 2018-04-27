package com.hackerhop.game.core.leaderboards;

import com.hackerhop.game.core.player.Character;

public class Score {
	// Character selected when reaching this score.
	public Character character;

	// Score the player obtained.
	public long score;

	// Time, in seconds, the game lasted.
	public long duration;

	// The name of the player that reached the score.
	public String name;

	/**
	 * Sets the character of this score from the String representation of the character.
	 *
	 * @param character The String representation of this score's character.
	 */
	public void setCharacter(String character) {
		this.character = Character.valueOf(character);
	}
}