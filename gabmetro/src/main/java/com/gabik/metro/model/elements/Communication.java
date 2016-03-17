package com.gabik.metro.model.elements;

import android.graphics.Color;
import com.gabik.metro.controller.touch.Selectable;
import com.gabik.metro.controller.touch.SelectedRectangle;
import com.gabik.metro.model.param.DrawParamBendCommunication;
import com.gabik.metro.model.param.DrawParamCommunication;
import com.gabik.metro.model.param.DrawParamRingCommunication;
import com.gabik.metro.model.param.ParamsDerivable;
import com.gabik.metro.view.drawElements.Drawable;

/**
 * Created by GaBiK on 24.02.2016.
 */
public class Communication implements ParamsDerivable, Selectable {

    private Branch branch;
    private DrawParamCommunication drawParamCommunication;

    public int time;
    public int getTime() {
        return time;
    }

    public Communication(Station stationOne, Station stationTwo, int time, String bendPointX, String bendPointY) {
        if (stationOne == null && stationTwo == null) throw new IllegalArgumentException("Один из входных параметров " +
                "функции равен null");
        if (time <= 0) throw new IllegalArgumentException("Время между станциями не может быть: " + time);
        variableInitialization(stationOne, stationTwo, time, bendPointX, bendPointY);
    }

    private void variableInitialization(Station stationOne, Station stationTwo, int time, String bendPointX,
                                        String bendPointY){
        this.time = time;
        this.branch = stationOne.getBranchStation();
        initDrawParam(stationOne, stationTwo, bendPointX, bendPointY);
    }

    private void initDrawParam(Station stationOne, Station stationTwo, String bendPointX, String bendPointY) {
        Branch branchOne = stationOne.getBranchStation();
        Branch branchTwo = stationTwo.getBranchStation();
        if (branchOne == null || branchTwo == null) throw new NullPointerException("Ветки не должны содержать" +
                "null");
        TypeCommunication typeCommunication = getTypeCommunication(branchOne, branchTwo, bendPointX, bendPointY);
        switch (typeCommunication){
            case RING:
                drawParamCommunication = new DrawParamRingCommunication(stationOne.getPoint(),
                        stationTwo.getPoint(), branch.getPointCenter(), branch.getRadius(), branch.getColor());
                break;
            case BEND:
                drawParamCommunication = new DrawParamBendCommunication(stationOne.getPoint(),
                        stationTwo.getPoint(), branch.getColor(), bendPointX, bendPointY);
                break;
            case LINE:
                drawParamCommunication = new DrawParamCommunication(stationOne.getPoint(),
                        stationTwo.getPoint(), branch.getColor());
                break;
        }
    }

    private TypeCommunication getTypeCommunication(Branch branchOne, Branch branchTwo, String bendPointX, String bendPointY) {
        if (branchOne.getType() == TypeBranch.RING && branchTwo.getType() == TypeBranch.RING){
            return TypeCommunication.RING;
        }
        if (bendPointX != null && bendPointY != null){
            return TypeCommunication.BEND;
        }
        return TypeCommunication.LINE;
    }

    @Override
    public Drawable getParam() {
        return drawParamCommunication;
    }

    @Override
    public SelectedRectangle getSelectedRectangle() {
        return null;
    }

    @Override
    public void select() {
        drawParamCommunication.size = 15;
    }

    @Override
    public void deselect() {
        drawParamCommunication.size = 10;
    }

    private enum TypeCommunication {
        LINE,
        RING,
        BEND
    }
}
