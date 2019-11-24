package managers;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.Timeline;
import javafx.util.Duration;
import modeles.Adversaire;
import modeles.Soldat;

public class ControllerSoldat extends Controller {

    Timeline timeline;
    private AnimationTimer animationTimerChangeImage;
    int cn = 0;
    long lastUpdate;

    public Timeline getTimeline() {
        return timeline;
    }

    public AnimationTimer getAnimationTimerChangeImage() {
        return animationTimerChangeImage;
    }


    public ControllerSoldat(Scene scene, Soldat elementGraphic) {
        super(scene, elementGraphic);
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> {
            elementGraphic.setX(0);
        });
        final KeyValue chx = new KeyValue(elementGraphic.xProperty(), 1300);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(5000), chx);
        timeline.getKeyFrames().setAll(keyFrame);
        timeline.play();
        animationTimerChangeImage = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 98000000L) {
                    if (cn > 3) cn = 0;
                    elementGraphic.setImage(cn);
                    cn++;
                    lastUpdate = now;
                }
            }
        };
        animationTimerChangeImage.start();
        elementGraphic.xProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue.longValue() == 1300) {
                scene.removeSoldat(elementGraphic);
                elementGraphic.setX(0);
                scene.getToolbarDeJeu().addSoldat2();

            }
        });
    }


    public void bombe() {
        scene.removeSoldat(getSoldat());
        /*getSoldat().setActive(false);
        scene.addBomb(getSoldat());
   */
    }

    public Soldat getSoldat() {
        return (Soldat) elementGraphic;
    }
}
