package com.gabik.metro.controller.touch;

/**
 * Created by GaBiK on 11.11.2015.
 */
public class ScaleHandler {
    private double oldDistance = 0;
    private float scale;
    public float getScale(float positionXBegin, float positionYBegin, float positionXEnd, float positionYEnd){
        scale = 0.01f;
        double distance = CalculateDistance(positionXBegin, positionYBegin, positionXEnd, positionYEnd);
        if (distance > oldDistance){
            oldDistance = distance;
            return scale;
        }
        oldDistance = distance;
        return -scale;
    }




    private double CalculateDistance(float xOne, float yOne, float xTwo, float yTwo){
        return Math.sqrt(Math.pow(xTwo-xOne,2) + Math.pow(yTwo-yOne,2));
    }
}
