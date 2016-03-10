package com.gabik.metro.model.db.elementsFromDB;

import android.database.Cursor;

/**
 * Created by GaBiK on 23.02.2016.
 */
public class ElementInt extends ElementBase {
    public int value;

    public ElementInt(String name) {
        super(name);
    }

    @Override
    protected void setValueFromCursor(Cursor c) {
        value = c.getInt(c.getColumnIndex(name));
    }
}

