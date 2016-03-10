package com.gabik.metro.view;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.view.SurfaceHolder;
import com.gabik.metro.model.Model;
import java.util.logging.Logger;

/**
 * Created by GaBiK on 28.10.2015.
 */
public class DrawThread extends Thread {
    private static final Logger log = Logger.getLogger(DrawThread.class.getName());

    private SurfaceHolder surfaceHolder;
    private DrawingMaster drawingMaster;
    private Matrix matrix;
    private Matrix invertMatrix;
    private float x,y,scale;
    private boolean isRun;
    private boolean isRedraw;

    public DrawThread(SurfaceHolder surfaceHolder, Model model){
        this.surfaceHolder = surfaceHolder;
        drawingMaster = new DrawingMaster(model);
        matrix = new Matrix();
        invertMatrix = new Matrix();
        scale = 1;
        x = 0;
        y = 0;
    }

    public void resetMatrix(float dScale, float dX, float dY){
        scale += dScale; x += dX; y += dY;
        matrix.setScale(scale, scale);
        matrix.postTranslate(x, y);
        matrix.invert(invertMatrix);
    }

    public void runDraw() {
        isRun = true;
    }

    public void stopDraw() {
        isRun = false;
    }

    public void redraw(){
        isRedraw = true;
    }
    @Override
    public void run() {
        Canvas canvas;
        while (isRun) {
            if (isRedraw) {
                isRedraw = false;
                canvas = null;
                try {
                    canvas = surfaceHolder.lockCanvas(null);
                    synchronized (surfaceHolder) {
                        canvas.drawColor(Color.WHITE);
                        canvas.setMatrix(matrix);
                        drawingMaster.Draw(canvas);
                    }
                } catch (Exception ex) {
                    log.finer(ex.getMessage());
                } finally {
                    if (canvas != null) {
                        surfaceHolder.unlockCanvasAndPost(canvas);
                    }
                }

            }
        }
    }

    public Matrix getInvertMatrix() {
        return invertMatrix;
    }
}
