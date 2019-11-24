package events;

import javafx.event.EventHandler;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import managers.Scene;

public class PressedTireBulletKey implements EventHandler<KeyEvent> {
    private Scene scene;

    public PressedTireBulletKey(Scene scene) {
        this.scene = scene;
    }


    @Override
    public void handle(KeyEvent event) {
        if (event.getCode().compareTo(KeyCode.S) == 0) {
            if (scene.getJoueur().isActive())
                scene.getJoueur().getControllerJoueur().tirerBullet();
        }
    }
}
