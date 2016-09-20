package com.gabik.metro.view.drawElements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.View;
import com.gabik.metro.model.param.DrawParamStation;

/**
 * Created by GaBiK on 04.11.2015.
 */
public class DrawStation extends DrawHandler {
    private static Paint paint = new Paint();
    private static Paint paintBorder = new Paint();
    private static Paint interiorOval = new Paint();
    private static Paint whiteOval = new Paint();
    static {
        paintBorder.setStyle(Paint.Style.STROKE);
        paintBorder.setStrokeWidth(2);
        paintBorder.setColor(Color.GRAY);
        paintBorder.setShadowLayer(2, 2, 2, Color.GRAY);

        interiorOval = new Paint();
        interiorOval.setStyle(Paint.Style.FILL);
        interiorOval.setStrokeMiter(2);

        whiteOval = new Paint();
        whiteOval.setStyle(Paint.Style.FILL);
        whiteOval.setStrokeMiter(2);
        whiteOval.setColor(Color.WHITE);
    }

    public void drawStation(DrawParamStation drawParamStation){
        interiorOval.setColor(drawParamStation.getColor());
        paintBorder.setColor(drawParamStation.getColor());
        canvas.drawCircle(drawParamStation.getX(), drawParamStation.getY(), drawParamStation.getRadiusBorder(), whiteOval);
        canvas.drawCircle(drawParamStation.getX(), drawParamStation.getY(), drawParamStation.getRadiusBorder(), paintBorder);
        canvas.drawCircle(drawParamStation.getX(), drawParamStation.getY(), drawParamStation.getRadius(), interiorOval);
    }

}
