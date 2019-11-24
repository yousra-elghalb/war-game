package managers;

import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.HashSet;
import java.util.ResourceBundle;
import java.util.Vector;

public class ToolbarDeJeu implements Initializable {
    public ImageView c1;
    public ImageView c2;
    public ImageView c3;
    public ImageView c4;
    public ImageView c5;
    public ImageView c6;
    public Text nbRocket;
    public Text nbSoldat;
    public Text nbAdv;
    public Text nbSoldat2;
    private Vector<ImageView> ceours = new Vector<>();
    Image image;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        addCeours();
        image = new Image("images/like(3).png");
        nbRocket.setText("10");
    }


    public Vector<ImageView> getCeours() {
        return ceours;
    }

    public void addCeours() {
        ceours.add(c1);
        ceours.add(c2);
        ceours.add(c3);
        ceours.add(c4);
        ceours.add(c5);
        ceours.add(c6);
    }

    public void removeCeour() {
        ceours.lastElement().setImage(image);
        ceours.remove(ceours.size() - 1);
    }

    public void addAdversaire() {
        int nb = Integer.parseInt(nbAdv.getText());
        nbAdv.setText((nb + 1) + "");
    }

    public void addSoldat() {
        int nb = Integer.parseInt(nbSoldat.getText());
        nbSoldat.setText((nb + 1) + "");
    }

    public void addSoldat2() {
        int nb = Integer.parseInt(nbSoldat2.getText());
        nbSoldat2.setText((nb + 1) + "");
    }

    public void removeRocket() {
        int nb = Integer.parseInt(nbRocket.getText());
        nbRocket.setText((nb - 1) + "");
    }

    public int getNbRocket() {
        return Integer.parseInt(nbRocket.getText());
    }
}
