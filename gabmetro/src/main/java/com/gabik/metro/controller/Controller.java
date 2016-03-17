package com.gabik.metro.controller;

import android.content.Context;
import android.view.MotionEvent;
import android.view.View;
import com.gabik.metro.controller.touch.*;
import com.gabik.metro.controller.touch.Point;
import com.gabik.metro.model.Model;
import com.gabik.metro.model.elements.Station;
import com.gabik.metro.view.MySurfaceView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Created by GaBiK on 04.11.2015.
 */
public class Controller implements Observer, View.OnTouchListener {
    private Model model;
    private MySurfaceView view;
    private TouchHandler touchHandler;
    private SelectHandler selectHandler;
    private MoveHandler moveHandler;
    private ScaleHandler scaleHandler;
    private TranslateHandler translateHandler;


    public Controller(MySurfaceView mySurfaceView, Context context){
        initModelAndView(mySurfaceView, context);
        initInternalField();
        registerViewAndControllerObserver();
    }



    private void initInternalField(){
        touchHandler = new TouchHandler();
        selectHandler = new SelectHandler();
        scaleHandler = new ScaleHandler();
        moveHandler = new MoveHandler();
        translateHandler = new TranslateHandler();
    }

    private void initModelAndView(MySurfaceView mySurfaceView, Context context){
        model = new Model(context);
        view = mySurfaceView;
        view.setModel(model);
        view.setOnTouchListener(this);
    }
    private void registerViewAndControllerObserver() {
        model.addObserver(view);
        model.addObserver(this);
    }

    @Override
    public void update(Observable observable, Object data) {

    }
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        TypeTouch typeTouch = touchHandler.getTypeTouch(event);
        switch (typeTouch){
            case touch:
                translateHandler.setStartPoint(event.getX(), event.getY());
                //model.update();
                break;
            case select:
                List<Selectable> baseElementList = model.getSelectableList();
                Point pointTouch = view.getConvertPoint(event.getX(), event.getY());
                Selectable selectElement = selectHandler.getSelectElement(baseElementList, pointTouch);
                if (selectElement != null) {
                    model.selectStation((Station) selectElement);
                }
                break;
            case move:
                Point point = translateHandler.getTranslate(event.getX(), event.getY());
                view.resetMatrix(0f,-point.x,-point.y);
                break;
            case scale:
                view.resetMatrix(scaleHandler.getScale(event.getX(0), event.getY(0), event.getX(1), event.getY(1)),0,0);
                break;
            default:
        }
        return true;
    }

}
