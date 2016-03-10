package com.gabik.metro.model.db.elementsFromDB;

import android.database.Cursor;

/**
 * Created by GaBiK on 23.02.2016.
 */
public interface Paramable {
    void readElement(Cursor c);
}
