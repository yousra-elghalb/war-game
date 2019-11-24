package modeles.image;

public class CustomImageJoueur extends CustomImage {

    private static CustomImageJoueur customImageJoueur;

    public static CustomImageJoueur getCustomImageJoueur(String type, int nbImage) {
        if (customImageJoueur == null)
            customImageJoueur = new CustomImageJoueur(type, nbImage);
        return customImageJoueur;
    }

    public static CustomImageJoueur getCustomImageJoueur(int stage, String type, int nbImage) {
        if (customImageJoueur == null)
            customImageJoueur = new CustomImageJoueur(stage, type, nbImage);
        return customImageJoueur;
    }


    private CustomImageJoueur(String type, int nbImage) {
        super(type, nbImage);
    }

    private CustomImageJoueur(int stage, String type, int nbImage) {
        super(stage, type, nbImage);
    }
}