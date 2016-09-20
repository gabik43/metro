package com.gabik.metro.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Matrix;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.util.AttributeSet;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import com.gabik.metro.controller.touch.Point;
import com.gabik.metro.model.Model;

import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Created by GaBiK on 28.10.2015.
 */
public class MySurfaceView extends SurfaceView implements SurfaceHolder.Callback {
    private static final Logger log = Logger.getLogger(MySurfaceView.class.getName());

    private DrawThread drawThread;
    private Model model;
    public void setModel(Model model){
        this.model = model
;    }

    public DrawThread getDrawThread() {
        return drawThread;
    }


    public MySurfaceView(Context context) {
        super(context);
        getHolder().addCallback(this);
    }

    public MySurfaceView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
    }

    public void resetMatrix(float dScale, float dX, float dY){
        drawThread.resetMatrix(dScale, dX, dY);
        drawThread.redraw();
    }

    public Point getConvertPoint(float x, float y){
        float[] pointsConversion = new float[2];
        pointsConversion[0] = x;
        pointsConversion[1] = y;
        drawThread.getInvertMatrix().mapPoints(pointsConversion);
        return new Point(pointsConversion[0], pointsConversion[1]);
    }


    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        log.info("Surface is created");
        drawThread = new DrawThread(getHolder(), model);
        drawThread.runDraw();
        drawThread.start();
        model.update();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        log.info("Surface is changed");
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        log.info("Surface is destroyed");
        boolean retry = true;
        drawThread.stopDraw();
        while (retry) {
            try {
                drawThread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
    }

    public void redraw() {
        drawThread.redraw();
    }
}
