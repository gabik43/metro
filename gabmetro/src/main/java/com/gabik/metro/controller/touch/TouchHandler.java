package com.gabik.metro.controller.touch;

import android.view.MotionEvent;

import java.util.Date;

/**
 * Created by GaBiK on 05.11.2015.
 */
public class TouchHandler {


    private final int DELAY_SELECT = 250;

    private long touchDownTime;
    private boolean startMove;
    private boolean startScale;



    public TypeTouch getTypeTouch(MotionEvent event){
        switch (event.getAction()){

            case MotionEvent.ACTION_DOWN:
                startMove = true;
                startScale = true;
                saveTimeTouch();
                return TypeTouch.none;

            case MotionEvent.ACTION_UP:
                if (isTimeTouchMoreDelay()) {
                    startMove = false;
                    startScale = false;
                    return TypeTouch.select;
                }
                break;

            case MotionEvent.ACTION_MOVE:
                int pointerCount = event.getPointerCount();
                if (pointerCount == 2) {
                    if (startScale) {
                        startMove = true;
                        startScale = false;
                        return TypeTouch.start_scale;
                    }
                    return TypeTouch.scale;
                }
                if (pointerCount == 1) {
                    if (startMove){
                        startMove = false;
                        startScale = true;
                        return TypeTouch.start_move;
                    }
                    return TypeTouch.move;
                }
                break;
        }
        return TypeTouch.none;
    }

    private void saveTimeTouch(){
        touchDownTime = new Date().getTime();
    }

    private boolean isTimeTouchMoreDelay(){
        if (Math.abs(new Date().getTime() - touchDownTime) < DELAY_SELECT){
            return true;
        }
        return false;
    }
}
