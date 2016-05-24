package io.itdraft.youfollowme.website.geometry;

public class Vector2D implements Vector {
    private final double dX;
    private final double dY;

    public Vector2D() {
        this(0, 0);
    }

    public Vector2D(Point point) {
        this(point.getX(), point.getY());
    }

    public Vector2D(double dX, double dY) {
        this.dX = dX;
        this.dY = dY;
    }

    public double getDx() {
        return dX;
    }

    public double getDy() {
        return dY;
    }

    public double length() {
        return Math.sqrt(getDx() * getDx() + getDy() * getDy());
    }

    public Vector2D add(Vector2D other) {
        return new Vector2D(getDx() + other.getDx(), getDy() + other.getDy());
    }

    public Vector2D sub(Vector2D other) {
        return new Vector2D(getDx() - other.getDx(), getDy() - other.getDy());
    }

    public Vector2D scale(double scaleFactor) {
        return new Vector2D(getDx() * scaleFactor, getDy() * scaleFactor);
    }

    public Vector2D normalize() {
        double length = length();

        return length != 0 ? new Vector2D(getDx() / length, getDy() / length) : this;
    }

    public double dotProduct(Vector2D other) {
        return getDx() * other.getDx() + getDy() * other.getDy();
    }

    public String toString() {
        return new StringBuilder().append("(").append(getDx()).append(", ")
                .append(getDy()).append(")").toString();
    }
}
