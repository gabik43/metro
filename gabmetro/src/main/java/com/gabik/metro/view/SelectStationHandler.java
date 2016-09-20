package com.gabik.metro.view;

import android.app.Activity;
import android.graphics.Rect;
import android.util.Log;
import android.view.*;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.gabik.metro.R;
import com.gabik.metro.StationSelectButton;
import com.gabik.metro.model.elements.Station;

/**
 * Created by GaBiK on 18.08.2016.
 */
class SelectStationHandler {
    public static final String TAG = "SelectStationHandler";

    private LinearLayout rootLayout;
    private ViewGroup parentLayout;

    private Rect rectangleParent = new Rect();
    private float width;
    private float height;
    private float halfWidth;
    private float halfHeight;

    /**
     * Факт инициализации размеров View элементов.
     * Инициализация производится при первом обращении.
     */
    private boolean isReadViewParam = false;

    private Button buttonStart;
    private Button buttonFinish;
    private TextView nameStation;

    public Button getButtonStart() {
        return buttonStart;
    }

    public Button getButtonFinish() {
        return buttonFinish;
    }

    public SelectStationHandler(Activity activity, final ViewGroup parentLayout){
        if (activity == null || parentLayout == null) throw new IllegalArgumentException("Сontext or parentLayout is" +
                " null");

        try {
            this.parentLayout = parentLayout;

            LayoutInflater layoutInflater = activity.getLayoutInflater();
            android.view.View view1 = layoutInflater.inflate(R.layout.select_station, parentLayout, false);
            rootLayout = (LinearLayout) view1;
            rootLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    width = rootLayout.getWidth();
                    height = rootLayout.getHeight();
                    halfWidth = width / 2;
                    halfHeight = height / 2;
                }
            });

            parentLayout.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    parentLayout.getGlobalVisibleRect(rectangleParent);
                }
            });
            rootLayout.setX(-400);
            rootLayout.setY(-400);
            parentLayout.addView(rootLayout);

            buttonStart = (Button)activity.findViewById(R.id.start);
            buttonFinish = (Button)activity.findViewById(R.id.finish);
            nameStation = (TextView)activity.findViewById(R.id.nameStation);
        } catch (Exception e) {
            Log.e(TAG, "Ошибка инициализации SelectStationHandler", e);
        }
    }

    public void viewSelectStation(String nameStation, float x, float y){
        this.nameStation.setText(nameStation);
        rootLayout.setX(getLeftPoint(x));
        rootLayout.setY(getTopPoint(y));
        parentLayout.addView(rootLayout);
    }


    private float getLeftPoint(float x){
        float leftPointTemp = x - halfWidth;

        float restult = leftPointTemp;
        if (leftPointTemp < 0){
            restult = 0;
        }
        if (leftPointTemp + width > rectangleParent.right){
            restult = rectangleParent.right - width;
        }
        return restult;
    }


    private float getTopPoint(float y){
        float topPointTemp =  y - halfHeight;
        float restult = topPointTemp;

        if (topPointTemp < rectangleParent.top){
            restult =  rectangleParent.top;
        }
        if (topPointTemp + height > rectangleParent.bottom){
            restult =  rectangleParent.bottom - height;
        }
        return restult - rectangleParent.top;
    }


    public void hideSelectStation(){
        parentLayout.removeView(rootLayout);
    }
}
