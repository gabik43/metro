package com.gabik.metro.model.db.elementsFromDB;

import android.database.Cursor;

/**
 * Created by GaBiK on 24.02.2016.
 */
public class ElementFloat extends ElementBase {
    public float value;

    public ElementFloat(String name) {
        super(name);
    }

    @Override
    protected void setValueFromCursor(Cursor c) {
        value = c.getFloat(c.getColumnIndex(name));
    }
}
