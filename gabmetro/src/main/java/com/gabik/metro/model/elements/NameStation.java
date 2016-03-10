package com.gabik.metro.model.elements;

import com.gabik.metro.controller.touch.Selectable;
import com.gabik.metro.controller.touch.SelectedRectangle;
import com.gabik.metro.model.param.DrawParamNameStation;
import com.gabik.metro.model.param.DrawParamNameStationTwoLine;
import com.gabik.metro.model.param.Paramable;
import com.gabik.metro.view.drawElements.Drawable;

import java.util.logging.Logger;

/**
 * Created by GaBiK on 06.03.2016.
 */
public class NameStation implements Paramable, Selectable {
    private static final Logger log = Logger.getLogger(NameStation.class.getName());
    private DrawParamNameStation drawParamNameStation;
    public String getName() {
        return drawParamNameStation.getName();
    }

    public NameStation(String name, int positionX, int positionY, int offsetX, int offsetY){
        if (isTwoLine(name)){
            String nameFirstLine = getPartName(PartName.FIRST_PART, name);
            String nameSecondLine = getPartName(PartName.SECOND_PART, name);
            drawParamNameStation = new DrawParamNameStationTwoLine(name, nameFirstLine, nameSecondLine, positionX, positionY, offsetX, offsetY);
        } else {
            drawParamNameStation = new DrawParamNameStation(name, positionX, positionY, offsetX, offsetY);
        }
    }

    private static String DELIMITER = "\\n";
    /*Проверка состоит ли имя из двух строк. Разделителем является символ DELIMITER
    *
    * Например: Петровско-\nРазумовская*/
    private boolean isTwoLine(String name) {
       return name.contains(DELIMITER);
    }

    private String getPartName(PartName partName, String name){
        try {
            int index = name.indexOf(DELIMITER);
            switch (partName){
                case FIRST_PART:
                    return name.substring(0, index);
                case SECOND_PART:
                    return name.substring(index + DELIMITER.length(), name.length());
                default:
                    return name;
            }
        } catch (Exception e) {
            log.severe("Возникла ошибка при попытке разбивки имени: " + name + " на две строки. Часть получения: " + partName);
            log.severe(e.getMessage());
            e.printStackTrace();
            return name;
        }
    }

    @Override
    public Drawable getParam() {
        return drawParamNameStation;
    }

    @Override
    public SelectedRectangle getSelectedRectangle() {
        return drawParamNameStation.getSelectedRectangle();
    }

    @Override
    public void select() {
        drawParamNameStation.activeParam();
    }

    @Override
    public void deselect() {
        drawParamNameStation.deActiveParam();
    }
    private enum PartName {
        FIRST_PART,
        SECOND_PART
    }
}
