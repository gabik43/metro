package com.gabik.metro.view;

import android.app.Activity;
import android.graphics.Matrix;
import android.widget.*;
import com.gabik.metro.StationSelectButton;
import com.gabik.metro.R;
import com.gabik.metro.controller.Placeable;
import com.gabik.metro.controller.touch.Point;
import com.gabik.metro.model.Model;
import com.gabik.metro.model.TypeSelectStation;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by GaBiK on 12.08.2016.
 */
public class View implements Observer {
    Scene scene;
    LinearLayout screen;
    SelectStationHandler selectStationHandler;
    PathInfoHandler pathInfoHandler;

    StationSelectButton stationSelectStart;
    StationSelectButton stationSelectFinish;

    public PathInfoHandler getPathInfoHandler() {
        return pathInfoHandler;
    }

    public StationSelectButton getStationSelectStart() {
        return stationSelectStart;
    }

    public StationSelectButton getStationSelectFinish() {
        return stationSelectFinish;
    }

    public Button getButtonStart() {
        return selectStationHandler.getButtonStart();
    }

    public Button getButtonFinish() {
        return selectStationHandler.getButtonFinish();
    }

    public Scene getScene() {
        return scene;
    }

    private Model model;

    public void setModel(Model model){
        this.model = model;
        scene.initDrawingMaster(model);
    }
    public View(Activity context){

        scene = (Scene)context.findViewById(R.id.mySurfaceView);
        screen = (LinearLayout)context.findViewById(R.id.scene);


        FrameLayout layoutMap = (FrameLayout)context.findViewById(R.id.frameLayout);

        pathInfoHandler = new PathInfoHandler(context, layoutMap);
        selectStationHandler = new SelectStationHandler(context, layoutMap);

        stationSelectStart = (StationSelectButton)context.findViewById(R.id.stationSelectButtonFirst);
        stationSelectStart.setTypeSelectStation(TypeSelectStation.START);

        stationSelectFinish = (StationSelectButton)context.findViewById(R.id.stationSelectButtonSecond);
        stationSelectFinish.setTypeSelectStation(TypeSelectStation.FINISH);

    }

    public void viewSelectStation(String nameStation, float x, float y){
        selectStationHandler.viewSelectStation(nameStation, x, y);
    }


    public void hideSelectStation(){
        selectStationHandler.hideSelectStation();
    }

    @Override
    public void update(Observable observable, Object data) {
        scene.invalidate();
    }

}

