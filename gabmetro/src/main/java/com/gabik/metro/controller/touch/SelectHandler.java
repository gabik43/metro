package com.gabik.metro.controller.touch;


import java.util.List;

/**
 * Created by GaBiK on 06.11.2015.
 */
public class SelectHandler {

    private Selectable oldSelectElement;
    public SelectHandler(){
    }

    public void selectElement(List<Selectable> baseElementList, Point point){
        for (Selectable baseElement : baseElementList){
            SelectedRectangle selectedRectangle = baseElement.getSelectedRectangle();
            if (isPointInRectangle(selectedRectangle, point)){
                if (oldSelectElement != null){
                    oldSelectElement.deselect();
                }
                baseElement.select();
                oldSelectElement = baseElement;
                return;
            }
        }
    }

    private boolean isPointInRectangle(SelectedRectangle selectedRectangle, Point point){
        if (selectedRectangle.xLeftTop < point.x && selectedRectangle.yLeftTop < point.y &&
                selectedRectangle.xRightDown > point.x && selectedRectangle.yRightDown > point.y){
            return true;
        }
        return false;
    }
}
