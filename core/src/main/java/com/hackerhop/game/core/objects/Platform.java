package com.hackerhop.game.core.objects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import org.jbox2d.collision.shapes.PolygonShape;
import org.jbox2d.common.Vec2;
import org.jbox2d.dynamics.*;


public class Platform {

    private float x, y;
    private static final float WIDTH = 60;
    private static final float HEIGHT = 20;
    private Body body;

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

    /**
     * Width is the distance between the left and right sides
     * Height is the difference between the top and the bottom
     * libgdx uses the coordinates of bottom left corner and width and height to construct a rectangle
     * Color currently gets set to Teal--obviously this can be changed
     **/
    public void rectRender(ShapeRenderer r) {

        r.rect(x, y, WIDTH, HEIGHT);
        r.setColor(Color.MAROON);

    }

//    /**
//     * Compares two Platform objects using their Euclidean distance.
//     * If the two Platforms are within a specified <code>threshold</code>, the method returns 0.
//     *
//     * @param o Platform object to compare to
//     * @return Euclidean distance between the two Platforms, or 0
//     */
//    public int compareTo(Object o) {
//        int threshold = 250;
//        Platform that = (Platform) o;
//        int i = this.distanceTo(that);
//        return i <= threshold ? 0 : i;
//    }

    /**
     * Returns the Euclidean distance of a Platform object from the current Platform.
     * Distance is calculated from the center of the Platforms.
     *
     * @param that target Platform object.
     * @return Euclidean distance from this Platform to <code>target</code> Platform.
     */
   /* public int distanceTo(Platform that) {
        int w = (int) ((this.x + ((this.rightEdge - this.x) / 2)) -
                ((that.x + ((that.rightEdge - that.x) / 2))));

        int h = (int) ((this.bottom + ((this.y - this.bottom) / 2)) -
                (that.bottom + ((that.y - that.bottom) / 2)));

        int d = (int) Math.sqrt((w * w) + (h * h));

        return d;
    } */

    /**
     * Generates a hashcode based on this Platform object's coordinates.
     * The Platform's bottom value is put in the last three digits of the hashcode.
     * The rest of the hashcode is the leftEdge value.
     *
     * @return the hashcode generated from this Platform's coordinates
     */
  /*  @Override
    public int hashCode() {
        int i = (int) ((1000 * x) + bottom);
        return i;
    }
*/

}
