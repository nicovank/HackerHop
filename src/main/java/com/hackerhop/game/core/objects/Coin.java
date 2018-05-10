package com.hackerhop.game.core.objects;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.hackerhop.game.core.graphics.GraphicsElement;
import com.hackerhop.game.core.utils.Constants;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class Coin extends PhysicalObject implements GraphicsElement, Constants {

    private static final float ANIMATION_DURATION = .1f;

    private Texture texture;
    private TextureRegion currentFrame;
    private Animation animation;
    private float timeAccumulator = 0;


    public Coin(float x, float y, World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.KINEMATIC;
        bodyDef.position.set(new Vec2(x, y));

        super.setBody(world.createBody(bodyDef));
        super.getBody().setUserData("coin");

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(2, 2);
        fixtureDef.shape = rectangle;
        super.getBody().createFixture(fixtureDef);
    }

    public void update() {
        timeAccumulator += Gdx.graphics.getDeltaTime();
        currentFrame = animation.getKeyFrame(timeAccumulator);
    }

    @Override
    public void loadResources() {
        texture = new Texture("coin.png");
        TextureRegion[] frames = TextureRegion.split(texture, 33, 33)[0];

        animation = new Animation(ANIMATION_DURATION, frames);
        animation.setPlayMode(Animation.PlayMode.LOOP);
    }

    @Override
    public void render(SpriteBatch batch) {
        batch.draw(currentFrame,
                super.getBody().getPosition().x * PHYSICS_RATIO,
                super.getBody().getPosition().y * PHYSICS_RATIO
        );
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
