package managers;

import modeles.ElementGraphic;

public abstract class Controller {
    protected ElementGraphic elementGraphic;
    protected Scene scene;

    public Controller(Scene scene, ElementGraphic elementGraphic) {
        this.elementGraphic = elementGraphic;
        this.scene = scene;
    }
}
