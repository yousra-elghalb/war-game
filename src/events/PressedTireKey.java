package events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import managers.Scene;

import java.util.HashSet;

public class PressedTireKey implements EventHandler<KeyEvent> {
    private Scene scene;

    public PressedTireKey(Scene scene) {
        this.scene = scene;
    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().compareTo(KeyCode.SPACE) == 0) {
            if (scene.getJoueur().isActive())
                scene.getJoueur().getControllerJoueur().tirer();
        }
    }
}
