package com.gabik.metro.view.drawElements;

import android.graphics.Canvas;

/**
 * Created by GaBiK on 29.02.2016.
 */
public abstract class DrawHandler {
    protected Canvas canvas;
    public void setActualCanvas(Canvas canvas){
        this.canvas = canvas;
    }
}
