package com.gabik.metro.model.elements;

import android.graphics.Point;
import com.gabik.metro.controller.touch.SelectedRectangle;
import com.gabik.metro.controller.touch.Selectable;
import com.gabik.metro.model.param.DrawParamStation;
import com.gabik.metro.model.param.ParamsDerivable;
import com.gabik.metro.view.drawElements.Drawable;

/**
 * Created by GaBiK on 04.11.2015.
 */
public class Station implements Selectable, ParamsDerivable {

    public int getId() {
        return id;
    }

    private int id;
    private NameStation nameStation;

    public NameStation getNameStation() {
        return nameStation;
    }

    private DrawParamStation drawParamStation;

    public Point getPoint(){
        return drawParamStation.getPoint();
    }

    public Branch getBranchStation() {
        return branchStation;
    }

    private Branch branchStation;

    public Station(int id, Branch branch, NameStation nameStation, int x, int y) {
        if (branch == null) throw new IllegalArgumentException("У элемента с именем: " + nameStation + "branch = null");
        this.id = id;
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

    @Override
    public String toString() {
        return "Station{" +
                "id=" + id +
                ", nameStation=" + nameStation +
                ", drawParamStation=" + drawParamStation +
                ", branchStation=" + branchStation +
                '}';
    }
}
