package com.hackerhop.game.core.objects;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class Platform {

    float top, bottom, leftEdge, rightEdge, width, height;

    public Platform(float tp, float wdth, float hght, float lft){
        this.top = tp;
        this.bottom = tp - hght;
        this.leftEdge = lft;
        this.rightEdge = lft + wdth;
    }

    public void render(ShapeRenderer r){
        //Set dimensions
        width = rightEdge - leftEdge;
        height = top - bottom;
        //Build rectangle and set color/texture
        r.rect(leftEdge, bottom, width, height);
        r.setColor(Color.PINK);
    }
}
