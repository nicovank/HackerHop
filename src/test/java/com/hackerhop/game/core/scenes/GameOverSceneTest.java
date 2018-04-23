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
public class GameOverSceneTest {
    GameOverScene gameOver;
    MainController controller;
    MainMenu mainMenu;

    @BeforeAll
    public void setupMock(){
        controller = mock(MainController.class, withSettings().defaultAnswer(RETURNS_MOCKS));
        gameOver = mock(GameOverScene.class, withSettings().defaultAnswer(RETURNS_MOCKS));
        mainMenu = mock(MainMenu.class, withSettings().defaultAnswer(RETURNS_MOCKS));
    }
    @Test
    void escapeKeyTest() {
        when(gameOver.keyDown(Input.Keys.ESCAPE)).thenReturn(true);
        controller.setScene(gameOver);
        gameOver.keyDown(Input.Keys.ESCAPE);
        if(gameOver.keyDown(Input.Keys.ESCAPE)){
            assertEquals(mainMenu.getTAG(), controller.getCurrentScene().getTAG());
        }
    }
}
