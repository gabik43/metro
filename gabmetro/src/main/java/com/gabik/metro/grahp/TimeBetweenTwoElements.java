package com.gabik.metro.grahp;

/**
 * Created by GaBiK on 13.03.2016.
 */
public class TimeBetweenTwoElements {

    private int indexStationOne;
    public int getIndexStationOne() {
        return indexStationOne;
    }

    private int indexStationTwo;
    public int getIndexStationTwo() {
        return indexStationTwo;
    }

    private int distance;
    public int getTime() {
        return distance;
    }

    public TimeBetweenTwoElements(int indexStationOne, int indexStationTwo, int distance) {
        this.indexStationOne = indexStationOne;
        this.indexStationTwo = indexStationTwo;
        this.distance = distance;
    }
}
