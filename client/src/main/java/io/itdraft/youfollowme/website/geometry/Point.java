package io.itdraft.youfollowme.website.geometry;

import java.util.Objects;

public class Point {

    private final double x;
    private final double y;

    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public Point move(double dx, double dy) {
        return new Point(getX() + dx, getY() + dy);
    }

    public Point sub(Point other) {
        return new Point(getX() - other.getX(), getY() - other.getY());
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0;
    }

    public int hashCode() {
        return Objects.hash(x, y);
    }
}
