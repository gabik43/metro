package com.gabik.metro.controller.touch;

import android.graphics.*;

/**
 * Created by GaBiK on 12.11.2015.
 */
public class TranslateHandler {
    private final float SENSITIVITY = 7;
    private float positionX, positionY;

    public Point getTranslate(float positionX, float positionY){
        float dX = this.positionX - positionX;
        float dY = this.positionY - positionY;
        Point point = new Point(Math.abs(dX) > SENSITIVITY ? dX : 0, Math.abs(dY) > SENSITIVITY ? dY : 0);
        this.positionX = positionX;
        this.positionY = positionY;
        return point;
    }

    public void setStartPoint(float positionX, float positionY) {
        this.positionX = positionX;
        this.positionY = positionY;
    }
}
