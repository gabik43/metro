package com.gabik.metro.model.db.elementsFromDB;

import android.database.Cursor;

/**
 * Created by GaBiK on 26.02.2016.
 */
public class BranchParam implements Paramable {
    public ElementInt id = new ElementInt("id");
    public ElementString name = new ElementString("name");
    public ElementString color = new ElementString("color");
    public ElementInt type = new ElementInt("type");
    public ElementInt roundingPointX = new ElementInt("rounding_point_x");
    public ElementInt roundingPointY = new ElementInt("rounding_point_y");
    public ElementInt radius = new ElementInt("radius");
    @Override
    public void readElement(Cursor c) {
        id.setValue(c);
        name.setValue(c);
        color.setValue(c);
        type.setValue(c);
        roundingPointX.setValue(c);
        roundingPointY.setValue(c);
        radius.setValue(c);
    }
}
