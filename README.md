# Hacker-Hop

<p align="center">
<img width="270" src="https://github.com/nicovank/HackerHop/blob/master/logo.png?raw=true" alt="Hacker-Hop Logo" />
</p>

## Project Description

Hacker-Hop should be a top down, “Doodle-jump” inspired platform game. 
The player will have to jump from platform to platform, avoiding obstacles 
and hazards in a quest to obtain the highest score. The main character will be a 
SUNY Oswego Computer Science student trying to navigate through their college career. 
The obstacles they face will include Due Dates, Student Loans, Lake Effect Weather, 
and anything else life in Oswego throws at them. The obstacles they face will include:

 - **Due Dates:** As the player progresses upwards, they will have to avoid “Due-Dates”. 
 These will take shape as rectangular blocks of paper which fall from the sky. The player 
 will have to navigate around these in order to succeed in their classes (progress through the levels).
 If the player comes in contact with a “Due-Date” the game is over.

 - **Student Loans:** As the player progresses upwards, student loans will chase them from below.
 They will take the shape of rectangular blocks of paper chasing them upwards through game levels.
 The player will have to avoid these in order to continue game progression. If the player comes in
 contact with a “Student Loan” the game is over. 

 - **Lake Effect Weather:** Further into the game, the player will come across weather hazard signs.
 These will indicate that a gust of wind will push the player to either the left or right side of the
 screen. This can knock the player off their platform, ending the game. 

The goal of the game is to navigate upwards, while avoiding obstacles and accumulating points that 
add to the player’s overall High-Score. The higher the player gets, and the more points they gather,
the harder gameplay will become. This entails increased speed of gameplay, faster moving obstacles, 
and more hazards for the player to avoid.

  
The team developing this project consists of:
 - Katie Gordon
 - Ye Bhone Myat
 - Robert Sgroi
 - Nicolas Van Kempen


## System Requirements

| Identifier 	| Priority 	| Requirements                                                                                                                 	|
|------------	|----------	|------------------------------------------------------------------------------------------------------------------------------	|
| FUNC-1     	| 10       	| Program must generate platforms for the player to jump on.                                                                   	|
| FUNC-2     	| 10       	| Program needs a player that moves upward, left, and right.                                                                   	|
| FUNC-3     	| 9        	| Screen must follow player movement.                                                                                          	|
| FUNC-4     	| 8        	| A score needs to be displayed, determined by the progress of the player’s position.                                          	|
| QUA-1      	| 1        	| Users need to be able to compare their scores using leaderboards.                                                            	|
| QUA-2      	| 7        	| Gameplay will involve obstacles that will impede the player’s progression                                                    	|
| QUA-3      	| 6        	| Obstacle difficulty/generation will be tied to player’s progress.                                                            	|
| QUA-4      	| 4        	| Point accumulation corresponds to levels.                                                                                    	|
| QUA-5      	| 5        	| Random events, signaled before they arrive, will add some challenge, different for every run.                                	|
| QUA-6      	| 2        	| The theme of the game should be a parody of the experience of a typical Oswego Computer Science/Software Engineering Student 	|
| QUA-7      	| 0        	| There is a character selection menu before starting the game, each character having different characteristics and bonuses.   	|


## User Stories

| Identifier 	| User Story                                                                                                	| Size      	|
|------------	|-----------------------------------------------------------------------------------------------------------	|-----------	|
| ST-1       	| As a player, I want the character to move up, left, and right when I press the up, left and right buttons 	| 5 points  	|
| ST-2       	| As a player, I can have platforms that I can jump on.                                                     	| 7 points  	|
| ST-3       	| As an Oswego student, I can see some references to stuff that happened or is happening in Oswego.         	| 10 points 	|
| ST-4       	| As a player, I want obstacles and random events that make gameplay more challenging.                      	| 7 points  	|
| ST-5       	| As a player, I want to be able to compare my high score to others using a leaderboard.                    	| 2 points  	|
| ST-6       	| As a player, I want to be able to advance through game levels, accumulating more points.                  	| 8 points  	|


## Use cases

| Actor  | Actor's goal                                                              | Use case                          |
|--------|---------------------------------------------------------------------------|-----------------------------------|
| Player | Choose a character to use in game play.                                   | CharacterSelection (UC-4)         |
| Player | Starting on the ground, jump up platforms going into the sky, earn points | StartGame (UC-1), Gameplay (UC-2) |
| Player | Use a leaderboard to compare high scores                                  | Leaderboard (UC-5)                |
| Player | Die                                                                       | Die (UC-6)                        |
| Game   | Generate obstacles that the player will the need to avoid                 | Obstacles (UC-3)                  |
| Game   | Update leaderboards                                                       | Leaderboard (UC-5)                |
| Server | Store and keep track of the highest scores                                | Leaderboard (UC-5)                |

### StartGame (UC-1)

<table>
	<tr>
		<td><b>Initiating actor</b></td>
		<td>Player</td>
	</tr>
	<tr>
    	<td><b>Actor's goals</b></td>
    	<td>Start a new game.</td>
    </tr>
	<tr>
    	<td><b>Participating actors</b></td>
    	<td>Game</td>
    </tr>
	<tr>
    	<td><b>Pre-conditions</b></td>
    	<td>The player has selected a character.</td>
    </tr>
	<tr>
    	<td><b>Post-conditions</b></td>
    	<td>The game has been initialized in such a way that the player is resting on the ground, 
    		and there are enough platforms to allow him to go up.</td>
    </tr>
    <tr>
    	<td><b>Flow of events</b></td>
    	<td>
    		<ol>
    			<li>The player clicks the “Start new game button”.</li>
    			<li>The game generates platforms and initializes the game to an initial state (score of zero, initial background, etc…).</li>
    		</ol>
    	</td>
    </tr>
</table>

### Gameplay (UC-2)

<table>
	<tr>
		<td><b>Initiating actor</b></td>
		<td>Player</td>
	</tr>
	<tr>
    	<td><b>Actor's goals</b></td>
    	<td>Avoid obstacles, progress upwards, earn points, be able to jump on platforms.</td>
    </tr>
	<tr>
    	<td><b>Participating actors</b></td>
    	<td>Game</td>
    </tr>
	<tr>
    	<td><b>Pre-conditions</b></td>
    	<td>The game already started.</td>
    </tr>
	<tr>
    	<td><b>Post-conditions</b></td>
    	<td>The player is in control of the character on screen and the game updates the score, obstacles, ...</td>
    </tr>
    <tr>
    	<td><b>Flow of events</b></td>
    	<td>
    		<ol>
    			<li>The player can press LEFT, RIGHT (or A / D) and SPACE (or W or UP) to move the character on screen.</li>
    			<li>The game updates the score depending on how much altitude the player was able to go to, 
    				and generates platforms and obstacles.</li>
    			<li>If the player hits an obstacles or misses a platform and falls below the last platform he jumped on, 
    				he <i>dies</i> (see UC-6).</li>
    		</ol>
    	</td>
    </tr>
</table>

### Obstacles (UC-3)

<table>
	<tr>
		<td><b>Initiating actor</b></td>
		<td>Game</td>
	</tr>
	<tr>
    	<td><b>Actor's goals</b></td>
    	<td>The game generates obstacles that the player needs to avoid.</td>
    </tr>
	<tr>
    	<td><b>Participating actors</b></td>
    	<td>Player</td>
    </tr>
	<tr>
    	<td><b>Pre-conditions</b></td>
    	<td>The game has started, and the player controls the character.</td>
    </tr>
	<tr>
    	<td><b>Post-conditions</b></td>
    	<td>The player either successfully passes the obstacles, or falls, ending gameplay.</td>
    </tr>
    <tr>
    	<td><b>Flow of events</b></td>
    	<td>
    		<ol>
    			<li>The player passes the previous section of obstacles.</li>
    			<li>The game generates new obstacles above the player, taht will come on screen with the camera scroll.</li>
    			<li>The game removes the previous obstacles once they go out of bounds.</li>
    		</ol>
    	</td>
    </tr>
</table>

### CharacterSelection  (UC-4)

<table>
	<tr>
		<td><b>Initiating actor</b></td>
		<td>Player</td>
	</tr>
	<tr>
    	<td><b>Actor's goals</b></td>
    	<td>Choose a character.</td>
    </tr>
	<tr>
    	<td><b>Participating actors</b></td>
    	<td>Game</td>
    </tr>
	<tr>
    	<td><b>Pre-conditions</b></td>
    	<td>The application is launched.</td>
    </tr>
	<tr>
    	<td><b>Post-conditions</b></td>
    	<td>The player selected a character and is ready to start a new game.</td>
    </tr>
    <tr>
    	<td><b>Flow of events</b></td>
    	<td>
    		<ol>
    			<li>The game displays a character selection screen.</li>
                <li>The player navigates the menu and selects a character. He then presses “Start game”.</li>
                <li>The game <i>starts</i> (see UC-1)</li>
    		</ol>
    	</td>
    </tr>
</table>

### Leaderboard   (UC-5)

<table>
	<tr>
		<td><b>Initiating actor</b></td>
		<td>Game, Server</td>
	</tr>
	<tr>
    	<td><b>Actor's goals</b></td>
    	<td>Keep track of the player's high score, and global high scores, and compare those.</td>
    </tr>
	<tr>
    	<td><b>Participating actors</b></td>
    	<td>Player</td>
    </tr>
	<tr>
    	<td><b>Pre-conditions</b></td>
    	<td>There is no game currently running, the player is in the menu. Alternatively the player just ended a game.</td>
    </tr>
	<tr>
    	<td><b>Post-conditions</b></td>
    	<td>The player can see his score in a table with the other high scores.</td>
    </tr>
    <tr>
    	<td><b>Flow of events</b></td>
    	<td>
    		<ol>
    			<li>The player clicks the "Leaderboard" button or finishes a game.</li>
    			<li>The game sends the player's high score to the server.</li>
    			<li>The server records that high score, and returns a list of the closest scores and the highest scores.</li>
    			<li>The game displays the highest scores to the player.</li>
    		</ol>
    	</td>
    </tr>
</table>

### Dies (UC-6)

<table>
	<tr>
		<td><b>Initiating actor</b></td>
		<td>Player</td>
	</tr>
	<tr>
    	<td><b>Actor's goals</b></td>
    	<td>Die.</td>
    </tr>
	<tr>
    	<td><b>Participating actors</b></td>
    	<td>Game, Server</td>
    </tr>
	<tr>
    	<td><b>Pre-conditions</b></td>
    	<td>The player hit an obstacle or went out of bounds.</td>
    </tr>
	<tr>
    	<td><b>Post-conditions</b></td>
    	<td>Adds score to the leaderboard and displays a “Game over” screen.</td>
    </tr>
    <tr>
    	<td><b>Flow of events</b></td>
    	<td>
    		<ol>
    			<li>Player fails to land on a platform, or hits an obstacle.</li>
                <li>The game sends the score to the server (see UC-5).</li>
                <li>The game displays a “Game over” screen.</li>
    		</ol>
    	</td>
    </tr>
</table>

## Traceability Matrix

|**Requirement**|**PW**|**UC-1**|**UC-2**|**UC-3**|**UC-4**|**UC-5**|**UC-6**|
| :---:         |:---: |:---:   |:---:   |:---:   |:---:   |:---:   |:---:   |
| FUNC-1        | 10   | X      | X      |        |        |        |        |
| FUNC-2        | 10   | X      | X      |        |        |        | X      |
| FUNC-3        | 9    | X      | X      |        |        |        |        |
| FUNC-4        | 8    |        |        |        |        | X      |        |
| QUA-1         | 1    |        | X      |        |        | X      |        |
| QUA-2         | 7    |        | X      | X      |        |        | X      |
| QUA-3         | 6    |        | X      | X      |        |        |        |
| QUA-4         | 4    |        |        |        |        | X      |        |
| QUA-5         | 5    |        | X      | X      |        |        | X      |
| QUA-6         | 2    |        |        |        |        |        |        |
| QUA-7         | 0    |        |        |        | X      |        |        |
| **MAX PW**    |      | 10     | 10     | 7      | 0      | 8      | 10     |
| **TOTAL PW**  |      | 29     | 38     | 18     | 0      | 13     | 22     |


# UML Diagrams

## Class diagram

![Class diagram](https://raw.githubusercontent.com/nicovank/HackerHop/master/uml/diagrams/diagram.png)

