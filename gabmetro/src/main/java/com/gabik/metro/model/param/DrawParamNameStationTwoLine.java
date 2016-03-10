package com.gabik.metro.model.param;

import com.gabik.metro.model.elements.ConstStation;
import com.gabik.metro.view.drawElements.DrawHandler;
import com.gabik.metro.view.drawElements.DrawName;

/**
 * Created by GaBiK on 09.03.2016.
 * Имя станции метро разделенное на две строки
 */
public class DrawParamNameStationTwoLine extends DrawParamNameStation {

    private String onePartName;
    public String getOnePartName() {
        return onePartName;
    }

    private String twoPartName;
    public String getTwoPartName() {
        return twoPartName;
    }

    private int positionTwoPartY;
    public int getpositionTwoPartY() {
        return positionTwoPartY;
    }

    public DrawParamNameStationTwoLine(String nameOne, String onePartName, String twoPartName, int positionX,
                                       int positionY, int offsetX, int offsetY) {
        super(nameOne, positionX, positionY, offsetX, offsetY);
        float widthOnePartName = DrawName.getWidthText(onePartName);
        float widthTwoPartName = DrawName.getWidthText(twoPartName);
        this.positionY = this.positionY - 5;
        if (offsetX < 0) {
            this.positionX = this.positionX + (int) (widthOnePartName > widthTwoPartName ? widthOnePartName : widthTwoPartName) - 5;
        }
        this.positionTwoPartY = this.positionY + ConstStation.DISTANCE_BETWEEN_TWO_LINE;
        this.onePartName = onePartName;
        this.twoPartName = twoPartName;
    }

    @Override
    public void draw(DrawHandler drawHandler) {
        ((DrawName)drawHandler).drawTwoLineNameStation(this);
    }
}
