package com.hackerhop.game.core.objects;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.hackerhop.game.core.graphics.GraphicsElement;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.BodyDef;
import org.jbox2d.dynamics.BodyType;
import org.jbox2d.dynamics.FixtureDef;
import org.jbox2d.dynamics.World;

public class TextbookObstacle extends PhysicalObject implements GraphicsElement {
    private Texture texture;
    private float x = 25;
    private float y = 100;
    private static final float WIDTH = 50;
    private static final float HEIGHT = 50;

    //TODO: Make a falling deadline
    public TextbookObstacle(World world){

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.DYNAMIC;
        bodyDef.position.set(new Vec2(x, y));

        super.setBody(world.createBody(bodyDef));

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(3,3);
        fixtureDef.shape = rectangle;
        super.getBody().createFixture(fixtureDef);
    }


    public void render(SpriteBatch batch) {
        batch.draw(texture, super.getBody().getPosition().x * 10,
                super.getBody().getPosition().y * 10,
                WIDTH,
                HEIGHT);
    }

    @Override
    public void loadGraphics() {
        texture = new Texture("deadline/textbooks.png");
    }

    @Override
    public void dispose() {
        texture.dispose();
    }
}
