package com.hackerhop.game.core.objects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;


public class Platform {

    float top, bottom, leftEdge, rightEdge;

    public Platform(float top, float width, float height, float left) {

        this.top = top;
        this.bottom = top - height;
        this.leftEdge = left;
        this.rightEdge = width + left;

    }

    /**
     * Width is the distance between the left and right sides
     * Height is the difference between the top and the bottom
     * libgdx uses the coordinates of bottom left corner and width and height to construct a rectangle
     * Color currently gets set to Teal--obviously this can be changed
     **/
    public void rectRender(ShapeRenderer r) {

        float width = rightEdge - leftEdge;
        float height = top - bottom;

        r.rect(leftEdge, bottom, width, height);
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
    public int distanceTo(Platform that) {
        int w = (int) ((this.leftEdge + ((this.rightEdge - this.leftEdge) / 2)) -
                ((that.leftEdge + ((that.rightEdge - that.leftEdge) / 2))));

        int h = (int) ((this.bottom + ((this.top - this.bottom) / 2)) -
                (that.bottom + ((that.top - that.bottom) / 2)));

        int d = (int) Math.sqrt((w * w) + (h * h));

        return d;
    }

    /**
     * Generates a hashcode based on this Platform object's coordinates.
     * The Platform's bottom value is put in the last three digits of the hashcode.
     * The rest of the hashcode is the leftEdge value.
     *
     * @return the hashcode generated from this Platform's coordinates
     */
    @Override
    public int hashCode(){
        int i = (int) ((1000 * leftEdge) + bottom);
        return i;
    }

//    @Override
//    public boolean equals(Object o){
//        if (o instanceof Platform){
//            int threshold = 500;
//            Platform that = (Platform) o;
//            int i = this.distanceTo(that);
//            return i < threshold;
//        } else {
//            return false;
//        }
//    }


}
