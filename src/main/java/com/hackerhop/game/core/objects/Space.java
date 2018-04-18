package com.hackerhop.game.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.utils.Constants;


public class Space implements GraphicsElement, Constants {
    private static final float ANIMATION_DURATION = 1f;
    private final float x, y;

    private Texture texture;
    private TextureRegion currentFrame;
    private Animation animation;
    private float timeAccumulator = 0;

    public Space(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public void update() {
        timeAccumulator += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(timeAccumulator);
    }

    @Override
    public void loadResources() {
        texture = new Texture("HelpScreen/PressToStart.png");
        TextureRegion[] frames = TextureRegion.split(texture, 384, 66)[0];

        animation = new Animation(ANIMATION_DURATION, frames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(currentFrame, x, y);
    }

    @Override
    public void dispose() {
        texture.dispose();
    }

}
