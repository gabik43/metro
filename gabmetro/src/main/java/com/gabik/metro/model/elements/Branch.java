package com.gabik.metro.model.elements;

import android.graphics.Color;
import android.graphics.Point;

/**
 * Created by GaBiK on 26.02.2016.
 */
public class Branch {
    private String name;

    private TypeBranch type;
    public TypeBranch getType() {
        return type;
    }

    private int radius;
    public int getRadius() {
        return radius;
    }

    private int color;
    public int getColor() {
        return color;
    }

    private Point pointCenter;
    public Point getPointCenter() {
        return pointCenter;
    }

    public Branch(String name, int type, String color, int roudingPointX, int roudingPointY, int radius) {
        this.name = name;
        this.type = TypeBranch.fromInteger(type);
        this.color = Color.parseColor(color);
        pointCenter = new Point(roudingPointX, roudingPointY);
        this.radius = radius;
    }
}
