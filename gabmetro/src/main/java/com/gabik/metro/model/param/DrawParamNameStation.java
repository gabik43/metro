package com.gabik.metro.model.param;

import android.graphics.Color;
import com.gabik.metro.controller.touch.SelectedRectangle;
import com.gabik.metro.view.drawElements.DrawHandler;
import com.gabik.metro.view.drawElements.DrawName;
import com.gabik.metro.view.drawElements.Drawable;

/**
 * Created by GaBiK on 06.03.2016.
 */
public class DrawParamNameStation implements Drawable {

    private String name;

    @Override
    public String toString() {
        return "DrawParamNameStation{" +
                "name='" + name + '\'' +
                ", color=" + color +
                ", positionX=" + positionX +
                ", positionY=" + positionY +
                ", selectedRectangle=" + selectedRectangle +
                '}';
    }

    private int color;
    public int getColor() {
        return color;
    }

    public String getName() {
        return name;
    }

    protected int positionX;
    public int getPositionX() {
        return positionX;
    }

    protected int positionY;
    public int getPositionY() {
        return positionY;
    }



    public DrawParamNameStation(String name, int positionX, int positionY, int offsetX, int offsetY) {
        initName(name);
        float widthText = DrawName.getWidthText(name);
        color = Color.BLACK;
        if (offsetX > 0){
            this.positionX = positionX + offsetX;
            this.positionY = positionY + offsetY;
        } else {
            this.positionX = positionX - (int)widthText + offsetX;
            this.positionY = positionY + offsetY;
        }
        selectedRectangle = new SelectedRectangle(this.positionX, this.positionY, this.positionX + widthText, this.positionY + 10);

    }


    private void initName(String name) {
        this.name = name;
    }

    public void activeParam(){
        color = Color.RED;
    }
    public void deActiveParam(){
        color = Color.BLACK;
    }

    private SelectedRectangle selectedRectangle;
    public SelectedRectangle getSelectedRectangle(){
        return selectedRectangle;
    }

    @Override
    public void draw(DrawHandler drawHandler) {
        ((DrawName)drawHandler).drawNameStation(this);
    }
}
