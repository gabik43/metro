package com.gabik.metro;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.gabik.metro.model.StationSelectable;
import com.gabik.metro.model.TypeSelectStation;
import com.gabik.metro.model.elements.Station;

import java.util.logging.Logger;

/**
 * Created by GaBiK on 10.04.2016.
 */
public class StationSelectButton extends LinearLayout implements View.OnClickListener, StationSelectable {

    private TypeSelectStation typeSelectStation;
    private static final Logger log = Logger.getLogger(StationSelectButton.class.getName());
    EditText editText;
    Button button;
    int width = 0;
    int height = 0;
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public StationSelectButton(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    public StationSelectButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public StationSelectButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public StationSelectButton(Context context) {
        super(context);
        init(context);
    }

    public void setTypeSelectStation(TypeSelectStation typeSelectStation){
        this.typeSelectStation = typeSelectStation;
    }
    private void init(Context context){
        setOnClickListener(this);
        //editText = new EditText(context);
        //editText.setText("df");

        //this.addView(editText);

        button = new Button(context);
        ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT);
        button.setLayoutParams(layoutParams);
        this.addView(button);


    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec){
        int parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        int parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        button.setWidth(MeasureSpec.getSize(widthMeasureSpec));
        button.setHeight(MeasureSpec.getSize(heightMeasureSpec));
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    public void onClick(View v) {

        System.out.print("Click");
    }


    @Override
    public void onSelectStation(TypeSelectStation typeSelectStation, Station station) {
        if (this.typeSelectStation == typeSelectStation) {
            button.setText(station.getNameStation().getNameWithDelimiter());
        }
    }
}
