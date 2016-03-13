package com.gabik.metro.model.db.elementsFromDB;

import android.database.Cursor;

/**
 * Created by GaBiK on 24.02.2016.
 */
public class CommunicationParam implements ReadableFromDB {
    public ElementInt idFirst = new ElementInt("id_first_station");
    public ElementInt idSecond = new ElementInt("id_second_station");
    public ElementInt time = new ElementInt("time");
    public ElementString bendPointX = new ElementString("bend_point_x");
    public ElementString bendPointY = new ElementString("bend_point_y");
    @Override
    public void readElement(Cursor c) {
        idFirst.setValue(c);
        idSecond.setValue(c);
        time.setValue(c);
        bendPointX.setValue(c);
        bendPointY.setValue(c);
    }
}
