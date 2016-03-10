package com.gabik.metro.model.param;

import android.graphics.Point;
import android.graphics.RectF;
import com.gabik.metro.view.drawElements.DrawCommunication;
import com.gabik.metro.view.drawElements.DrawHandler;

/**
 * Created by GaBiK on 28.02.2016.
 * Расширение для кольцевых параметров
 */
public class DrawParamRingCommunication extends DrawParamCommunication {
    public int startAngel;
    public int endAngel;

    public RectF rectangleCircle;

    public DrawParamRingCommunication(Point pointOne, Point pointTwo, Point pointCenter, float radius, int color) {
        super(pointOne, pointTwo, color);
        calculateAngelAndRectangle(pointOne, pointTwo, pointCenter, radius);
    }

    /*Метод расчета начального и конечного угла арки и прямоугольника для отображения арки*/
    private void calculateAngelAndRectangle(Point pointOne, Point pointTwo, Point pointCenter, float radius) {
        startAngel = (int) (180/Math.PI*Math.atan2(pointOne.y - pointCenter.y,
                pointOne.x - pointCenter.x));

        int advanceEndAngle = (int) (180/Math.PI*Math.atan2(pointTwo.y - pointCenter.y,
                pointTwo.x - pointCenter.x));
        advanceEndAngle = Math.abs(startAngel - advanceEndAngle);

        // Проверяем в какую сторону меньше градусов - в ту сторону и выбираем направление.
        endAngel = advanceEndAngle > 360 - advanceEndAngle ? 360 - advanceEndAngle:advanceEndAngle;

        rectangleCircle = new RectF(pointCenter.x- radius,
                pointCenter.y - radius, pointCenter.x + radius, pointCenter.y + radius);
    }

    @Override
    public void draw(DrawHandler drawHandler) {
        ((DrawCommunication)drawHandler).drawArc(this);
    }
}
