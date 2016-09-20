package com.gabik.metro.core;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.view.View;
import android.widget.ListView;
import com.gabik.metro.controller.Controller;
import com.gabik.metro.R;


import java.util.logging.Logger;

/**
 * Created by GaBiK on 22.10.2015.
 */
public class MainActivity extends Activity {
    private static final Logger log = Logger.getLogger(MainActivity.class.getName());
    private static Controller controller;
    private ListView mDrawerList;
    private ActionBarDrawerToggle mDrawerToggle;
    DrawerLayout mDrawerLayout;
    public MainActivity(){
    }



    @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.main_activity);

        Controller controller = new Controller(this);
        //mDrawerList = (ListView) findViewById(R.id.left_drawer);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);

/*        mDrawerToggle = new ActionBarDrawerToggle(
                this,                  *//* host Activity *//*
                mDrawerLayout,         *//* DrawerLayout object *//*
                //R.drawable.ic_drawer,  *//* nav drawer icon to replace 'Up' caret *//*
                R.string.drawer_open,  *//* "open drawer" description *//*
                R.string.drawer_close  *//* "close drawer" description *//*
        ) {

            *//** Called when a drawer has settled in a completely closed state. *//*
            public void onDrawerClosed(View view) {
                super.onDrawerClosed(view);
                getActionBar().setTitle("тт");
            }

            *//** Called when a drawer has settled in a completely open state. *//*
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                getActionBar().setTitle("тт2");
            }
        };*/

    }

    public void onClick(View view) {
        System.out.print("Click");
    }
}
