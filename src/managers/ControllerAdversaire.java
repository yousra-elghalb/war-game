package managers;

import Program.Main;
import javafx.animation.*;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.NodeOrientation;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.util.Duration;
import modeles.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;

public class ControllerAdversaire extends Controller {


    private AnimationTimer animationTimerChangeImage;
    private AnimationTimer animationTimerChangePosition;
    private AnimationTimer animationTimerChangeRotateArm;
    private AnimationTimer animationTimerTirer;
    private AnimationTimer animationTimerTirerBullet;
    private static int cn = 1;
    private String type = "";
    private long lastUpdate = 0;
    private long lastUpdate2 = 0;
    private static int cn2 = 1;
    private long lastUpdate3 = 0;
    private long lastUpdate4 = 0;
    private long lastUpdate5 = 0;
    int Wmax = 1200, Wmin = 100;
    int Hmax = 850, Hmin = 50;
    TranslateTransition transition;
    //    Queue<Timeline> deplacementsLibres = new ArrayDeque<>();
    private Timeline timeline;
    private boolean deplace = true;

    public AnimationTimer getAnimationTimerChangeImage() {
        return animationTimerChangeImage;
    }

    public AnimationTimer getAnimationTimerChangePosition() {
        return animationTimerChangePosition;
    }

    public AnimationTimer getAnimationTimerTirer() {
        return animationTimerTirer;
    }

    public AnimationTimer getAnimationTimerChangeRotateArm() {
        return animationTimerChangeRotateArm;
    }

    public AnimationTimer getAnimationTimerTirerBullet() {
        return animationTimerTirerBullet;
    }

    public ControllerAdversaire(Scene scene, Adversaire elementGraphic) {
        super(scene, elementGraphic);
        timeline = new Timeline();
        timeline.setCycleCount(1);
        timeline.setOnFinished(event -> {
            deplace = true;
        });
        animationTimerChangeRotateArm = new AnimationTimer() {
            @Override
            public void handle(long now) {
//                 Random r = new Random();
                long v = (long) (8000000000L + Math.random() * (9080000000L - 8000000000L));
                if (now - lastUpdate3 >= v) {
                    double rotate = (-60 + Math.random() * 120);
                    getAdversaire().getArm().rotate(rotate);
                    lastUpdate3 = now;
                }
            }
        };
        animationTimerChangeRotateArm.start();

        animationTimerChangeImage = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 28000000) {
                    if (cn > 3) cn = 0;
                    elementGraphic.setImage(cn);
                    cn++;
                    lastUpdate = now;
                }
            }
        };
        animationTimerChangeImage.start();


        animationTimerChangePosition = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate2 >= 2080000000L && deplace) {
                    double eleW = elementGraphic.getFitWidth();
                    double x = Math.random() * (scene.getZone1().getPointMax().getX() - eleW - scene.getZone1().getPointMin().getX() - 200) + scene.getZone1().getPointMin().getX() + 200;
                    double y = Math.random() * (((scene.getZone1().getPointMax().getY() - scene.getZone1().getPointMin().getY()) + 1)) + scene.getZone1().getPointMin().getY();
//                    int y = r.nextInt((Hmax - Hmin) + 1) + Hmin;
                    deplace = false;
                    final KeyValue chx = new KeyValue(elementGraphic.xProperty(), x);
                    final KeyValue chy = new KeyValue(elementGraphic.yProperty(), y);
                    KeyFrame keyFrame = new KeyFrame(Duration.millis(1400), chx, chy);
                    timeline.getKeyFrames().setAll(keyFrame);
                    timeline.play();
                    /*
                    TranslateTransition transition2 = getDeplacement();
//                    TranslateTransition transition2 = new TranslateTransition(Duration.millis(1000), elementGraphic);
                    transition2.setToX(x);
                    transition2.setToY(y);
                    transition2.setCycleCount(1);
                    transition2.play();
                   *//* transition2.setOnFinished(event -> {
                        elementGraphic.setX(elementGraphic.getTranslateX());
                        elementGraphic.setY(elementGraphic.getTranslateY());
                        elementGraphic.setTranslateX(0);
                        elementGraphic.setTranslateY(0);
                    });*//*
                    transition2.setOnFinished(event -> {
                        deplacementsLibres.add(transition2);
                    });*/
//                    elementGraphic.setTranslateY(y);
//                    elementGraphic.setTranslateX(x);
                    lastUpdate2 = now;
                }
            }
        };
        animationTimerChangePosition.start();

        animationTimerTirer = new AnimationTimer() {
            @Override
            public void handle(long now) {
//                if (Math.random() <= 0.002) {
                if (now - lastUpdate4 >= 90800000000L) {
                    tirer();
                    lastUpdate4 = now;
                }
            }
        };
        animationTimerTirer.start();

        animationTimerTirerBullet = new AnimationTimer() {
            @Override
            public void handle(long now) {
//                if (Math.random() <= 0.002) {
                Random r = new Random();
                long v = (long) (2080000000L + Math.random() * (9080000000L - 2080000000L));
                if (now - lastUpdate5 >= v) {
                    tirerBullet();
                    lastUpdate5 = now;
                }
            }
        };
        animationTimerTirerBullet.start();
      /*  transition = new TranslateTransition(Duration.millis(2000), elementGraphic);
        transition.setFromX(1400);
        transition.setToX(1000);
        transition.setCycleCount(1);
        transition.play();*/
    }


    public void bombe() {
        scene.removeAdversaire(getAdversaire());
        getAdversaire().setActive(false);
        scene.addBomb((Adversaire) this.elementGraphic);
    }

    public void tirer() {
        Rocket rocket = scene.addRocket();
//        rocket.getControllerRocket().tirer((Adversaire) this.elementGraphic);
        tirer(rocket);
    }

    public void tirer(Rocket rocket) {
//        elementGraphic.translateXProperty().removeListener(changeListenerJoueur);
        rocket.translateXProperty().addListener(rocket.getControllerRocket().getChangeListenerAdversaire());
        rocket.getControllerRocket().setTransition(new TranslateTransition(Duration.millis(1200), rocket));
        rocket.setTranslateY(getAdversaire().getY() + 25);
        if (rocket.getRotate() != 180)
            rocket.setRotate(180);
        rocket.setTranslateX(getAdversaire().getX() - getAdversaire().getFitWidth());
        rocket.getControllerRocket().getTransition().setToX(3);
        rocket.getControllerRocket().getTransition().play();

    }

    public void tirerBullet() {
        Bullet bullet = scene.addBullet();
        bullet.setNodeOrientation(NodeOrientation.RIGHT_TO_LEFT);
//        rocket.getControllerRocket().tirer((Joueur) this.elementGraphic);
        tirer(bullet);
    }

    public void tirer(Bullet bullet) {
        bullet.xProperty().addListener(bullet.getControllerBullet().getChangeListenerAdversaire());
        bullet.xProperty().removeListener(bullet.getControllerBullet().getChangeListenerJoueur());
        double rotate = getAdversaire().getArm().getRotate();
        double x = getAdversaire().getArm().getSortie().getCenterX();
        double y = getAdversaire().getArm().getSortie().getCenterY();
        bullet.setX(x);
        bullet.setY(y);

        System.out.println("x= " + x + " y= " + y);
        int newX = (int) (x + -1200 * Math.cos((Math.PI * rotate / 180)));
        int newY = (int) (y + -1200 * Math.sin((Math.PI * rotate / 180)));

        KeyValue keyValue = new KeyValue(bullet.xProperty(), newX);
        KeyValue keyValue2 = new KeyValue(bullet.yProperty(), newY);
        bullet.setRotate(rotate);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1200), keyValue, keyValue2);
        bullet.getControllerBullet().getTimeline().getKeyFrames().setAll(keyFrame);
        bullet.getControllerBullet().getTimeline().play();
    }

    public Adversaire getAdversaire() {
        return (Adversaire) elementGraphic;
    }

    public Timeline getTimeline() {
        return timeline;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
