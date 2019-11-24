package modeles;

import javafx.scene.Node;
import javafx.scene.image.ImageView;
import managers.ControllerRocket;
import managers.Scene;
import modeles.image.CustomImageRocket;
import modeles.image.CustomImageRocket;
import modeles.image.CustomImageRocket;

public class Rocket extends ElementGraphic {

    public Rocket(Scene scene) {
        super(scene);
        this.customImage = CustomImageRocket.getCustomImageRocket("ro", 3);
        this.controller = new ControllerRocket(scene, this);
        setImage(customImage.getImages().firstElement());
        setFitWidth(100);
        setFitHeight(20);
    }

    public CustomImageRocket getImageRocket() {
        return (CustomImageRocket) customImage;
    }

    public ControllerRocket getControllerRocket() {
        return (ControllerRocket) controller;
    }

}
