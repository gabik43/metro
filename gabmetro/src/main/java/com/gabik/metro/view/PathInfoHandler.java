package com.gabik.metro.view;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gabik.metro.R;
import com.gabik.metro.model.PathSelectable;

import java.util.Calendar;

/**
 * Created by GaBiK on 04.09.2016.
 */
public class PathInfoHandler implements PathSelectable {

    private LinearLayout rootLayout;
    private TextView textView;

    public PathInfoHandler(Activity activity, FrameLayout layoutMap){

        LayoutInflater layoutInflater = activity.getLayoutInflater();
        android.view.View view1 = layoutInflater.inflate(R.layout.path_info, layoutMap, false);
        rootLayout = (LinearLayout) view1;

        layoutMap.addView(rootLayout);


        textView = (TextView) activity.findViewById(R.id.pathInfoText);
    }

    @Override
    public void onSelectPath(int time) {
        textView.setText(getText(time));

    }

    public String getText(int time){
        return "Время в пути: " + String.valueOf(time / 60);
    }
}
