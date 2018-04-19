package com.hackerhop.game.core.utils.blinkers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;

public abstract class Blinker implements Disposable {
    private float showTime;
    private float hideTime;
    private boolean isShowed; // start displaying the object by default
    private float blinkTimer;

    public Blinker(float showTime, float hideTime) {
        this(showTime, hideTime, true);
    }

    public Blinker(float showTime, float hideTime, boolean startShown) {
        this.showTime = showTime;
        this.hideTime = hideTime;
        this.isShowed = startShown;
    }

    public void update() {
        blinkTimer += Gdx.graphics.getDeltaTime();
    }

    public boolean shouldBlink() {
        if (isShowed) {
            if (blinkTimer > showTime) {
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

    public abstract void render(SpriteBatch batch);
}

