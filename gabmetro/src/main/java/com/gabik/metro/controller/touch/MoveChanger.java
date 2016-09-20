package com.gabik.metro.controller.touch;

import com.gabik.metro.controller.Placeable;

/**
 * Created by GaBiK on 12.11.2015.
 */
public class MoveChanger extends ViewChanger {
    private final float SENSITIVITY = 7;

    private Point currentTouchPoint;

    public MoveChanger(Placeable placeable) {
        super(placeable);
    }



    public void translate(float positionX, float positionY){

        Point dxPoint = getdXPoint(positionX, positionY);

        Point newPosition = new Point(dxPoint.x, dxPoint.y);

        placeable.updatePosition(newPosition);
    }

    private Point getdXPoint(float positionX, float positionY){
        float dX = positionX - currentTouchPoint.x;
        float dY = positionY - currentTouchPoint.y ;

        // Уменьшаем мелькания при кликах
        if (Math.abs(dX) < SENSITIVITY){
            dX = 0;
        }
        if (Math.abs(dY) < SENSITIVITY){
            dY = 0;
        }

        // Ограничение верхнего левого угла
        if (dX > 0 && placeable.getLeftTopPointScreen().x <=
                placeable.getCurrentLeftTopPointWitchScaling().x){
            dX = 0;
        }
        if (dY > 0 && placeable.getLeftTopPointScreen().y <= placeable.getCurrentLeftTopPointWitchScaling().y){
            dY = 0;
        }


        // Ограничение нижнего правого угла
        if (dX < 0 && placeable.getRightBottomPointScreen().x >= placeable.getCurrentRightBottomPointWitchScaling().x){
            dX = 0;
        }
        if (dY < 0 && placeable.getRightBottomPointScreen().y >= placeable.getCurrentRightBottomPointWitchScaling().y){
            dY = 0;
        }

        currentTouchPoint.x = positionX;
        currentTouchPoint.y = positionY;

        return new Point(dX, dY);
    }

    public void setStartTouchPoint(float positionX, float positionY){
        currentTouchPoint = new Point(positionX, positionY);
    }


}
