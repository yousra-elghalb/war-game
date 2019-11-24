package modeles.image;

public class CustomImageBomb extends CustomImage {

    private static CustomImageBomb customImageBomb;

    public static CustomImageBomb getCustomImageBomb(String type, int nbImage) {
        if (customImageBomb == null)
            customImageBomb = new CustomImageBomb(type, nbImage);
        return customImageBomb;
    }

    public static CustomImageBomb getCustomImageBomb(int stage, String type, int nbImage) {
        if (customImageBomb == null)
            customImageBomb = new CustomImageBomb(stage, type, nbImage);
        return customImageBomb;
    }


    private CustomImageBomb(String type, int nbImage) {
        super(type, nbImage);
    }

    private CustomImageBomb(int stage, String type, int nbImage) {
        super(stage, type, nbImage);
    }
}