package com.hackerhop.game.core.utils.blinkers;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;

public class GraphicsElementBlinker extends Blinker implements GraphicsElement {
    
    private GraphicsElement element;

    public GraphicsElementBlinker(GraphicsElement element, float showTime, float hideTime) {
        super(showTime, hideTime);
        this.element = element;
    }

    @Override
    public void loadResources() {
        element.loadResources();
    }

    @Override
    public void render(SpriteBatch batch) {
        if(super.shouldBlink()) {
            element.render(batch);
        }
    }

    @Override
    public void dispose() {
        element.dispose();
    }
}
