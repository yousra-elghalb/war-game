package modeles.image;

public class CustomImageRocket extends CustomImage {

    private static CustomImageRocket customImageRocket;

    public static CustomImageRocket getCustomImageRocket(String type, int nbImage) {
        if (customImageRocket == null)
            customImageRocket = new CustomImageRocket(type, nbImage);
        return customImageRocket;
    }

    public static CustomImageRocket getCustomImageRocket(int stage, String type, int nbImage) {
        if (customImageRocket == null)
            customImageRocket = new CustomImageRocket(stage, type, nbImage);
        return customImageRocket;
    }


    private CustomImageRocket(String type, int nbImage) {
        super(type, nbImage);
    }

    private CustomImageRocket(int stage, String type, int nbImage) {
        super(stage, type, nbImage);
    }
}