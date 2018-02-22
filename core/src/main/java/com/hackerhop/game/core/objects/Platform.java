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
        Width is the distance between the left and right sides
        Height is the difference between the top and the bottom
        libgdx uses the coordinates of bottom left corner and width and height to construct a rectangle
        Color currently gets set to Teal--obviously this can be changed
     **/
    public void rectRender(ShapeRenderer r){

        float width = rightEdge - leftEdge;
        float height = top - bottom;

        r.rect(leftEdge, bottom, width, height);
        r.setColor(Color.MAROON);


    }

}
