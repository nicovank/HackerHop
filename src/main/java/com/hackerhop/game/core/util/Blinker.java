package com.hackerhop.game.core.util;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Blinker {
    private float showTime;
    private float hideTime;
    private boolean isShowed = true; // start displaying the object by default

    private float blinkTimer;
    private Sprite sprite;

    public Blinker(Sprite sprite, float showTime, float hideTime) {
        this.sprite = sprite;
        this.showTime = showTime;
        this.hideTime = hideTime;
    }

    private boolean shouldBlink(float d) {
        blinkTimer += d;

        if (isShowed) {
            if(blinkTimer > showTime) {
                blinkTimer = 0;
                isShowed = false;
            }
        } else {
            if (blinkTimer > hideTime) {
                blinkTimer = 0;
                isShowed = true;
            }
        }

        return isShowed;
    }

    public void render(SpriteBatch batch) {
        if (shouldBlink(Gdx.graphics.getDeltaTime())) {
            sprite.draw(batch);
        }
    }
}

