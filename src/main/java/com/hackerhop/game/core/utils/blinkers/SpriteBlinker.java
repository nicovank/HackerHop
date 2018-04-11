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

    /**
     * renders the sprite at the given position, overriding the Sprite's position.
	 *
     * @param batch The batch where to draw the sprite.
     * @param x The x-coordinate where to draw the sprite.
     * @param y The y-coordinate where to draw the sprite.
     */
    public void render(SpriteBatch batch, float x, float y) {
        if (super.shouldBlink()) {
            batch.draw(sprite, x, y);
        }
    }
}
