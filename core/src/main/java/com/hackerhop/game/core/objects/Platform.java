package com.hackerhop.game.core.objects;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;


public class Platform {

    private float x, y;
    private static final float WIDTH = 60;
    private static final float HEIGHT = 20;
    private Body body;
    public Texture texture = new Texture("platform/bricks.png");

    public Platform(float x, float y, World world) {

        this.x = x;
        this.y = y;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyType.STATIC;
        bodyDef.position.set(new Vec2(x, y));

        body = world.createBody(bodyDef);

        FixtureDef fixtureDef = new FixtureDef();
        PolygonShape rectangle = new PolygonShape();
        rectangle.setAsBox(100, 100);
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

        batch.draw(texture, body.getPosition().x , body.getPosition().y , WIDTH, HEIGHT);
       // r.rect(x, y, WIDTH, HEIGHT);
       // r.setColor(Color.MAROON);

    }

    /**
     * Calculates the Euclidean distance from this Platform to Platform <code>p</code>.
     *
     * @param p the target Platform
     * @return Euclidean distance between <code>this</code> and <code>p</code>
     */
    public int distanceTo(Platform p){
        int a = (int) this.x;
        int b = (int) this.y;

        return (int) Math.sqrt((a * a) + (b * b));
    }

}
