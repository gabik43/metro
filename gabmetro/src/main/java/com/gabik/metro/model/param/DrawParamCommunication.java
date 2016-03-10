package com.gabik.metro.model.param;

import android.graphics.Point;
import com.gabik.metro.view.drawElements.DrawCommunication;
import com.gabik.metro.view.drawElements.DrawHandler;
import com.gabik.metro.view.drawElements.Drawable;

import java.util.logging.Logger;

/**
 * Created by GaBiK on 28.02.2016.
 */
public class DrawParamCommunication implements Drawable {
    private static final Logger log = Logger.getLogger(DrawParamCommunication.class.getName());
    public Point pointOne;
    public Point pointTwo;
    public int color;

    public DrawParamCommunication(Point pointOne, Point pointTwo, int color) {
        this.pointOne = pointOne;
        this.pointTwo = pointTwo;
        this.color = color;
    }

    @Override
    public void draw(DrawHandler drawHandler) {
        DrawCommunication drawCommunication = (DrawCommunication)drawHandler;
        drawCommunication.drawLine(this);
    }
}
