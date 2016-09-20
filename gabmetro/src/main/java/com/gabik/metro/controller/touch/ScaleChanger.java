package com.gabik.metro.controller.touch;

import com.gabik.metro.controller.Placeable;

/**
 * Created by GaBiK on 12.09.2016.
 */
public class ScaleChanger extends ViewChanger {
    private static final float SCALE_SPEED = 0.55f;
    private static final float MAX_SCALE = 2.5f;
    private static final float MIN_SCALE = 0.8f;
    private Point pivotPoint;
    private double initDistance;
    private double initScale;

    public ScaleChanger(Placeable placeable) {
        super(placeable);
    }

    public void initScale(Point begin, Point end){
        initDistance = calculateDistance(begin, end);
        initScale = placeable.getScale();


        //setPivot(getCorrectedPoint(begin), getCorrectedPoint(end));
    }

    private float getPercentDiffDistance(float newDistance){
        if (newDistance > initDistance){
            return  (float)((newDistance - initDistance) / (newDistance / 100)) / SCALE_SPEED;
        } else {
            return -(float)((initDistance - newDistance) / (initDistance / 100))/ SCALE_SPEED;
        }
    }

    public void scale(Point begin, Point end){
        float distance = calculateDistance(begin, end);
        float newScale = (float) initScale + getPercentDiffDistance(distance) * 0.01f;
        if (newScale < MAX_SCALE && newScale > MIN_SCALE) {
            placeable.setScale((float) initScale + getPercentDiffDistance(distance) * 0.01f);
        }
    }

    private void setPivot(Point begin, Point end){

        Point pointBegin = placeable.getCorrectPointInvert(new Point(begin.x, end.x));
        Point pointEnd = placeable.getCorrectPointInvert(new Point(begin.y, end.y));

        float pivotX = Math.abs(pointBegin.x - pointEnd.x) /
                2 + (pointBegin.x > pointEnd.x ? pointEnd.x : pointBegin.x );
        float pivotY = Math.abs(begin.y - end.y) /
                2 + (pointBegin.y > pointEnd.y ? pointEnd.y : pointBegin.y);
        pivotPoint = new Point(pivotX, pivotY);
        placeable.setPivot(pivotPoint);
    }

    private float calculateDistance(Point begin, Point end){
        return (float)Math.sqrt(Math.pow(end.x-begin.x,2) + Math.pow(end.y-begin.y,2));
    }


}
