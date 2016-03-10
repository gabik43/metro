package com.gabik.metro.model;

import com.gabik.metro.controller.touch.Selectable;
import com.gabik.metro.model.elements.Communication;
import com.gabik.metro.model.elements.Branch;
import com.gabik.metro.model.elements.NameStation;
import com.gabik.metro.model.elements.Station;
import com.gabik.metro.model.param.Paramable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by GaBiK on 22.02.2016.
 */
public class Map {

    private HashMap<Integer, Station> mapStation;
    private HashMap<Integer, Branch> mapLines;
    private HashMap<String, NameStation> mapNames;
    private HashMap<IdCommunication, Communication> mapCommunication;

    private List<Selectable> selectableList;
    public List<Selectable> getSelectableList() {
        return selectableList;
    }

    private List<Paramable> drawStation;

    public List<Paramable> getDrawCommunication() {
        return drawCommunication;
    }

    private List<Paramable> drawCommunication;
    public List<Paramable> getDrawableStation() {
        return drawStation;
    }

    private List<Paramable> drawNames;
    public List<Paramable> getDrawableNames() {
        return drawNames;
    }

    public Map(){
        mapStation = new HashMap<Integer, Station>();
        mapLines = new HashMap<Integer, Branch>();
        mapNames = new HashMap<String, NameStation>();
        mapCommunication = new HashMap<IdCommunication, Communication>();
        selectableList = new ArrayList<Selectable>();
        drawStation = new ArrayList<Paramable>();
        drawCommunication = new ArrayList<Paramable>();
        drawNames= new ArrayList<Paramable>();
    }


    public void addStation(int idStation, Station station){
        if (idStation < 0) throw new IllegalArgumentException("Станция должна быть с id >= 0. Попытка добавить: id: " + idStation);
        if (mapStation.containsKey(idStation)) throw new IllegalArgumentException("Станция с id: " + idStation + " уже " +
                "была добавлена");
        mapStation.put(idStation, station);
        selectableList.add(station);
        drawStation.add(station);
    }
    public void addName(NameStation nameStation){
        if (mapNames.containsKey(nameStation.getName())) return;
        mapNames.put(nameStation.getName(), nameStation);
        drawNames.add(nameStation);
    }
    public NameStation getName(String nameStation){
        NameStation name = mapNames.get(nameStation);
        if (name == null) throw new NullPointerException("Станции с именем " + nameStation + " нет в наборе имен");
        return mapNames.get(nameStation);
    }

    public void addLines(int idLines, Branch branch){
        if (idLines < 0) throw new IllegalArgumentException("Станция должна быть с id >= 0. Попытка добавить: id: " + idLines);
        if (mapLines.containsKey(idLines)) throw new IllegalArgumentException("Станция с id: " + idLines + " уже " +
                "была добавлена");
        mapLines.put(idLines, branch);
    }

    public void addCommunication(IdCommunication idCommunication, Communication communication){
        if (mapCommunication.containsKey(idCommunication)) throw new IllegalArgumentException("Связь с id: " + idCommunication + " уже " +
                "была добавлена");
        mapCommunication.put(idCommunication, communication);
        drawCommunication.add(communication);
    }

    public Station getStation(int id) {
        return mapStation.get(id);
    }
    public Branch getLine(int id) {
        return mapLines.get(id);
    }
}
