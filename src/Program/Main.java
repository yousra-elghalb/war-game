package Program;

import events.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Stage;

import java.util.HashSet;

public class Main extends Application {
    private HashSet<KeyCode> characters = new HashSet<>();
    public static managers.Scene sceneDeJeu;

    @Override
    public void start(Stage primaryStage) throws Exception {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/Scene.fxml"));
//        Parent root = FXMLLoader.load(getClass().getResource("../views/Scene.fxml"));


        Parent root = (Parent) loader.load();
        sceneDeJeu = loader.getController();

        primaryStage.setTitle("Scene");
        Scene scene = new Scene(root, 1300, 900);


        scene.addEventHandler(KeyEvent.KEY_RELEASED, new ReleaserKey(characters, sceneDeJeu));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new PressedKey(characters, sceneDeJeu));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new PressedTowKey(characters, sceneDeJeu));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new PressedTireKey(sceneDeJeu));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new PressedArmKey(sceneDeJeu));
        scene.addEventHandler(KeyEvent.KEY_PRESSED, new PressedTireBulletKey(sceneDeJeu));

        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
        primaryStage.resizableProperty().addListener((observable, oldValue, newValue) -> {

        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
