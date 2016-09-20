package com.gabik.metro.model;

import com.gabik.metro.model.elements.Station;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by GaBiK on 12.08.2016.
 */
public interface StationSelectable {
    public void onSelectStation(TypeSelectStation typeSelectStation, Station station);
}
