package com.gabik.metro.controller.touch;

import com.gabik.metro.controller.Placeable;

/**
 * Created by GaBiK on 12.09.2016.
 */
public class ViewChanger {
    protected Placeable placeable;

    public ViewChanger(Placeable placeable) {
        this.placeable = placeable;
    }

    public Point getCorrectedPoint(Point notCorreted){
        return placeable.getCorrectPointInvert(notCorreted);
    }
}
