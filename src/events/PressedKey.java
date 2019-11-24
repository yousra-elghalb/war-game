package events;

import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import managers.ControllerRocket;
import managers.Scene;

import java.io.IOException;
import java.util.HashSet;

public class PressedKey implements EventHandler<KeyEvent> {
    private HashSet<KeyCode> characters;
    private Scene scene;

    public PressedKey(HashSet<KeyCode> characters, Scene scene) {
        this.characters = characters;
        this.scene = scene;
    }


    @Override
    public void handle(KeyEvent event) {
//        if (event.getCode().compareTo(KeyCode.SPACE) != 0)
        if (event.getCode().compareTo(KeyCode.SPACE) == 0) {
            event.consume();
            return;
        }
        characters.add(event.getCode());
       /* if (event.getCode().compareTo(KeyCode.SPACE) == 0) {
            scene.getJoueur().getControllerJoueur().tirer();
        }*/
        if (characters.size() == 1) {
            switch (event.getCode()) {
                case UP:
                    if (scene.getZone1().getPointMin().getY() <= (scene.getJoueur().getY() - 30))
                        scene.getJoueur().deplacerY(-30);
                    break;
                case DOWN:
                    if (scene.getZone1().getPointMax().getY() >= (scene.getJoueur().getY() + 30))
                        scene.getJoueur().deplacerY(+30);
                    break;
                case LEFT:
                    if (scene.getZone1().getPointMin().getX() <= (scene.getJoueur().getX() - 30))
                        scene.getJoueur().deplacerX(-30);
                    break;
                case RIGHT:
                    if (scene.getZone1().getPointMax().getX() - scene.getJoueur().getFitWidth() >= (scene.getJoueur().getX() + 30))
                        scene.getJoueur().deplacerX(+30);
                    break;
            }
        }
    }
}
