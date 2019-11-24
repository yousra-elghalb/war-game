package managers;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.util.Duration;
import modeles.*;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.stream.Stream;

public class Scene implements Initializable {
    public AnchorPane background;
    public AnchorPane nextbackground;
    public AnchorPane primary;
    public BorderPane ctoolbar;
    private ToolbarDeJeu toolbarDeJeu;
    private Background backgroundimg;
    private Image image;
    private BackgroundSize backgroundSize;
    Timer timer;
    private Joueur joueur;
    private ControllerJoueur controllerJoueurManager;
    //    private HashMap<ImageView, ControllerAdversaire> adversaires = new HashMap<>();
    private Vector<Adversaire> adversairesOccupes = new Vector<>();
    private Queue<Adversaire> adversairesLibres = new ArrayDeque<>();
    private Vector<Bomb> bombsOccupes = new Vector<>();
    private Queue<Bomb> bombsLibres = new ArrayDeque<>();
    private Vector<Bullet> bulletsOccupes = new Vector<>();
    private Queue<Bullet> bulletsLibres = new ArrayDeque<>();
    private Vector<Rocket> rocketsOccupes = new Vector<>();
    private Queue<Rocket> rocketsLibres = new ArrayDeque<>();
    private Vector<Soldat> soldatsOccupes = new Vector<>();
    private Queue<Soldat> soldatsLibres = new ArrayDeque<>();
    long lastUpdate2 = 0;
    long lastUpdate3 = 0;
    long NbAdversaires = 0;
    AnimationTimer animationTimer2;
    AnimationTimer animationTimer3;
    AnimationTimer animationTimer;
    Timeline timelineJoueur;
    Zone zone1 = new Zone(0, 32, 1300, 700);

    public Joueur getJoueur() {
        return joueur;
    }

    public ToolbarDeJeu getToolbarDeJeu() {
        return toolbarDeJeu;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {


        changeBackground();
        addToolBar();
        addJoueur();
        addAdversaires();
        addSoldats();

        timelineJoueur = new Timeline(new KeyFrame(Duration.millis(2000), event -> addJoueur()));
        timelineJoueur.setOnFinished(event -> {
            joueur.getControllerJoueur().init();
        });
    }

    public void addToolBar() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/toolbarDeJeu.fxml"));
            ctoolbar.setCenter(fxmlLoader.load());
            this.toolbarDeJeu = fxmlLoader.getController();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void changeBackground() {
        timer = new Timer();
        image = new Image("images/stage1.jpg", 1302, 900, false, false);
        backgroundSize = new BackgroundSize(1302, 900, false, false, false, false);
        backgroundimg = new Background(new BackgroundImage(image, BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT, BackgroundPosition.CENTER, backgroundSize));

        background.setBackground(backgroundimg);
        background.setTranslateX(0);
        nextbackground.setBackground(backgroundimg);
        animationTimer = new AnimationTimer() {
            @Override
            public void handle(long now) {
                if (background.getTranslateX() > (primary.getWidth() * -1)) {
                    background.setTranslateX(background.getTranslateX() - 1);
                } else {
                    background.setTranslateX(primary.getWidth());
                }
                if (nextbackground.getTranslateX() > (primary.getWidth() * -1)) {
                    nextbackground.setTranslateX(nextbackground.getTranslateX() - 1);
                } else {
                    nextbackground.setTranslateX(primary.getWidth());
                }
            }
        };
        animationTimer.start();
    }


    public void addAdversaires() {
        animationTimer2 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Random r = new Random();
                long v = r.nextInt((2080000000 - 1080000000) + 1) + 1080000000;
                if (now - lastUpdate2 >= v) {
                    if (adversairesOccupes.size() < 6) {
                        addAdversaire();
                        lastUpdate2 = now;
                    }
                }
            }
        };
        animationTimer2.start();
    }

    public void addSoldats() {
        animationTimer3 = new AnimationTimer() {
            @Override
            public void handle(long now) {
                Random r = new Random();
                long v = (long) (Math.random() * (9080000000L - 1080000000L) + 1080000000L);
                if (now - lastUpdate3 >= v) {
                    if (soldatsLibres.size() < 5) {
                        addSoldat();
                        lastUpdate3 = now;
                    }
                }
            }
        };
        animationTimer3.start();
    }

    public void removeJoueur(Joueur joueur) {
        this.primary.getChildren().remove(joueur);
        joueur.setActive(false);
        primary.getChildren().remove(joueur.getArm());
        primary.getChildren().remove(joueur.getArm().getSortie());
        if (toolbarDeJeu.getCeours().size() > 0) {
            timelineJoueur.setCycleCount(1);
            timelineJoueur.play();
            toolbarDeJeu.removeCeour();
        }
    }

    public void addJoueur() {
        if (this.joueur == null)
            this.joueur = new Joueur(this);
        joueur.setActive(true);
        primary.getChildren().add(joueur);
        primary.getChildren().add(joueur.getArm());
        primary.getChildren().add(joueur.getArm().getSortie());
    }

    public void removeBomb(Bomb bomb) {
        this.primary.getChildren().remove(bomb);
        bombsOccupes.removeElement(bomb);
        bombsLibres.add(bomb);
    }

    private Bomb addBomb() {
        Bomb bomb;
        if (bombsLibres.size() == 0) {
            bomb = new Bomb(this);
            bombsOccupes.add(bomb);
            this.primary.getChildren().add(bomb);
        } else {
            bomb = bombsLibres.poll();
            bombsOccupes.add(bomb);
            this.primary.getChildren().add(bomb);
        }
        return bomb;
    }

    public Bomb addBomb(ElementGraphic cible) {
        Bomb bomb = addBomb();
        bomb.getControllerBomb().startBomb(cible.getTranslateX(), cible.getTranslateY());
        return bomb;
    }

    public Bomb addBomb(Adversaire cible) {
        Bomb bomb = addBomb();
        bomb.getControllerBomb().startBomb(cible);
        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(3000), event -> cible.setActive(true)));
        timeline.setCycleCount(1);
        timeline.play();
        return bomb;
    }

    public Bomb addBomb(Joueur cible) {
        Bomb bomb = addBomb();
        bomb.getControllerBomb().startBomb(cible);
        return bomb;
    }

    public void removeRocket(Rocket rocket) {
        rocketsOccupes.removeElement(rocket);
        this.primary.getChildren().remove(rocket);
        rocketsLibres.add(rocket);
        rocket.getControllerRocket().init();
    }

    public Rocket addRocket() {
        System.out.println("ro " + rocketsOccupes.size() + " rl" + rocketsLibres.size());
        Rocket rocket;
        if (rocketsLibres.size() == 0) {
            rocket = new Rocket(this);
            rocketsOccupes.add(rocket);
            this.primary.getChildren().add(rocket);
        } else {
            rocket = rocketsLibres.poll();
            rocketsOccupes.add(rocket);
            this.primary.getChildren().add(rocket);
        }
        return rocket;
    }

    public void removeBullet(Bullet bullet) {
        bulletsOccupes.removeElement(bullet);
        this.primary.getChildren().remove(bullet);
        bulletsLibres.add(bullet);
        bullet.getControllerBullet().init();
    }

    public Bullet addBullet() {
        System.out.println("ro " + bulletsOccupes.size() + " rl" + bulletsLibres.size());
        Bullet bullet;
        if (bulletsLibres.size() == 0) {
            bullet = new Bullet(this);
            bulletsOccupes.add(bullet);
            this.primary.getChildren().add(bullet);
        } else {
            bullet = bulletsLibres.poll();
            bulletsOccupes.add(bullet);
            this.primary.getChildren().add(bullet);
        }
        return bullet;
    }

    public void removeAdversaire(Adversaire adversaire) {
        this.primary.getChildren().remove(adversaire);
        this.primary.getChildren().remove(adversaire.getArm());
        this.primary.getChildren().remove(adversaire.getArm().getSortie());
        adversairesOccupes.removeElement(adversaire);
        adversairesLibres.add(adversaire);
        toolbarDeJeu.addAdversaire();

    }

    public void addAdversaire() {
        Adversaire adversaire = null;
        if (adversairesLibres.size() == 0) {
            adversaire = new Adversaire(this);
            adversairesOccupes.add(adversaire);
        } else {
            if (adversairesLibres.element().isActive()) {
                adversaire = adversairesLibres.poll();
                adversairesOccupes.add(adversaire);
            }
        }
        if (adversaire != null) {
            primary.getChildren().add(adversaire);
            this.primary.getChildren().add(adversaire.getArm());
            this.primary.getChildren().add(adversaire.getArm().getSortie());
        }

    }

    public void removeSoldat(Soldat soldat) {
        this.primary.getChildren().remove(soldat);
        soldat.setActive(false);
        soldatsOccupes.removeElement(soldat);
        soldatsLibres.add(soldat);
    }

    public void addSoldat() {
        Soldat soldat = null;
        if (soldatsLibres.size() == 0) {
            soldat = new Soldat(this);
        } else {
            soldat = soldatsLibres.poll();
        }
        if (soldat != null) {
            soldat.setActive(true);
            primary.getChildren().add(soldat);
            soldatsOccupes.add(soldat);
        }
    }


    public ControllerJoueur getControllerJoueurManager() {
        return controllerJoueurManager;
    }

    public Vector<Adversaire> getAdversairesOccupes() {
        return adversairesOccupes;
    }

    public Vector<Soldat> getSoldatsOccupes() {
        return soldatsOccupes;
    }

    public Zone getZone1() {
        return zone1;
    }
}