package modeles;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import managers.ControllerSoldat;
import managers.Scene;
import modeles.image.CustomImageSoldat;

public class Soldat extends ElementGraphic {
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

    public Soldat(Scene scene) {
        super(scene);

        this.customImage = CustomImageSoldat.getCustomImageSoldat("soldat", 6);
        this.controller = new ControllerSoldat(scene, this);
        setImage(customImage.getImages().firstElement());
        active.addListener((observable) -> {
            if (isActive()) {
                getControllerSoldat().getTimeline().play();
                getControllerSoldat().getAnimationTimerChangeImage().start();
            } else {
                getControllerSoldat().getTimeline().stop();
                getControllerSoldat().getAnimationTimerChangeImage().stop();
            }
        });
        setX(0);
        setY(725);
        setFitWidth(40);
        setFitHeight(80);
    }

    public CustomImageSoldat getImageSoldat() {
        return (CustomImageSoldat) customImage;
    }

    public ControllerSoldat getControllerSoldat() {
        return (ControllerSoldat) controller;
    }

}
