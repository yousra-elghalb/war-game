package managers;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.TranslateTransition;
import javafx.geometry.NodeOrientation;
import javafx.util.Duration;
import modeles.Bullet;
import modeles.Joueur;
import modeles.Rocket;

public class ControllerJoueur extends Controller {

    private static int cn = 1;
    private String type = "";
    private long lastUpdate = 0;
    private static int cn2 = 1;
    private long lastUpdate3 = 0;
    AnimationTimer animationTimer3;
    AnimationTimer animationTimer;


    public ControllerJoueur(Scene scene, Joueur elementGraphic) {
        super(scene, elementGraphic);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (now - lastUpdate >= 58000000) {
                    if (cn > 3) cn = 0;
                    elementGraphic.setImage(cn);
                    cn++;
                    lastUpdate = now;
                }
            }
        };
        animationTimer.start();

    }


    public void Bombe() {
        animationTimer.stop();
        scene.removeJoueur(getJoueur());
        scene.addBomb((Joueur) this.elementGraphic);
    }

    public void init() {
        animationTimer.start();
    }

    public void tirer() {
        if (scene.getToolbarDeJeu().getNbRocket() > 0) {

            Rocket rocket = scene.addRocket();
//        rocket.getControllerRocket().tirer((Joueur) this.elementGraphic);
            tirer(rocket);
            scene.getToolbarDeJeu().removeRocket();
        }
    }

    public void tirer(Rocket rocket) {
        //        elementGraphic.translateXProperty().removeListener(changeListenerAdversaire);
        rocket.translateXProperty().addListener(rocket.getControllerRocket().getChangeListenerJoueur());
        rocket.getControllerRocket().setTransition(new TranslateTransition(Duration.millis(1200), rocket));
        rocket.setTranslateY(getJoueur().getY() + 38);
        rocket.setTranslateX(getJoueur().getX() + getJoueur().getFitWidth() - 35);
        rocket.getControllerRocket().getTransition().setToX(1300);
        if (rocket.getRotate() != 0)
            rocket.setRotate(0);
        rocket.getControllerRocket().getTransition().play();

    }

    public void tirerBullet() {
        Bullet bullet = scene.addBullet();
        bullet.setNodeOrientation(NodeOrientation.LEFT_TO_RIGHT);

        bullet.setX(getJoueur().getArm().getSortie().getCenterX());
        bullet.setY(getJoueur().getArm().getSortie().getCenterY());
//        rocket.getControllerRocket().tirer((Joueur) this.elementGraphic);
        tirer(bullet);
    }

    public void tirer(Bullet bullet) {


        bullet.xProperty().removeListener(bullet.getControllerBullet().getChangeListenerAdversaire());
        bullet.xProperty().addListener(bullet.getControllerBullet().getChangeListenerJoueur());
        double rotate = getJoueur().getArm().getRotate();
        double x = getJoueur().getArm().getSortie().getCenterX();
        double y = getJoueur().getArm().getSortie().getCenterY();
        bullet.setX(x);
        bullet.setY(y);

        int newX = (int) (x + 1200 * Math.cos((Math.PI * rotate / 180)));
        int newY = (int) (y + 1200 * Math.sin((Math.PI * rotate / 180)));

        KeyValue keyValue = new KeyValue(bullet.xProperty(), newX);
        KeyValue keyValue2 = new KeyValue(bullet.yProperty(), newY);
        bullet.setRotate(rotate);
        KeyFrame keyFrame = new KeyFrame(Duration.millis(1200), keyValue, keyValue2);
        bullet.getControllerBullet().getTimeline().getKeyFrames().setAll(keyFrame);
        bullet.getControllerBullet().getTimeline().play();
    }

    public Joueur getJoueur() {
        return (Joueur) elementGraphic;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
