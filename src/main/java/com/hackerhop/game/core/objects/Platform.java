package com.hackerhop.game.core.objects;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;


public class Platform {


    private static final String TAG = Platform.class.getName();
    private float x, y;
    private float WIDTH;
    private static final float HEIGHT = 20;
    private Body body;
    public Texture texture = new Texture("platform/bricks.png");

    public Platform(float x, float y, World world) {

        this.x = x;
        this.y = y;
        WIDTH = 60;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;
        bodyDef.position.set(new Vec2(x, y));

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(3, -1);
        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);

    }

    /**
     * Separate constructor the base platform
     */
    public Platform(World world) {
        this.x = 0;
        this.y = 0;
        WIDTH = 540;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;
        bodyDef.position.set(new Vec2(x, y));

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(50, -1);
        fixtureDef.shape = rectangle;
        body.createFixture(fixtureDef);
    }

    public Body getBody() {
        return body;
    }

    /**
     * Width is the distance between the left and right sides
     * Height is the difference between the top and the bottom
     * libgdx uses the coordinates of bottom left corner and width and height to construct a rectangle
     * Color currently gets set to Teal--obviously this can be changed
     **/
    public void rectRender(SpriteBatch batch) {
        batch.draw(texture, body.getPosition().x * 10, body.getPosition().y * 10, WIDTH, HEIGHT);
    }

    /**
     * Calculates the distance between two Platforms <code>p</code>.
     *
     * @param p the target Platform
     * @return distance between <code>this</code> and <code>p</code>
     */
    public double distanceTo(Platform p){
       float a = p.x;
       float b = p.y;
        return Math.sqrt(Math.pow((this.x - a), 2) + Math.pow((this.y - b), 2));
    }

}
