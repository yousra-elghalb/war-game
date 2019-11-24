package events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import managers.Scene;

import java.util.HashSet;

public class PressedTowKey implements EventHandler<KeyEvent> {
    private HashSet<KeyCode> characters;
    private Scene scene;

    public PressedTowKey(HashSet<KeyCode> characters, Scene scene) {
        this.characters = characters;
        this.scene = scene;
    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().compareTo(KeyCode.SPACE) != 0)
            characters.add(event.getCode());

        if (characters.size() > 1) {
            if (characters.contains(KeyCode.UP) && characters.contains(KeyCode.RIGHT)) {
                if (scene.getZone1().getPointMin().getY() <= (scene.getJoueur().getY() - 30)
                        && scene.getZone1().getPointMax().getX() - scene.getJoueur().getFitWidth() >= (scene.getJoueur().getX() + 30))
                    scene.getJoueur().deplacerXY(+30, -30);
            } else if (characters.contains(KeyCode.UP) && characters.contains(KeyCode.LEFT)) {
                if (scene.getZone1().getPointMin().getY() <= (scene.getJoueur().getY() - 30)
                        && scene.getZone1().getPointMin().getX() <= (scene.getJoueur().getX() - 30))
                    scene.getJoueur().deplacerXY(-30, -30);
            } else if (characters.contains(KeyCode.DOWN) && characters.contains(KeyCode.RIGHT)) {
                if (scene.getZone1().getPointMax().getY() >= (scene.getJoueur().getY() + 30)
                        && scene.getZone1().getPointMax().getX() - scene.getJoueur().getFitWidth() >= (scene.getJoueur().getX() + 30))
                    scene.getJoueur().deplacerXY(+30, +30);
            } else if (characters.contains(KeyCode.DOWN) && characters.contains(KeyCode.LEFT)) {
                if (scene.getZone1().getPointMax().getY() >= (scene.getJoueur().getY() + 30)
                        && scene.getZone1().getPointMin().getX() <= (scene.getJoueur().getX() - 30))
                    scene.getJoueur().deplacerXY(-30, +30);
            }
        }
    }
}
