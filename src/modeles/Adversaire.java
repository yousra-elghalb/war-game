package modeles;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.geometry.NodeOrientation;
import javafx.scene.Node;
import javafx.scene.image.ImageView;
import managers.ControllerAdversaire;
import managers.Scene;
import modeles.image.CustomImageAdversaire;
import modeles.image.CustomImageAdversaire;
import modeles.image.CustomImageAdversaire;

public class Adversaire extends ElementGraphic {
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

    public Adversaire(Scene scene) {
        super(scene);
        arm = new Arm(scene);
        this.customImage = CustomImageAdversaire.getCustomImageAdversaire("ad", 4);
        this.controller = new ControllerAdversaire(scene, this);
        setImage(customImage.getImages().firstElement());
//        setTranslateY(200);
        active.addListener(observable -> {
            if (isActive()) {
                setX(2000);
                setY(500);
                getControllerAdversaire().getAnimationTimerChangeImage().start();
                getControllerAdversaire().getAnimationTimerChangePosition().start();
                getControllerAdversaire().getAnimationTimerChangeRotateArm().start();
                getControllerAdversaire().getTimeline().play();
                getControllerAdversaire().getAnimationTimerTirer().start();
                getControllerAdversaire().getAnimationTimerTirerBullet().start();
            } else {
                getControllerAdversaire().getTimeline().stop();
                getControllerAdversaire().getAnimationTimerChangeImage().stop();
                getControllerAdversaire().getAnimationTimerChangePosition().stop();
                getControllerAdversaire().getAnimationTimerChangeRotateArm().stop();
                getControllerAdversaire().getAnimationTimerTirer().stop();
                getControllerAdversaire().getAnimationTimerTirerBullet().stop();
            }
        });
        setX(1500);
        setY(400);
        arm.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
        arm.setX(getX() - 26);
        arm.setY(getY() + 27);
        xProperty().addListener(observable -> {
            arm.setX(getX() - 26);
        });
        yProperty().addListener(observable -> {
            arm.setY(getY() + 27);

        });
        setFitWidth(150);
        setFitHeight(50);
    }

    @Override
    public void deplacerXY(double x, double y) {
        super.deplacerXY(x, y);
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

    public Arm getArm() {
        return arm;
    }

    public CustomImageAdversaire getImageAdversaire() {
        return (CustomImageAdversaire) customImage;
    }

    public ControllerAdversaire getControllerAdversaire() {
        return (ControllerAdversaire) controller;
    }
}
