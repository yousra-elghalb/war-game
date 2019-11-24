package modeles.image;

public class CustomImageArm extends CustomImage {

    private static CustomImageArm customImageArm;

    public static CustomImageArm getCustomImageArm(String type, int nbImage) {
        if (customImageArm == null)
            customImageArm = new CustomImageArm(type, nbImage);
        return customImageArm;
    }

    public static CustomImageArm getCustomImageArm(int stage, String type, int nbImage) {
        if (customImageArm == null)
            customImageArm = new CustomImageArm(stage, type, nbImage);
        return customImageArm;
    }


    private CustomImageArm(String type, int nbImage) {
        super(type, nbImage);
    }

    private CustomImageArm(int stage, String type, int nbImage) {
        super(stage, type, nbImage);
    }
}