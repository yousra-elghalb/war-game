package modeles;

import javafx.scene.image.ImageView;
import managers.ControllerBomb;
import managers.Scene;
import modeles.image.CustomImageBomb;
import modeles.image.CustomImageBomb;

public class Bomb extends ElementGraphic {

    public Bomb(Scene scene) {
        super(scene);
        this.customImage = CustomImageBomb.getCustomImageBomb("p", 30);
        this.controller = new ControllerBomb(scene, this);
        setImage(customImage.getImages().firstElement());
        /*setFitWidth(160);
        setFitHeight(150);*/

    }

    public CustomImageBomb getImageBomb() {
        return (CustomImageBomb) customImage;
    }

    public ControllerBomb getControllerBomb() {
        return (ControllerBomb) controller;
    }

}
