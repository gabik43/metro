package com.gabik.metro.controller;

import android.app.Activity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import com.gabik.metro.R;
import com.gabik.metro.controller.touch.*;
import com.gabik.metro.controller.touch.Point;
import com.gabik.metro.model.Model;
import com.gabik.metro.model.TypeSelectStation;
import com.gabik.metro.model.elements.Station;
import com.gabik.metro.view.MySurfaceView;

import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;

/**
 * Created by GaBiK on 04.11.2015.
 */
public class Controller implements Observer, View.OnTouchListener {

    private Model model;
    private com.gabik.metro.view.View view;
    private TouchHandler touchHandler;
    private SelectHandler selectHandler;
    private Placeable placeable;
    //private MoveChanger moveChanger;
    //private ScaleChanger scaleChanger;


    public Controller(Activity context){
        initModelAndView(context);
        initInternalField();
        registerViewAndControllerObserver();
    }



    private void initInternalField(){
        touchHandler = new TouchHandler();
        selectHandler = new SelectHandler();
        placeable = view.getScene();
    }

    private void initModelAndView(Activity context){
        try {
            FrameLayout frameLayout = (FrameLayout)context.findViewById(R.id.content_frame);
            frameLayout.setOnTouchListener(this);

            view = new com.gabik.metro.view.View(context);
            model = new Model(context);
            view.setModel(model);


            view.getButtonStart().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.activeStation(TypeSelectStation.START);
                    view.hideSelectStation();
                }
            });

            view.getButtonFinish().setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    model.activeStation(TypeSelectStation.FINISH);
                    view.hideSelectStation();
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }


        model.addStationSelectableListener(view.getStationSelectStart());
        model.addPathSelectableListener(view.getPathInfoHandler());
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
        view.hideSelectStation();
        switch (typeTouch){
            case select:
                List<Selectable> baseElementList = model.getSelectableList();
                Point pointTouch = new Point(event.getX(), event.getY() );
                Point pointWitchScale = moveChanger.getCorrectedPoint(pointTouch);
                Selectable selectElement = selectHandler.getSelectElement(baseElementList, pointWitchScale);
                if (selectElement != null) {
                    Station selectStation = (Station) selectElement;
                    view.viewSelectStation(selectStation.getNameStation().getName(), event.getX(), event.getY());
                    model.activeStation(selectStation);
                }
                break;
            case start_move:
                moveChanger.setStartTouchPoint(event.getX(), event.getY());
                break;
            case  move:
                moveChanger.translate(event.getX(), event.getY());
                break;
            case start_scale:
                scaleChanger.initScale(new Point(event.getX(0), event.getY(0)),
                        new Point(event.getX(1), event.getY(1)));
                break;
            case scale:
                scaleChanger.scale(new Point(event.getX(0), event.getY(0)),
                        new Point(event.getX(1), event.getY(1)));
                break;
            default:
        }


        return true;
    }

}
