package com.gabik.metro.model.param;

import android.graphics.Point;
import com.gabik.metro.view.drawElements.DrawCommunication;
import com.gabik.metro.view.drawElements.DrawHandler;

import java.util.logging.Logger;

/**
 * Created by GaBiK on 11.03.2016.
 * Связи между станциями у которых есть точки перегиба.
 *
 * Связь строится между первой станцией - далее точки перегиба в порядке очередности в массиве и конечной станции.
 */
public class DrawParamBendCommunication extends DrawParamCommunication {
    private static final Logger log = Logger.getLogger(DrawParamBendCommunication.class.getName());

    private int bendPointsX[];
    private int bendPointsY[];

    public int getBendX(int index){
        return getElement(index, bendPointsX);
    }

    public int getBendY(int index){
        return getElement(index, bendPointsY);
    }

    private int getElement(int index, int [] mas){
        if (index >= countBendPoints) throw new  IllegalArgumentException("Index: " + index + " выходит за пределы " +
                "массива. Размер массива: " + countBendPoints);
        return mas[index];
    }

    private int countBendPoints;
    public int getCountBendPoints() {
        return countBendPoints;
    }

    public DrawParamBendCommunication(Point pointOne, Point pointTwo, int color, String bendPointsX, String bendPointsY) {
        super(pointOne, pointTwo, color);
        if (bendPointsX == null || bendPointsY == null)
            throw new IllegalArgumentException("Входные параметры: bendPointsX или bendPointsY не должны быть null");
        this.bendPointsX = initMasBendPoints(bendPointsX);
        this.bendPointsY = initMasBendPoints(bendPointsY);
    }

    private int[] initMasBendPoints(String bendPointsX) {
        try {
            String[] bendPointsString = bendPointsX.split(",");
            countBendPoints = bendPointsString.length;
            int[] returnBendPoint = new int[countBendPoints];
            for (int i = 0; i < bendPointsString.length; i++){
                returnBendPoint[i] = Integer.valueOf(bendPointsString[i]);
            }
            return returnBendPoint;
        } catch (Exception e) {
            log.severe("Возникла ошибка при попытке получить целочисленный массив из строки: " + bendPointsX +
                    " Exception: " + e.getMessage());
            e.printStackTrace();
            return new int[1];
        }
    }

    @Override
    public void draw(DrawHandler drawHandler) {
        DrawCommunication drawCommunication = (DrawCommunication)drawHandler;
        drawCommunication.drawLineBend(this);
    }
}
