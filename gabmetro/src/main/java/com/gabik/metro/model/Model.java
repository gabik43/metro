package com.gabik.metro.model;

import android.content.Context;
import com.gabik.metro.controller.touch.Selectable;
import com.gabik.metro.model.db.*;
import com.gabik.metro.model.db.elementsFromDB.BranchParam;
import com.gabik.metro.model.db.elementsFromDB.CommunicationParam;
import com.gabik.metro.model.db.elementsFromDB.StationParam;
import com.gabik.metro.model.elements.Branch;
import com.gabik.metro.model.elements.Communication;
import com.gabik.metro.model.elements.NameStation;
import com.gabik.metro.model.elements.Station;
import com.gabik.metro.model.param.Paramable;

import java.util.List;
import java.util.Observable;

/**
 * Created by GaBiK on 04.11.2015.
 */
public class Model extends Observable {

    private Map map;
    private Context context;

    public List<Selectable> getSelectableList(){
        return map.getSelectableList();
    }
    public List<Paramable> getDrawableStation(){
        return map.getDrawableStation();
    }
    public List<Paramable> getDrawCommunication(){
        return map.getDrawCommunication();
    }
    public List<Paramable> getDrawNames(){
        return map.getDrawableNames();
    }

    public Model(Context context){
        this.context = context;
        DataBaseHelper dataBaseHelper = new DataBaseHelper(context);
        map = new Map();

        List<com.gabik.metro.model.db.elementsFromDB.Paramable> paramFromDBLInes = dataBaseHelper.getParam(Select.Branch);
        for (com.gabik.metro.model.db.elementsFromDB.Paramable param : paramFromDBLInes) {
            BranchParam branchParam = (BranchParam) param;
            map.addLines(branchParam.id.value, new Branch(branchParam.name.value, branchParam.type.value,
                    branchParam.color.value,
                    branchParam.roundingPointX.value, branchParam.roundingPointY.value, branchParam.radius.value));
        }

        try {
            List<com.gabik.metro.model.db.elementsFromDB.Paramable> paramFromDBStations = dataBaseHelper.getParam(Select.Station);
            for (com.gabik.metro.model.db.elementsFromDB.Paramable param : paramFromDBStations) {
                StationParam stationParam = (StationParam) param;
                Branch branch = map.getLine(stationParam.idLines.value);
                map.addName(new NameStation(stationParam.name.value, stationParam.x.value, stationParam.y.value,
                        stationParam.offsetXName.value, stationParam.offsetYName.value));
                map.addStation(stationParam.id.value, new Station(branch, map.getName(stationParam.name.value),
                        stationParam.x.value, stationParam.y.value));
            }
        } catch (Exception ex){
            ex.printStackTrace();
        }

        List<com.gabik.metro.model.db.elementsFromDB.Paramable> communications = dataBaseHelper.getParam(Select.Communication);
        for (com.gabik.metro.model.db.elementsFromDB.Paramable param : communications){
            CommunicationParam lineParam = (CommunicationParam)param;
            Station StationOne = map.getStation(lineParam.idFirst.value);
            Station StationTwo = map.getStation(lineParam.idSecond.value);
            map.addCommunication(new IdCommunication(lineParam.idFirst.value,lineParam.idSecond.value),
                    new Communication(StationOne, StationTwo, lineParam.time.value));
        }
    }


    public void update(){
        setChanged();
        notifyObservers();
    }
}
