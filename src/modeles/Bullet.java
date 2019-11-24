package modeles;

import managers.ControllerBullet;
import managers.Scene;
import modeles.image.CustomImageBullet;

public class Bullet extends ElementGraphic {

    public Bullet(Scene scene) {
        super(scene);
        this.customImage = CustomImageBullet.getCustomImageBullet("bu", 1);
        System.out.println("scene bu= [" + scene + "]");
        this.controller = new ControllerBullet(scene, this);
        setImage(customImage.getImages().firstElement());
//        setX(1400);
        setFitWidth(40);
        setFitHeight(8);
    }

    public CustomImageBullet getImageBullet() {
        return (CustomImageBullet) customImage;
    }

    public ControllerBullet getControllerBullet() {
        return (ControllerBullet) controller;
    }

}
