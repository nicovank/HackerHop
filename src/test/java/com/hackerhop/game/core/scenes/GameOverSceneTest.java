package com.hackerhop.game.core.scenes;

import com.badlogic.gdx.Input;
import com.hackerhop.game.core.MainController;
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
        controller = mock(MainController.class);
        gameOver = mock(GameOverScene.class);
        mainMenu = mock(MainMenu.class);
    }
    @Test
    void escapeKeyTest() {
        if(gameOver.keyDown(Input.Keys.ESCAPE)){
            Scene newScene = controller.getCurrentScene();
            assertEquals(mainMenu, newScene);
        }
    }
}
