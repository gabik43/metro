package com.gabik.metro.model.elements;

import android.graphics.Point;
import com.gabik.metro.controller.touch.SelectedRectangle;
import com.gabik.metro.controller.touch.Selectable;
import com.gabik.metro.model.param.DrawParamStation;
import com.gabik.metro.model.param.Paramable;
import com.gabik.metro.view.drawElements.Drawable;

/**
 * Created by GaBiK on 04.11.2015.
 */
public class Station implements Selectable, Paramable {

    private NameStation nameStation;
    private DrawParamStation drawParamStation;

    public Point getPoint(){
        return drawParamStation.getPoint();
    }

    public Branch getBranchStation() {
        return branchStation;
    }

    private Branch branchStation;

    public Station(Branch branch, NameStation nameStation, int x, int y) {
        if (branch == null) throw new IllegalArgumentException("У элемента с именем: " + nameStation + "branch = null");
        this.branchStation = branch;
        this.nameStation = nameStation;
        drawParamStation = new DrawParamStation(new Point(x,y), branch.getColor(), nameStation.getSelectedRectangle());
    }

    @Override
    public SelectedRectangle getSelectedRectangle() {
        return drawParamStation.getSelectedRectangle();
    }

    @Override
    public void select() {
        drawParamStation.activeParam();
        nameStation.select();
    }

    @Override
    public void deselect() {
        drawParamStation.deActiveParam(branchStation.getColor());
        nameStation.deselect();
    }

    @Override
    public Drawable getParam() {
        return drawParamStation;
    }
}