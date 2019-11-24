package managers;

import Program.Main;
import javafx.animation.AnimationTimer;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import modeles.Adversaire;
import modeles.ElementGraphic;
import modeles.Joueur;
import modeles.Rocket;

import java.net.URL;
import java.util.*;

public class ControllerRocket extends Controller {
    private Timer timer = new Timer();
    private Timer timer2 = new Timer();
    private static int cn = 1, cn2 = 1;
    AnimationTimer animationTimer;
    AnimationTimer animationTimer2;
    private long lastUpdate = 0;
    private long lastUpdate2 = 0;
    TranslateTransition transition;
    private ChangeListener<Number> changeListenerJoueur;
    private ChangeListener<Number> changeListenerAdversaire;
//    ElementGraphic source;

    public ChangeListener<Number> getChangeListenerJoueur() {
        return changeListenerJoueur;
    }

    public ChangeListener<Number> getChangeListenerAdversaire() {
        return changeListenerAdversaire;
    }

    public ControllerRocket(Scene scene, Rocket elementGraphic) {
        super(scene, elementGraphic);
        changeListenerJoueur = (observable, oldValue, newValue) -> {
            testToucheAdversaire();
            if (newValue.longValue() > 1110) {
                transition.stop();
                animationTimer.stop();
                scene.removeRocket(elementGraphic);
                scene.addBomb(elementGraphic);
            }
        };
        changeListenerAdversaire = (observable, oldValue, newValue) -> {
            testToucheJoueur();
            if (newValue.longValue() < 5) {
                transition.stop();
                animationTimer.stop();
                scene.removeRocket(elementGraphic);
                scene.addBomb(elementGraphic);
            }
        };
        animationTimer = new AnimationTimer() {
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
        animationTimer.start();
    }

    public void init() {
        elementGraphic.translateXProperty().removeListener(changeListenerJoueur);
        elementGraphic.translateXProperty().removeListener(changeListenerAdversaire);

    }

    private void testToucheJoueur() {
        Joueur joueur = scene.getJoueur();
        if (joueur.isActive() && elementGraphic.getBoundsInParent().intersects(joueur.getBoundsInParent())) {
            joueur.getControllerJoueur().Bombe();
            transition.stop();
            animationTimer.stop();
            scene.removeRocket(getRocket());
        }
    }

    private void testToucheAdversaire() {
        Adversaire adversaireT = scene.getAdversairesOccupes().stream().filter(adversaire -> elementGraphic.getBoundsInParent().intersects(adversaire.getBoundsInParent())).findFirst().orElse(null);
        if (adversaireT != null) {
            adversaireT.getControllerAdversaire().bombe();
            transition.stop();
            animationTimer.stop();
            scene.removeRocket(getRocket());
        }
      /*  scene.getAdversairesOccupes().stream().forEach(adversaire -> {
            if (elementGraphic.getBoundsInParent().intersects(adversaire.getBoundsInParent())) {

            }
        });*/
    }

    public TranslateTransition getTransition() {
        return transition;
    }


    public void setTransition(TranslateTransition transition) {
        this.transition = transition;
    }

    public double calculeDist(double x, double y, double x2, double y2) {
        return Math.sqrt(Math.pow(x - x2, 2) + Math.pow(y - y2, 2));
    }

    public Rocket getRocket() {
        return (Rocket) elementGraphic;
    }
}
