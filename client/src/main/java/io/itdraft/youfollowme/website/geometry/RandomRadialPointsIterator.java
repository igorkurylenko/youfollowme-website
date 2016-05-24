package io.itdraft.youfollowme.website.geometry;

import java.util.NoSuchElementException;

import static java.lang.Math.abs;

public class RandomRadialPointsIterator extends PointsIterator {
    public static double DEFAULT_RADIUS_STEP = 35;
    public static double DEFAULT_RADIAL_STEP = 30;

    private final HasPoints shape;
    private final Point startPoint;
    private final double radiusStep;
    private final double radialStep;
    private Point nextPoint;
    private double curRadius;
    private double curRotation;
    private final double rotationOffset;
    private boolean pointWasDiscoveredForRotationCycle = false;

    public RandomRadialPointsIterator(HasPoints shape, Point startPoint) {
        this(shape, startPoint, DEFAULT_RADIUS_STEP, DEFAULT_RADIAL_STEP);
    }

    public RandomRadialPointsIterator(HasPoints shape, Point startPoint,
                                      double radiusStep, double radialStep) {
        this.shape = shape;
        this.startPoint = startPoint;
        this.radiusStep = radiusStep;
        this.radialStep = radialStep;

        curRadius = radiusStep;
        rotationOffset = Math.random() * 360;

        if (shape.contains(startPoint)) {
            nextPoint = startPoint;
        }
    }

    public boolean hasNext() {
        return discoverNextPoint() != null;
    }

    public Point next() {
        Point discoveredPoint = discoverNextPoint();

        if (discoveredPoint == null) throw new NoSuchElementException();

        nextPoint = null;

        return discoveredPoint;
    }

    private Point discoverNextPoint() {
        if (nextPoint != null) return nextPoint;

        nextPoint = discoverNextPoint(startPoint);

        return nextPoint;
    }

    private Point discoverNextPoint(Point startPoint) {
        if (abs(curRotation) >= 360 && !pointWasDiscoveredForRotationCycle) return null;

        if (abs(curRotation) >= 360) {
            onRotationCycleComplete();
        }

        double dx = Math.cos(rotationOffset + curRotation * (Math.PI / 180)) * curRadius;
        double dy = Math.sin(rotationOffset + curRotation * (Math.PI / 180)) * curRadius;

        curRotation += radialStep/(1 + Math.log(curRadius/radialStep));

        Point discoveredPoint = startPoint.move(dx, dy);

        if (shape.contains(discoveredPoint)) {
            pointWasDiscoveredForRotationCycle = true;

            return discoveredPoint;

        } else {
            return discoverNextPoint(startPoint);
        }
    }

    private void onRotationCycleComplete() {
        curRadius += radiusStep;
        curRotation = 0;
        pointWasDiscoveredForRotationCycle = false;
    }
}
