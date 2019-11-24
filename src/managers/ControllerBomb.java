package managers;

import javafx.animation.AnimationTimer;
import modeles.Bomb;
import modeles.ElementGraphic;

public class ControllerBomb extends Controller {
    private AnimationTimer animationTimerChangeImage;
    long lastUpdate;
    int cn = 0;

    public ControllerBomb(Scene scene, Bomb elementGraphic) {
        super(scene, elementGraphic);


        animationTimerChangeImage = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 88000000) {
                    if (cn > 29) {
                        cn = 0;
                        animationTimerChangeImage.stop();
                        scene.removeBomb(elementGraphic);
                        return;
                    }
                    elementGraphic.setImage(cn);
                    cn++;
                    lastUpdate = now;
                }
            }
        };
    }

    public void startBomb(ElementGraphic cible) {
        startBomb(cible.getX(), cible.getY());
    }

    public void startBomb(double x, double y) {
        this.elementGraphic.setX(x);
        this.elementGraphic.setY(y - 75);
        animationTimerChangeImage.start();
    }

    public AnimationTimer getAnimationTimerChangeImage() {
        return animationTimerChangeImage;
    }

    public Bomb getBomb() {
        return (Bomb) elementGraphic;
    }
}
