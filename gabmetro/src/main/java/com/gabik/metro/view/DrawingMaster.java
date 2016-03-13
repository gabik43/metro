package com.gabik.metro.view;

import android.graphics.Canvas;
import com.gabik.metro.model.*;
import com.gabik.metro.model.param.ParamsDerivable;
import com.gabik.metro.view.drawElements.DrawCommunication;
import com.gabik.metro.view.drawElements.DrawName;
import com.gabik.metro.view.drawElements.DrawStation;

/**
 * Created by GaBiK on 05.11.2015.
 */
public class DrawingMaster {
    private Model model;
    private DrawCommunication drawCommunication;
    private DrawStation drawStation;
    private DrawName drawName;

    public DrawingMaster(Model model){
        this.model = model;
        drawCommunication = new DrawCommunication();
        drawStation = new DrawStation();
        drawName = new DrawName();
    }

    public void Draw(Canvas canvas){
        try {
            canvas.save();
            drawCommunication.setActualCanvas(canvas);
            drawStation.setActualCanvas(canvas);
            drawName.setActualCanvas(canvas);

            for (ParamsDerivable objectOnMap : model.getDrawCommunication()){
                objectOnMap.getParam().draw(drawCommunication);
            }

            for (ParamsDerivable objectOnMap : model.getDrawableStation()){
                objectOnMap.getParam().draw(drawStation);
            }

            for (ParamsDerivable objectOnMap : model.getDrawNames()){
                objectOnMap.getParam().draw(drawName);
            }

            canvas.restore();
        } catch (Exception ex){
            System.out.print(ex.getMessage());
            ex.printStackTrace();
        }
    }
}
