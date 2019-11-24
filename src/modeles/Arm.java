package modeles;

import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import managers.ControllerJoueur;
import managers.Scene;
import modeles.image.CustomImageArm;
import modeles.image.CustomImageBullet;
import modeles.image.CustomImageJoueur;

public class Arm extends ElementGraphic {
    private Sortie sortie;
    private int x;

    public Arm(Scene scene) {
        super(scene);
        sortie = new Sortie();
        this.customImage = CustomImageArm.getCustomImageArm("arm", 1);
//        this.controller = new ControllerJoueur(scene, this);
        setImage(customImage.getImages().firstElement());
        setX(0);
        setY(0);
        x = 50;
        nodeOrientationProperty().addListener(observable -> {
            if (getNodeOrientation() == NodeOrientation.RIGHT_TO_LEFT)
                x = -50;
            else {
                x = 50;
            }
        });
        xProperty().addListener(observable -> {
            changeSortie();

        });
        yProperty().addListener(observable -> {
            changeSortie();

        });
        setFitWidth(100);
        setFitHeight(10);
    }

    public void rotateToTop() {
        setRotate(getRotate() - 10);
        changeSortie();

    }

    public void rotate(double rotate) {
        setRotate(rotate);
        changeSortie();

    }

    public void rotateToBottom() {
        setRotate(getRotate() + 10);
        changeSortie();

    }

    public void changeSortie() {
        if (x == 50)
            sortie.changeXY(getRotate(), getX() + 50, getY(), 50);
        else
            sortie.changeXYA(getRotate(), getX() + 50, getY(), 50);
    }

    @Override
    public void deplacerXY(double x, double y) {
        super.deplacerXY(x, y);

    }

    @Override
    public void deplacerX(double x) {
        super.deplacerX(x);
    }

    @Override
    public void deplacerY(double y) {
        super.deplacerY(y);
    }

    public CustomImageJoueur getImageJoueur() {
        return (CustomImageJoueur) customImage;
    }

    public ControllerJoueur getControllerJoueur() {
        return (ControllerJoueur) controller;
    }

    public Sortie getSortie() {
        return sortie;
    }

    public void setSortie(Sortie sortie) {
        this.sortie = sortie;
    }
}
