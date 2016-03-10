package com.gabik.metro.model.db.elementsFromDB;

import android.database.Cursor;

import java.util.logging.Logger;

/**
 * Created by GaBiK on 24.02.2016.
 */
public abstract class ElementBase {
    private static final Logger log = Logger.getLogger(ElementBase.class.getName());
    public ElementBase(String name) {
        this.name = name;
    }

    public String name;
    public void setValue(Cursor c){
        try {
            setValueFromCursor(c);
        } catch (Exception ex){
            log.severe("Ошибка получения объекта " + name + " из курсора с набором полей: " + c.getColumnNames());
            ex.printStackTrace();
        }
    }
    protected abstract void setValueFromCursor(Cursor c);
}
