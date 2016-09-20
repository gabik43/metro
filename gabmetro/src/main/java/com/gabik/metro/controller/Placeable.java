package com.gabik.metro.controller;

import com.gabik.metro.controller.touch.Point;

/**
 * Created by GaBiK on 08.09.2016.
 */
public interface Placeable {
    //Point getLeftTopPointScreen();
    //Point getRightBottomPointScreen();
    //Point getCurrentLeftTopPointWitchScaling();
    //Point getCurrentRightBottomPointWitchScaling();
    //void setPivot(Point pivotPoint);
    //void updatePosition(Point pointTranslate);
    //void setScale(float scale);
    //float getScale();
    //Point getCorrectPointInvert(Point point);

    void updatePosition(Point newPosition);
    void updateScale(Point touchOne, Point touchTwo);


}
