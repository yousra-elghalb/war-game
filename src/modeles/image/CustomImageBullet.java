package modeles.image;

public class CustomImageBullet extends CustomImage {

    private static CustomImageBullet customImageBullet;

    public static CustomImageBullet getCustomImageBullet(String type, int nbImage) {
        if (customImageBullet == null)
            customImageBullet = new CustomImageBullet(type, nbImage);
        return customImageBullet;
    }

    public static CustomImageBullet getCustomImageBullet(int stage, String type, int nbImage) {
        if (customImageBullet == null)
            customImageBullet = new CustomImageBullet(stage, type, nbImage);
        return customImageBullet;
    }


    private CustomImageBullet(String type, int nbImage) {
        super(type, nbImage);
    }

    private CustomImageBullet(int stage, String type, int nbImage) {
        super(stage, type, nbImage);
    }
}