package com.gabik.metro.model.db;

/**
 * Created by GaBiK on 23.02.2016.
 */
public enum Select {
    Station("SELECT * FROM Stations, NamePosition WHERE Stations.id_namePosition = NamePosition.id_position"),
    Communication("SELECT * FROM CommunicationStations"),
    Branch("SELECT * FROM Branch");
    private final String name;
    Select(String name) {
        this.name = name;
    }
    public boolean equalsName(String otherName) {
        return (otherName == null) ? false : name.equals(otherName);
    }

    public String toString() {
        return this.name;
    }

}
