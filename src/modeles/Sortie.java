package modeles;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Sortie extends Circle {
    public Sortie() {
        setFill(new Color(0, 0, 0, 0));
        setRadius(5);
    }


    public void changeXY(double rotate, double x, double y, double reduis) {
        int newX = (int) (x + reduis * Math.cos((Math.PI * rotate / 180)));
        int newY = (int) (y + reduis * Math.sin((Math.PI * rotate / 180)));
        setCenterX(newX);
        setCenterY(newY);
    }

    public void changeXYA(double rotate, double x, double y, double reduis) {
        int newX = (int) (x - reduis * Math.cos((Math.PI * rotate / 180)));
        int newY = (int) (y - reduis * Math.sin((Math.PI * rotate / 180)));
        setCenterX(newX);
        setCenterY(newY);
    }

}
