package modeles;

import javafx.scene.image.ImageView;
import managers.Controller;
import managers.Scene;
import modeles.image.CustomImage;
import modeles.image.CustomImageBomb;

public abstract class ElementGraphic extends ImageView {
    protected CustomImage customImage;
    protected Controller controller;
    protected Scene scene;

    public ElementGraphic(Scene scene) {
        this.scene = scene;
    }

    public void setImageBomb(CustomImage customImage) {
        this.customImage = customImage;
    }

    public void setImage(int index) {
        setImage(customImage.getImages().get(index));
    }

    public void deplacerXY(double x, double y) {
        deplacerX(x);
        deplacerY(y);
    }

    public void deplacerX(double x) {
        setX(getX() + x);
    }

    public void deplacerY(double y) {
        setY(getY() + y);
    }

}
