package com.gabik.metro.controller.touch;


import java.util.List;

/**
 * Created by GaBiK on 06.11.2015.
 */
public class SelectHandler {


    public SelectHandler(){
    }

    public Selectable getSelectElement(List<Selectable> baseElementList, Point point){
        for (Selectable baseElement : baseElementList){
            SelectedRectangle selectedRectangle = baseElement.getSelectedRectangle();
            if (isPointInRectangle(selectedRectangle, point)){
                return baseElement;
            }
        }
        return null;
    }

    private boolean isPointInRectangle(SelectedRectangle selectedRectangle, Point point){
        if (selectedRectangle.xLeftTop < point.x && selectedRectangle.yLeftTop < point.y &&
                selectedRectangle.xRightDown > point.x && selectedRectangle.yRightDown > point.y){
            return true;
        }
        return false;
    }
}
