package com.gabik.metro.controller.touch;

/**
 * Created by GaBiK on 06.11.2015.
 */
public interface Selectable {


    public SelectedRectangle getSelectedRectangle();
    void select();
    void deselect();
}
