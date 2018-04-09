package com.hackerhop.game.core.utils.blinkers;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteBlinker extends Blinker {
    
    private Sprite sprite;
    
    public SpriteBlinker(Sprite sprite, float showTime, float hideTime) {
        super(showTime, hideTime);
        this.sprite = sprite;
    }

    @Override
    public void render(SpriteBatch batch) {
        if (super.shouldBlink()) {
            sprite.draw(batch);
        }
    }
}
