package com.gabik.metro.model.db;

import com.gabik.metro.model.db.elementsFromDB.Paramable;

import java.util.List;

/**
 * Created by GaBiK on 18.02.2016.
 */
public interface DataBaseEmpl {
    public List<Paramable> getParam(Select select);
}
