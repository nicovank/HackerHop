package com.hackerhop.game.core.objects;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import java.util.Random;

public class Platform {

    Random r = new Random();
    float lmax = 480;
    float lmin, tmin = 0;
    float tmax = 660;
    float top, leftEdge, width, height;

    public Platform() {
        width = 60;
        height = 20;
    }
     /**
        Width is the distance between the left and right sides
        Height is the difference between the top and the bottom
        libgdx uses the coordinates of bottom left corner and width and height to construct a rectangle
        Color currently gets set to Teal--obviously this can be changed
     **/
    public void rectRender(ShapeRenderer renderer){
        top = r.nextFloat() * (tmax - tmin) + tmin;
        leftEdge = r.nextFloat() * (lmax - lmin) + lmin;
        renderer.rect(100, 100, width, height);
        renderer.setColor(Color.MAROON);
    }

}
