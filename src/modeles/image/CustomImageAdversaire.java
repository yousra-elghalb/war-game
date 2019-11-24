package modeles.image;

public class CustomImageAdversaire extends CustomImage {

    private static CustomImageAdversaire customImageAdversaire;

    public static CustomImageAdversaire getCustomImageAdversaire(String type, int nbImage) {
        if (customImageAdversaire == null)
            customImageAdversaire = new CustomImageAdversaire(type, nbImage);
        return customImageAdversaire;
    }

    public static CustomImageAdversaire getCustomImageAdversaire(int stage, String type, int nbImage) {
        if (customImageAdversaire == null)
            customImageAdversaire = new CustomImageAdversaire(stage, type, nbImage);
        return customImageAdversaire;
    }


    private CustomImageAdversaire(String type, int nbImage) {
        super(type, nbImage);
    }

    private CustomImageAdversaire(int stage, String type, int nbImage) {
        super(stage, type, nbImage);
    }
}