package events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import managers.Scene;

import java.util.HashSet;

public class ReleaserKey implements EventHandler<KeyEvent> {
    private HashSet<KeyCode> characters;
    private Scene joueur;

    public ReleaserKey(HashSet<KeyCode> characters, Scene joueur) {
        this.characters = characters;
        this.joueur = joueur;
    }

    @Override
    public void handle(KeyEvent event) {
        characters.remove(event.getCode());
        if (event.getCode().compareTo(KeyCode.SPACE) == 0) {
//            joueur.getControllerJoueurManager().setType("");
        }

    }
}
