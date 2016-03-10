package com.gabik.metro.model.param;

import android.graphics.Canvas;
import android.graphics.Point;
import com.gabik.metro.controller.touch.SelectedRectangle;
import com.gabik.metro.model.elements.ConstStation;
import com.gabik.metro.view.drawElements.DrawCommunication;
import com.gabik.metro.view.drawElements.DrawHandler;
import com.gabik.metro.view.drawElements.DrawStation;
import com.gabik.metro.view.drawElements.Drawable;

/**
 * Created by GaBiK on 02.03.2016.
 */
public class DrawParamStation implements Drawable {
    private Point point;
    public Point getPoint(){
        return point;
    }
    public float getX(){
        return point.x;
    }
    public float getY(){
        return point.y;
    }

    private float radius;
    public float getRadius() {
        return radius;
    }

    private float radiusBorder;
    public float getRadiusBorder() {
        return radiusBorder;
    }


    private int color;
    public int getColor() {
        return color;
    }

    private SelectedRectangle selectedRectangle;
    public SelectedRectangle getSelectedRectangle(){
        return selectedRectangle;
    }

    public DrawParamStation(Point point, int color, SelectedRectangle selectedRectangleName) {
        this.point = point;
        this.radius = ConstStation.STATION_RADIUS_NORMAL;
        this.radiusBorder = ConstStation.STATION_RADIUS_BORDER;
        this.color = color;
        float radiusWithSens = radius + ConstStation.SENSITIVITY;
        selectedRectangle = new SelectedRectangle(point.x - radiusWithSens, point.y - radiusWithSens,
                point.x + radiusWithSens, point.y + radiusWithSens);
        selectedRectangle.joinRectangle(selectedRectangleName);
    }

    public void activeParam(){
        radiusBorder = ConstStation.STATION_RADIUS_BORDER_SELECTED;
        radius = ConstStation.STATION_RADIUS_SELECTED;
    }
    public void deActiveParam(int color){
        radiusBorder = ConstStation.STATION_RADIUS_BORDER;
        radius = ConstStation.STATION_RADIUS_NORMAL;
    }


    @Override
    public void draw(DrawHandler drawHandler) {
        ((DrawStation)drawHandler).drawStation(this);
    }
}
