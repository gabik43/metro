package com.gabik.metro.model.db.elementsFromDB;

import android.database.Cursor;

/**
 * Created by GaBiK on 24.02.2016.
 */
public class ElementString extends ElementBase {
    public String value;

    public ElementString(String name) {
        super(name);
    }

    @Override
    protected void setValueFromCursor(Cursor c) {
        value = c.getString(c.getColumnIndex(name));
    }
}
