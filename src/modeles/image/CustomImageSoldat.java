package modeles.image;

public class CustomImageSoldat extends CustomImage {

    private static CustomImageSoldat customImageSoldat;

    public static CustomImageSoldat getCustomImageSoldat(String type, int nbImage) {
        if (customImageSoldat == null)
            customImageSoldat = new CustomImageSoldat(type, nbImage);
        return customImageSoldat;
    }

    public static CustomImageSoldat getCustomImageSoldat(int stage, String type, int nbImage) {
        if (customImageSoldat == null)
            customImageSoldat = new CustomImageSoldat(stage, type, nbImage);
        return customImageSoldat;
    }


    private CustomImageSoldat(String type, int nbImage) {
        super(type, nbImage);
    }

    private CustomImageSoldat(int stage, String type, int nbImage) {
        super(stage, type, nbImage);
    }
}