package njackson.com.android;

import com.gabik.metro.controller.Placeable;
import com.gabik.metro.controller.touch.Point;

/**
 * Created by GaBiK on 12.09.2016.
 */
public class TestPlaceble implements Placeable {

    private Point currentLeftTopPoint = new Point(0,0);
    private Point currentRightBottomPoint = new Point(10,10);
    @Override
    public Point getLeftTopPointScreen() {
        return new Point(0,0);
    }

    @Override
    public Point getRightBottomPointScreen() {
        return new  Point(100,100);
    }

    @Override
    public Point getLeftTopPointScene() {
        return currentLeftTopPoint;
    }

    @Override
    public Point getCurrentRightBottomPoint() {
        return currentRightBottomPoint;
    }

    @Override
    public void setPivot(Point pivotPoint) {

    }

    @Override
    public void updatePosition(Point pointTranslate) {

    }

    @Override
    public void setScale(float scale) {

    }

    @Override
    public Point getCorrectPointInvert(Point point) {
        return null;
    }
}
