package modeles;

import javafx.geometry.Point2D;

public class Zone {
    Point2D pointMin;
    Point2D pointMax;

    public Zone(double x1, double y1, double x2, double y2) {
        this.pointMin = new Point2D(x1, y1);
        this.pointMax = new Point2D(x2, y2);
    }

    public Point2D getPointMin() {
        return pointMin;
    }

    public void setPointMin(Point2D pointMin) {
        this.pointMin = pointMin;
    }

    public Point2D getPointMax() {
        return pointMax;
    }

    public void setPointMax(Point2D pointMax) {
        this.pointMax = pointMax;
    }
}
