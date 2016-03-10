package com.gabik.metro.model.elements;

/**
 * Created by GaBiK on 27.02.2016.
 */
public enum TypeBranch {
    RING,
    NORMAL;

    public static TypeBranch fromInteger(int x) {
        switch(x) {
            case 0:
                return RING;
            case 1:
                return NORMAL;
        }
        throw new IllegalArgumentException("Возникла ошибка при попытке приоберазования числа " + x + " в " +
                TypeBranch.class.getName());
    }
}
