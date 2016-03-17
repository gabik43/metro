package com.gabik.metro.view.drawElements;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import com.gabik.metro.model.param.DrawParamBendCommunication;
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
        paint.setStrokeWidth(drawParamCommunication.size);
        paint.setColor(drawParamCommunication.color);
        canvas.drawLine(drawParamCommunication.pointOne.x, drawParamCommunication.pointOne.y,
                drawParamCommunication.pointTwo.x, drawParamCommunication.pointTwo.y, paint);
    }

    public void drawArc(DrawParamRingCommunication drawParamCommunication){
        paint.setStrokeWidth(drawParamCommunication.size);
        paint.setColor(drawParamCommunication.color);
        canvas.drawArc(drawParamCommunication.rectangleCircle,
                drawParamCommunication.startAngel,
                drawParamCommunication.endAngel, false, paint);
    }

    public void drawLineBend(DrawParamBendCommunication drawParamBendCommunication) {
        paint.setStrokeWidth(drawParamBendCommunication.size);
        paint.setColor(drawParamBendCommunication.color);
        Path path = new Path();
        path.moveTo(drawParamBendCommunication.pointOne.x, drawParamBendCommunication.pointOne.y);
        for (int i = 0; i < drawParamBendCommunication.getCountBendPoints(); i++){
            path.lineTo(drawParamBendCommunication.getBendX(i), drawParamBendCommunication.getBendY(i));
        }
        path.lineTo(drawParamBendCommunication.pointTwo.x, drawParamBendCommunication.pointTwo.y);
        canvas.drawPath(path,paint);
    }
}
