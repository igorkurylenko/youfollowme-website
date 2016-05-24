package io.itdraft.youfollowme.website.geometry;

public interface HasPoints extends Iterable<Point> {
    boolean contains(Point point);

    PointsIterator iterator();
}
