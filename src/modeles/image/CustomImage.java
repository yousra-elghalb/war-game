package modeles.image;

import javafx.scene.image.Image;

import java.util.Vector;

public abstract class CustomImage {

    protected Vector<Image> images;
    protected int stage;
    protected String type;
    protected int nbImage;

    protected CustomImage(String type, int nbImage) {
        this.stage = 1;
        this.type = type;
        this.nbImage = nbImage;
        addImages();
    }

    protected CustomImage(int stage, String type, int nbImage) {
        this.stage = stage;
        this.type = type;
        this.nbImage = nbImage;
        addImages();
    }


    private void addImages() {
        images = new Vector<>();
        for (int i = 1; i <= nbImage; i++) {
//            System.out.println("images/stage" + stage + "/" + type + "/" + type + "" + i + ".png");
            images.add(new Image("images/stage" + stage + "/" + type + "/" + type + "" + i + ".png"));
        }
    }

    public Vector<Image> getImages() {
        return images;
    }

    public void setImages(Vector<Image> images) {
        this.images = images;
    }

    public int getStage() {
        return stage;
    }

    public void setStage(int stage) {
        this.stage = stage;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getNbImage() {
        return nbImage;
    }

    public void setNbImage(int nbImage) {
        this.nbImage = nbImage;
    }
}
