package com.gabik.metro.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import com.gabik.metro.controller.Placeable;
import com.gabik.metro.controller.touch.Point;
import com.gabik.metro.model.Model;

/**
 * Created by GaBiK on 08.09.2016.
 */
public class Scene extends View implements Placeable {

    private DrawingMaster drawingMaster;
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawingMaster.Draw(canvas);
    }

    public void initDrawingMaster(Model model){
        drawingMaster = new DrawingMaster(model);
    }

    public Scene(Context context) {
        super(context);
    }

    public Scene(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Scene(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Scene(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    /*private void updatePosition(){
        float newPositionX = placeable.getLeftTopPointScene().x;
        float newPositionY = placeable.getLeftTopPointScene().y;
        // Ограничение верхнего левого угла
        if (placeable.getLeftTopPointScreen().x <=
                placeable.getCurrentLeftTopPointWitchScaling().x){
            newPositionX = placeable.getLeftTopPointScene().x -
                    (Math.abs(placeable.getLeftTopPointScreen().x - placeable.getCurrentLeftTopPointWitchScaling().x));
        }
        if (placeable.getLeftTopPointScreen().y <= placeable.getCurrentLeftTopPointWitchScaling().y){
            newPositionY = placeable.getLeftTopPointScene().y -
                    Math.abs(placeable.getLeftTopPointScreen().y - placeable.getCurrentLeftTopPointWitchScaling().y);
        }


        // Ограничение нижнего правого угла
        if (placeable.getRightBottomPointScreen().x >= placeable.getCurrentRightBottomPointWitchScaling().x){
            newPositionX = placeable.getLeftTopPointScene().x +
                    (Math.abs(placeable.getLeftTopPointScreen().x - placeable.getCurrentLeftTopPointWitchScaling().x));
        }
        if (placeable.getRightBottomPointScreen().y >= placeable.getCurrentRightBottomPointWitchScaling().y){
            newPositionY = placeable.getLeftTopPointScene().y +
                    Math.abs(placeable.getLeftTopPointScreen().y - placeable.getCurrentLeftTopPointWitchScaling().y);
        }
        placeable.updatePosition(new Point(newPositionX, newPositionY));
    }*/

    @Override
    public Point getLeftTopPointScreen() {
        return new Point(this.getX(), this.getY());
    }

    @Override
    public Point getRightBottomPointScreen() {
        return new Point(this.getX() + this.getWidth(), this.getY() + this.getHeight());
    }

    @Override
    public Point getCurrentLeftTopPointWitchScaling() {
        float realWidth = this.getWidth() * this.getScaleX();
        float realHeigth = this.getHeight() * this.getScaleY();

        float dX = (this.getWidth() - realWidth) / 2;
        float dY = (this.getHeight() - realHeigth) / 2;

        return new Point(this.getX() + dX, this.getY() + dY);
    }

/*    @Override
    public Point getLeftTopPointScene() {
        return new Point(this.getX(), this.getY());
    }*/


    @Override
    public Point getCurrentRightBottomPointWitchScaling() {
        float realWidth = this.getWidth() * this.getScaleX();
        float realHeigth = this.getHeight() * this.getScaleY();

        float dX = (this.getWidth() - realWidth) / 2;
        float dY = (this.getHeight() - realHeigth) / 2;
        return new Point(this.getX() + realWidth + dX,
                this.getY() + realHeigth + dY);
    }

    @Override
    public void setPivot(Point pivotPoint) {
        this.setPivotX(pivotPoint.x);
        this.setPivotY(pivotPoint.y);
    }

    @Override
    public void updatePosition(Point dPoint) {
        this.setTranslationX(this.getX() + dPoint.x);
        this.setTranslationY(this.getY() + dPoint.y);
    }

    @Override
    public void setScale(float scalePercent) {
        this.setScaleX(scalePercent);
        this.setScaleY(scalePercent);
    }


    @Override
    public float getScale() {
        return this.getScaleX();
    }

    @Override
    public Point getCorrectPointInvert(Point point) {
        this.getMatrix();

        float[] pointsConversion = new float[2];
        pointsConversion[0] = point.x;
        pointsConversion[1] =  point.y;

        Matrix matrix = new Matrix();
        this.getMatrix().invert(matrix);

        matrix.mapPoints(pointsConversion);

        return new Point(pointsConversion[0], pointsConversion[1]);
    }
}
