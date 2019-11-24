package events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import managers.Scene;

public class PressedArmKey implements EventHandler<KeyEvent> {
    private Scene scene;

    public PressedArmKey(Scene scene) {
        this.scene = scene;
    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().compareTo(KeyCode.Q) == 0) {
            scene.getJoueur().getArm().rotateToTop();
        }
        if (event.getCode().compareTo(KeyCode.D) == 0) {
            scene.getJoueur().getArm().rotateToBottom();
        }
    }
}
