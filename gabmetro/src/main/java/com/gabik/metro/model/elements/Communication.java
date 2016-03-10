package com.gabik.metro.model.elements;

import com.gabik.metro.model.param.DrawParamCommunication;
import com.gabik.metro.model.param.DrawParamRingCommunication;
import com.gabik.metro.model.param.Paramable;
import com.gabik.metro.view.drawElements.Drawable;

/**
 * Created by GaBiK on 24.02.2016.
 */
public class Communication implements Paramable {

    private Station stationOne, stationTwo;
    private Branch branch;
    private DrawParamCommunication drawParamCommunication;
    public int time;

    public Communication(Station stationOne, Station stationTwo, int time) {
        if (stationOne == null && stationTwo == null) throw new IllegalArgumentException("Один из входных параметров " +
                "функции равен null");
        variableInitialization(stationOne, stationTwo, time);
    }

    private void variableInitialization(Station stationOne, Station stationTwo, int time){
        this.time = time;
        this.stationOne = stationOne;
        this.stationTwo = stationTwo;
        Branch branchOne = stationOne.getBranchStation();
        Branch branchTwo = stationTwo.getBranchStation();
        this.branch = branchOne;
        if (branchOne.getType() == TypeBranch.RING && branchTwo.getType() == TypeBranch.RING){
            drawParamCommunication = new DrawParamRingCommunication(stationOne.getPoint(),
                    stationTwo.getPoint(), branch.getPointCenter(), branch.getRadius(), branch.getColor());
        } else {
            drawParamCommunication = new DrawParamCommunication(stationOne.getPoint(),
                   stationTwo.getPoint(), branch.getColor());
        }
    }

    @Override
    public Drawable getParam() {
        return drawParamCommunication;
    }
}
