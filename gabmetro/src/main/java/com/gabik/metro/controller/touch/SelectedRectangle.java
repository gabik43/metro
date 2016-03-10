package com.gabik.metro.controller.touch;

/**
 * Created by GaBiK on 06.11.2015.
 */
public class SelectedRectangle {
    public SelectedRectangle(float xLeftTop, float yLeftTop, float xRightDown, float yRightDown) {
        this.xLeftTop = xLeftTop;
        this.yLeftTop = yLeftTop;
        this.xRightDown = xRightDown;
        this.yRightDown = yRightDown;
    }
    public void joinRectangle(SelectedRectangle selectedRectangle){
        this.xLeftTop = selectedRectangle.xLeftTop > this.xLeftTop ? this.xLeftTop : selectedRectangle.xLeftTop;
        this.xRightDown = selectedRectangle.xRightDown > this.xRightDown ? selectedRectangle.xRightDown : this.xRightDown;
    }

    public float xLeftTop, yLeftTop, xRightDown, yRightDown;
}
