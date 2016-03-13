package com.gabik.metro.model.db.elementsFromDB;

import android.database.Cursor;

/**
 * Created by GaBiK on 23.02.2016.
 */
public class StationParam implements ReadableFromDB {
    public ElementInt id = new ElementInt("id");
    public ElementString name = new ElementString("name");
    public ElementInt x = new ElementInt("positionX");
    public ElementInt y = new ElementInt("positionY");
    public ElementInt idLines = new ElementInt("id_lines");
    public ElementInt offsetXName = new ElementInt("offsetX");
    public ElementInt offsetYName = new ElementInt("offsetY");

    @Override
    public void readElement(Cursor c) {
        id.setValue(c);
        name.setValue(c);
        x.setValue(c);
        y.setValue(c);
        idLines.setValue(c);
        offsetXName.setValue(c);
        offsetYName.setValue(c);
    }
}
