package io.itdraft.youfollowme.website.geometry;

import java.util.Iterator;

public abstract class PointsIterator implements Iterator<Point> {
    public void remove() {
        throw new UnsupportedOperationException();
    }
}
