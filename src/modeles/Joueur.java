package modeles;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import managers.ControllerJoueur;
import managers.Scene;
import modeles.image.CustomImageJoueur;

public class Joueur extends ElementGraphic {
    Arm arm;
    private BooleanProperty active = new SimpleBooleanProperty(true);

    public boolean isActive() {
        return active.get();
    }

    public BooleanProperty activeProperty() {
        return active;
    }

    public void setActive(boolean active) {
        this.active.set(active);
    }

    public Joueur(Scene scene) {
        super(scene);
        arm = new Arm(scene);
        this.customImage = CustomImageJoueur.getCustomImageJoueur("jo", 4);
        this.controller = new ControllerJoueur(scene, this);
        setImage(customImage.getImages().firstElement());
        setX(0);
        setY(20);
        arm.deplacerXY(62, 49);
        setFitWidth(150);
        setFitHeight(50);
    }

    @Override
    public void deplacerXY(double x, double y) {
        super.deplacerXY(x, y);
//        arm.deplacerXY(x, y);
    }

    @Override
    public void deplacerX(double x) {
        super.deplacerX(x);
        arm.deplacerX(x);

    }

    @Override
    public void deplacerY(double y) {
        super.deplacerY(y);
        arm.deplacerY(y);
    }

    public CustomImageJoueur getImageJoueur() {
        return (CustomImageJoueur) customImage;
    }

    public ControllerJoueur getControllerJoueur() {
        return (ControllerJoueur) controller;
    }

    public Arm getArm() {
        return arm;
    }
}
