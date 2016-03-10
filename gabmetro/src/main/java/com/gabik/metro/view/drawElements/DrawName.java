package com.gabik.metro.view.drawElements;

import android.graphics.Color;
import android.graphics.Paint;
import com.gabik.metro.model.param.DrawParamNameStation;
import com.gabik.metro.model.param.DrawParamNameStationTwoLine;

/**
 * Created by GaBiK on 06.03.2016.
 */
public class DrawName extends DrawHandler {
    private static Paint paint = new Paint();
    private static Paint paintBorder = new Paint();
    private static Paint interiorOval = new Paint();
    private static Paint whiteOval = new Paint();
    static {
        paintBorder = new Paint();
        paintBorder.setTextSize(18);
        paintBorder.setColor(Color.BLACK);

    }
    public static float getWidthText(String text){
        return paintBorder.measureText(text);
    }

    public void drawNameStation(DrawParamNameStation drawParamNameStation){
        paintBorder.setColor(drawParamNameStation.getColor());
        canvas.drawText(drawParamNameStation.getName(), drawParamNameStation.getPositionX(),
                drawParamNameStation.getPositionY(), paintBorder);
    }

    public void drawTwoLineNameStation(DrawParamNameStationTwoLine drawParamNameStation){
        paintBorder.setColor(drawParamNameStation.getColor());
        canvas.drawText(drawParamNameStation.getOnePartName(), drawParamNameStation.getPositionX(),
                drawParamNameStation.getPositionY(), paintBorder);
        canvas.drawText(drawParamNameStation.getTwoPartName(), drawParamNameStation.getPositionX(),
                drawParamNameStation.getpositionTwoPartY(), paintBorder);
    }
}
