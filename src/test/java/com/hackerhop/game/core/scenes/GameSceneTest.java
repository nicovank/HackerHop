package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Input;
import com.hackerhop.game.core.MainController;
import com.hackerhop.game.core.player.Player;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class GameSceneTest {
    GameOverScene gameOver;
    GameScene gameScene;
    MainController controller;
    Player player;

    @BeforeAll
    public void setupMock(){
        controller = mock(MainController.class, withSettings().defaultAnswer(RETURNS_MOCKS));
        player = mock(Player.class, withSettings().defaultAnswer(RETURNS_MOCKS));
        gameOver = mock(GameOverScene.class, withSettings().defaultAnswer(RETURNS_MOCKS));
        gameScene = mock(GameScene.class, withSettings().defaultAnswer(RETURNS_MOCKS));
    }
    @Test
    void playerDeathTest() {
        controller.setScene(gameScene);
        gameScene.playerDeath();
        assertEquals(gameOver.getTAG(), controller.getCurrentScene().getTAG());
    }
}
