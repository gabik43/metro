package com.gabik.metro.controller.touch;

import android.view.MotionEvent;

import java.util.Date;

/**
 * Created by GaBiK on 05.11.2015.
 */
public class TouchHandler {


    private final int DELAY_TOUCH = 200;


    private long touchDownTime;
    private boolean isDownTouch;
    public TypeTouch getTypeTouch(MotionEvent event){
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                downTouch();
                return TypeTouch.touch;

            case MotionEvent.ACTION_UP:
                if (isSelect())
                    return TypeTouch.select;
                break;

            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                if (pointerCount == 2) {
                    return TypeTouch.scale;
                }
                if (pointerCount == 1) {
                    return TypeTouch.move;
                }
                break;
        }
        return TypeTouch.none;
    }

    private void downTouch(){
        isDownTouch = true;
        Date date = new Date();
        touchDownTime = date.getTime();
    }

    private boolean isSelect(){
        Date date = new Date();
        if (Math.abs(date.getTime() - touchDownTime) < DELAY_TOUCH){
            return true;
        }
        return false;
    }
}
