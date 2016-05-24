package io.itdraft.youfollowme.website.geometry;

public class Size {

    private final double width;
    private final double height;

    public Size(double width, double height) {
        this.width = width;
        this.height = height;
    }

    public Size scale(double scaleFactor) {
        return new Size(getWidth() * scaleFactor, getHeight() * scaleFactor);
    }

    public double getWidth() {
        return width;
    }

    public double getHeight() {
        return height;
    }
}
