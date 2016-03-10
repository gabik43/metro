package com.gabik.metro.view.drawElements;

import android.graphics.Canvas;
import android.graphics.Paint;
import com.gabik.metro.model.param.DrawParamCommunication;
import com.gabik.metro.model.param.DrawParamRingCommunication;

/**
 * Created by GaBiK on 28.02.2016.
 */
public class DrawCommunication extends DrawHandler {
    private static Paint paint = new Paint();

    static {
        paint.setStrokeWidth(10);
        paint.setStyle(Paint.Style.STROKE);

    }

    public void drawLine(DrawParamCommunication drawParamCommunication){
        paint.setColor(drawParamCommunication.color);
        canvas.drawLine(drawParamCommunication.pointOne.x, drawParamCommunication.pointOne.y,
                drawParamCommunication.pointTwo.x, drawParamCommunication.pointTwo.y, paint);
    }

    public void drawArc(DrawParamRingCommunication drawParamCommunication){
        paint.setColor(drawParamCommunication.color);
        canvas.drawArc(drawParamCommunication.rectangleCircle,
                drawParamCommunication.startAngel,
                drawParamCommunication.endAngel, false, paint);
    }
    }
