package com.gabik.metro.core;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import com.gabik.metro.controller.Controller;
import com.gabik.metro.model.db.DataBaseHelper;
import com.gabik.metro.view.MySurfaceView;
import com.gabik.metro.R;


import java.io.IOException;
import java.util.logging.Logger;

/**
 * Created by GaBiK on 22.10.2015.
 */
public class MainActivity extends Activity {
    private static final Logger log = Logger.getLogger(MainActivity.class.getName());
    private static Controller controller;

    public MainActivity(){
    }



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_activity);
        MySurfaceView surfaceView = (MySurfaceView)findViewById(R.id.mySurfaceView);
        Controller controller = new Controller(surfaceView, this);

    }
}
