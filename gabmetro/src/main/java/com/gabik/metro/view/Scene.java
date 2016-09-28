package com.gabik.metro.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import com.gabik.metro.controller.Placeable;
import com.gabik.metro.controller.touch.Point;
import com.gabik.metro.model.Model;

/**
 * Created by GaBiK on 08.09.2016.
 */
public class Scene extends View implements Placeable {

    private DrawingMaster drawingMaster;
    private LinearLayout parent;
    private Rect rectScaling;
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
        updateRectScaling();
    }

    public Scene(Context context, AttributeSet attrs) {
        super(context, attrs);
        updateRectScaling();
    }

    public Scene(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        updateRectScaling();
    }


    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public Scene(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

    }

    public void setParent(LinearLayout parentLayout){
        parent = parentLayout;
    }

    @Override
    public void updatePosition(Point dPoint){
        float newPositionX = this.getX() + dPoint.x;
        float newPositionY = this.getY() + dPoint.y;
        // Ограничение верхнего левого угла
        if (rectScaling.left + dPoint.x >= parent.getX()){
            newPositionX = this.getX();
        }
        if (rectScaling.top + dPoint.y >= parent.getY()){
            newPositionY = this.getY();
        }
        if (rectScaling.right + dPoint.x <= parent.getX() + parent.getWidth()){
            newPositionX = this.getX();
        }
        if (rectScaling.bottom + dPoint.y <= parent.getY() + parent.getHeight()){
            newPositionY = this.getY();
        }

        updatePosition2(new Point(newPositionX, newPositionY));
    }


/*    @Override
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
    }*/

/*    @Override
    public Point getLeftTopPointScene() {
        return new Point(this.getX(), this.getY());
    }*/


/*    @Override
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
    }*/

    public void updatePosition2(Point dPoint) {
        this.setTranslationX(dPoint.x);
        this.setTranslationY(dPoint.y);
        updateRectScaling();
    }

    private void updateRectScaling() {
        float realWidth = this.getWidth() * this.getScaleX();
        float realHeigth = this.getHeight() * this.getScaleY();

        float dX = (this.getWidth() - realWidth) / 2;
        float dY = (this.getHeight() - realHeigth) / 2;
        rectScaling = new Rect((int)(this.getX() + dX), (int)(this.getY() + dY),
                (int)(this.getX() + realWidth + dX), (int)(this.getY() + realHeigth + dY));
    }

    @Override
    public void updateScale(Point touchOne, Point touchTwo) {
        float distance = calculateDistance(touchOne, touchTwo);
        float newScale = (float) initScale + getPercentDiffDistance(distance) * 0.01f;
        if (newScale < MAX_SCALE && newScale > MIN_SCALE) {
            placeable.setScale((float) initScale + getPercentDiffDistance(distance) * 0.01f);
        }
    }

    private float calculateDistance(Point begin, Point end){
        return (float)Math.sqrt(Math.pow(end.x-begin.x,2) + Math.pow(end.y-begin.y,2));
    }

    private float getPercentDiffDistance(float newDistance){
        if (newDistance > initDistance){
            return  (float)((newDistance - initDistance) / (newDistance / 100)) / SCALE_SPEED;
        } else {
            return -(float)((initDistance - newDistance) / (initDistance / 100))/ SCALE_SPEED;
        }
    }

    @Override
    public Point getRealPosition(Point position) {
        this.getMatrix();

        float[] pointsConversion = new float[2];
        pointsConversion[0] = position.x;
        pointsConversion[1] =  position.y;

        Matrix matrix = new Matrix();
        this.getMatrix().invert(matrix);

        matrix.mapPoints(pointsConversion);

        return new Point(pointsConversion[0], pointsConversion[1]);
    }

/*    @Override
    public void setScale(float scalePercent) {
        this.setScaleX(scalePercent);
        this.setScaleY(scalePercent);
    }


    @Override
    public float getScale() {
        return this.getScaleX();
    }*/

}
