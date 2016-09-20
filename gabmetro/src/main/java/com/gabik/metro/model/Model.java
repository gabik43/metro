package com.gabik.metro.model;

import android.content.Context;
import com.gabik.metro.controller.touch.Selectable;
import com.gabik.metro.grahp.Graph;
import com.gabik.metro.model.db.*;
import com.gabik.metro.model.db.elementsFromDB.BranchParam;
import com.gabik.metro.model.db.elementsFromDB.CommunicationParam;
import com.gabik.metro.model.db.elementsFromDB.ReadableFromDB;
import com.gabik.metro.model.db.elementsFromDB.StationParam;
import com.gabik.metro.model.elements.Branch;
import com.gabik.metro.model.elements.Communication;
import com.gabik.metro.model.elements.NameStation;
import com.gabik.metro.model.elements.Station;
import com.gabik.metro.model.param.ParamsDerivable;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.logging.Logger;

/**
 * Created by GaBiK on 04.11.2015.
 */
public class Model extends Observable {
    private static final Logger log = Logger.getLogger(Model.class.getName());

    private Map map;
    private Graph graph;
    private Context context;
    private DataBaseHelper dataBaseHelper;
    private List<StationSelectable> stationSelectables = new ArrayList<StationSelectable>();
    private List<PathSelectable> pathSelectableList = new ArrayList<PathSelectable>();

    private Station activeStation;

    public void addStationSelectableListener(StationSelectable stationSelectable){
        stationSelectables.add(stationSelectable);
    }

    public void addPathSelectableListener(PathSelectable pathSelectable){
        pathSelectableList.add(pathSelectable);
    }

    private void onSelectStation(TypeSelectStation typeSelectStation, Station station){
        if (stationSelectables == null) return;
        for (StationSelectable stationSelectable : stationSelectables){
            stationSelectable.onSelectStation(typeSelectStation, station);
        }
    }

    private void onPathSelect(int time){
        if (pathSelectableList == null) return;
        for (PathSelectable pathSelectable : pathSelectableList){
            pathSelectable.onSelectPath(time);
        }
    }

    private CurrentActiveElements currentActiveElements;
    public CurrentActiveElements getCurrentActiveElements() {
        return currentActiveElements;
    }

    private Station selectStationOne, selectStationTwo;

    public List<Selectable> getSelectableList(){
        return map.getSelectableList();
    }
    public List<ParamsDerivable> getDrawableStation(){
        return map.getDrawableStation();
    }
    public List<ParamsDerivable> getDrawCommunication(){
        return map.getDrawCommunication();
    }
    public List<ParamsDerivable> getDrawNames(){
        return map.getDrawableNames();
    }

    public Model(Context context){
        initialFieldAndSetContext(context);
        initialBranches();
        initialStations();
        initialCommunications();
        graph = new Graph(map.getDistance(), 500);
    }

    private void initialFieldAndSetContext(Context context) {
        if (context == null) throw new IllegalArgumentException("Context не может быть null");
        this.context = context;
        dataBaseHelper = new DataBaseHelper(context);
        map = new Map();
    }

    private void initialBranches() {
        log.fine("Начало инициализации веток метро");
        try {
            List<ReadableFromDB> paramFromDBLInes = dataBaseHelper.getParam(Select.Branch);
            for (ReadableFromDB param : paramFromDBLInes) {
                BranchParam branchParam = (BranchParam) param;
                map.addLines(branchParam.id.value, new Branch(branchParam.name.value, branchParam.type.value,
                        branchParam.color.value,
                        branchParam.roundingPointX.value, branchParam.roundingPointY.value, branchParam.radius.value));
                log.fine("Ветки метро успешно инициализированны");
            }
        } catch (Exception e) {
            log.severe("Произошла ошибка при попытке инициализации веток: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initialStations() {
        try {
            log.fine("Начало инициализации станций метро");
            List<ReadableFromDB> paramFromDBStations = dataBaseHelper.getParam(Select.Station);
            for (ReadableFromDB param : paramFromDBStations) {
                StationParam stationParam = (StationParam) param;
                Branch branch = map.getLine(stationParam.idLines.value);
                map.addName(new NameStation(stationParam.name.value, stationParam.x.value, stationParam.y.value,
                        stationParam.offsetXName.value, stationParam.offsetYName.value));
                map.addStation(stationParam.id.value, new Station(stationParam.id.value, branch, map.getName(stationParam.name.value),
                        stationParam.x.value, stationParam.y.value));
                log.fine("Станции метро успешно инициализированны");
            }
        } catch (Exception e) {
            log.severe("Произошла ошибка при попытке инициализации станций: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private void initialCommunications() {
        try {
            log.fine("Начало инициализации связей станций");
            List<ReadableFromDB> communications = dataBaseHelper.getParam(Select.Communication);
            for (ReadableFromDB param : communications){
                CommunicationParam lineParam = (CommunicationParam)param;
                Station StationOne = map.getStation(lineParam.idFirst.value);
                Station StationTwo = map.getStation(lineParam.idSecond.value);
                IdCommunication idCommunication = new IdCommunication(lineParam.idFirst.value,lineParam.idSecond.value);
                log.info(idCommunication.toString());
                Communication communication = new Communication(StationOne, StationTwo, lineParam.time.value,
                        lineParam.bendPointX.value,lineParam.bendPointY.value);

                map.addCommunication(idCommunication, communication);
                log.fine("Связи станций успешно инициализированны");
            }
        } catch (Exception e) {
            log.severe("Произошла ошибка при попытке инициализации связей станции: " + e.getMessage());
            e.printStackTrace();
        }
    }

    public List<Integer> listActive;

    public void activeStation(Station station){
        activeStation = station;
    }

    public void update(){
        setChanged();
        notifyObservers();
    }

    private void selectPath(){
        if (selectStationOne != null && selectStationTwo != null){
            if (listActive != null){
                for (int i = 0; i < listActive.size(); i++){
                    if (i+1 < listActive.size()) {
                        IdCommunication idCommunication = new IdCommunication(listActive.get(i), listActive.get(i + 1));
                        map.getCommunication(idCommunication).deselect();
                    }
                    map.getStation(listActive.get(i)).deselect();
                }
            }

            listActive = graph.calculate(selectStationOne.getId(),selectStationTwo.getId());
            onPathSelect(graph.getDistance());
            for (int i = 0; i < listActive.size(); i++){
                if (i+1 < listActive.size()) {
                    IdCommunication idCommunication = new IdCommunication(listActive.get(i), listActive.get(i + 1));
                    map.getCommunication(idCommunication).select();
                }
                map.getStation(listActive.get(i)).select();
            }

        }
    }


    public void activeStation(TypeSelectStation typeSelectStation){
        if (typeSelectStation == TypeSelectStation.START){
            selectStationTwo = activeStation;
        } else {
            selectStationOne = activeStation;
        }

        activeStation.select();
        onSelectStation(typeSelectStation, activeStation);

        selectPath();
        update();
    }

}

