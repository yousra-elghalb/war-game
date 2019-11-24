package managers;

import javafx.animation.AnimationTimer;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import modeles.Adversaire;
import modeles.Bullet;
import modeles.Joueur;
import modeles.Soldat;

import java.util.Timer;

public class ControllerBullet extends Controller {
    private Timer timer = new Timer();
    private Timer timer2 = new Timer();
    private static int cn = 1, cn2 = 1;
    AnimationTimer animationTimer;
    AnimationTimer animationTimer2;
    private long lastUpdate = 0;
    private long lastUpdate2 = 0;
    Timeline timeline;
    private ChangeListener<Number> changeListenerJoueur;
    private ChangeListener<Number> changeListenerAdversaire;
//    ElementGraphic source;

    public ChangeListener<Number> getChangeListenerJoueur() {
        return changeListenerJoueur;
    }

    public ChangeListener<Number> getChangeListenerAdversaire() {
        return changeListenerAdversaire;
    }

    public ControllerBullet(Scene scene, Bullet elementGraphic) {
        super(scene, elementGraphic);
        timeline = new Timeline();
        timeline.setCycleCount(1);
        changeListenerJoueur = (observable, oldValue, newValue) -> {
            testToucheAdversaire();
        };
        timeline.setOnFinished(event -> {
            scene.removeBullet(elementGraphic);
        });
        changeListenerAdversaire = (observable, oldValue, newValue) -> {
            testToucheJoueur();
            testToucheSoldat();
        };

     /*   animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 58000000) {
                    if (cn > 2) cn = 0;
                    elementGraphic.setImage(cn);
                    cn++;
                    lastUpdate = now;
                }
            }
        };
        animationTimer.start();*/
    }


    public void init() {
        elementGraphic.xProperty().removeListener(changeListenerJoueur);
        elementGraphic.xProperty().removeListener(changeListenerAdversaire);
        /*elementGraphic.translateXProperty().removeListener(changeListenerJoueur);
        elementGraphic.translateXProperty().removeListener(changeListenerAdversaire);*/

    }

    private void testToucheJoueur() {
        Joueur joueur = scene.getJoueur();
        if (joueur.isActive() && elementGraphic.getBoundsInParent().intersects(joueur.getBoundsInParent())) {
            joueur.getControllerJoueur().Bombe();
            timeline.stop();
            scene.removeBullet(getBullet());
        }
    }

    private void testToucheAdversaire() {
        Adversaire adversaireT = scene.getAdversairesOccupes().stream().filter(adversaire -> elementGraphic.getBoundsInParent().intersects(adversaire.getBoundsInParent())).findFirst().orElse(null);
        if (adversaireT != null) {
            adversaireT.getControllerAdversaire().bombe();
            timeline.stop();
            scene.removeBullet(getBullet());

        }
    }

    private void testToucheSoldat() {
        Soldat soldatt = scene.getSoldatsOccupes().stream().filter(soldat -> elementGraphic.getBoundsInParent().intersects(soldat.getBoundsInParent())).findFirst().orElse(null);
        if (soldatt != null) {
            soldatt.getControllerSoldat().bombe();
            timeline.stop();
            scene.removeBullet(getBullet());
            scene.getToolbarDeJeu().addSoldat();
        }
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public Bullet getBullet() {
        return (Bullet) elementGraphic;
    }
}
