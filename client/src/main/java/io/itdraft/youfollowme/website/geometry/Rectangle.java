package io.itdraft.youfollowme.website.geometry;

import io.itdraft.youfollowme.website.util.CollectionUtil;

import java.util.List;

public class Rectangle implements HasSize, HasPoints {
    private final Point center;
    private final Size size;
    private double left;
    private double right;
    private double top;
    private double bottom;
    private LineSegment topEdgeLine;
    private LineSegment rightEdgeLine;
    private LineSegment bottomEdgeLine;
    private LineSegment leftEdgeLine;
    private double diagonalLength;

    public Rectangle(double width, double height) {
        this(new Size(width, height));
    }

    public Rectangle(Size size) {
        this(new Point(size.getWidth() / 2, size.getHeight() / 2), size);
    }

    public Rectangle(Point center, Size size) {
        this.center = center;
        this.size = size;
        left = center.getX() - size.getWidth() / 2;
        right = center.getX() + size.getWidth() / 2;
        top = center.getY() - size.getHeight() / 2;
        bottom = center.getY() + size.getHeight() / 2;
        topEdgeLine = new LineSegment(new Point(left, top), new Point(right, top));
        rightEdgeLine = new LineSegment(new Point(right, top), new Point(right, bottom));
        bottomEdgeLine = new LineSegment(new Point(left, bottom), new Point(right, bottom));
        leftEdgeLine = new LineSegment(new Point(left, top), new Point(left, bottom));
        diagonalLength = Math.sqrt(size.getWidth() * size.getWidth() + size.getHeight() * size.getHeight());
    }

    public Point getCenter() {
        return center;
    }

    public Size getSize() {
        return size;
    }

    public double getCenterX() {
        return center.getX();
    }

    public double getCenterY() {
        return center.getY();
    }

    public double getLeft() {
        return left;
    }

    public double getRight() {
        return right;
    }

    public double getTop() {
        return top;
    }

    public double getBottom() {
        return bottom;
    }

    public LineSegment getTopEdgeLine() {
        return topEdgeLine;
    }

    public LineSegment getRightEdgeLine() {
        return rightEdgeLine;
    }

    public LineSegment getBottomEdgeLine() {
        return bottomEdgeLine;
    }

    public LineSegment getLeftEdgeLine() {
        return leftEdgeLine;
    }

    public double getDiagonalLength() {
        return diagonalLength;
    }

    public double getWidth() {
        return size.getWidth();
    }

    public double getHeight() {
        return size.getHeight();
    }

    public boolean intersectsAny(List<Rectangle> others) {
        if (CollectionUtil.isNullOrEmpty(others)) return false;

        for (Rectangle other : others) {
            if (intersects(other)) return true;
        }

        return false;
    }

    public boolean intersects(Rectangle other) {
        return !(getLeft() > other.getRight() ||
                getRight() < other.getLeft() ||
                getTop() > other.getBottom() ||
                getBottom() < other.getTop());
    }

    public boolean contains(Point point) {
        double x = point.getX();
        double y = point.getY();

        return x > this.getLeft() &&
                x < this.getRight() &&
                y < this.getBottom() &&
                y > this.getTop();
    }

    public boolean includes(Rectangle rectangle) {
        return getLeft() <= rectangle.getLeft() &&
                getRight() >= rectangle.getRight() &&
                getTop() <= rectangle.getTop() &&
                getBottom() >= rectangle.getBottom();
    }

    public PointsIterator iterator() {
        return randomRadialPointsIterator();
    }

    private PointsIterator randomRadialPointsIterator() {
        return randomRadialPointsIterator(getCenter());
    }

    /**
     * @param point first point to be returned from the points iterator
     *              if it belongs to rectangle (by a call to next)
     * @return
     */
    private PointsIterator randomRadialPointsIterator(Point point) {
        return new RandomRadialPointsIterator(this, point);
    }

    public LineSegment getLineSegmentFromCenterToPoint(Point point) {
        return new LineSegment(getCenter(), point);
    }

    public LineSegment getLineSegmentFromCenterToEdge(Point throughPoint) {
        LineSegment throughLine = new LineSegment(getCenter(), throughPoint);
        LineSegment result = null;

        if (throughPoint.getX() >= getCenterX() && throughPoint.getY() <= getCenterY()) {
            result = getLineSegmentFromCenterToTopOrRightEdgeLine(throughLine);

        } else if (throughPoint.getX() >= getCenterX() && throughPoint.getY() >= getCenterY()) {
            result = getLineSegmentFromCenterToRightOrBottomEdgeLine(throughLine);

        } else if (throughPoint.getX() <= getCenterX() && throughPoint.getY() >= getCenterY()) {
            result = getLineSegmentFromCenterToLeftOrBottomEdgeLine(throughLine);

        } else if (throughPoint.getX() <= getCenterX() && throughPoint.getY() <= getCenterY()) {
            result = getLineSegmentFromCenterToLeftOrTopEdgeLine(throughLine);
        }

        return result;
    }

    private LineSegment getLineSegmentFromCenterToLeftOrTopEdgeLine(LineSegment throughLine) {
        Point leftIntersectionPoint = throughLine.getLineLineIntersection(getLeftEdgeLine());
        Point topIntersectionPoint = throughLine.getLineLineIntersection(getTopEdgeLine());

        Point boundaryIntersectionPoint = leftIntersectionPoint == null ? topIntersectionPoint :
                contains(leftIntersectionPoint) ? leftIntersectionPoint : topIntersectionPoint;

        return new LineSegment(getCenter(), boundaryIntersectionPoint);
    }

    private LineSegment getLineSegmentFromCenterToLeftOrBottomEdgeLine(LineSegment throughSegment) {
        Point leftIntersectionPoint = throughSegment.getLineLineIntersection(getLeftEdgeLine());
        Point bottomIntersectionPoint = throughSegment.getLineLineIntersection(getBottomEdgeLine());

        Point intersection = leftIntersectionPoint == null ? bottomIntersectionPoint :
                contains(leftIntersectionPoint) ? leftIntersectionPoint : bottomIntersectionPoint;

        return new LineSegment(getCenter(), intersection);
    }

    private LineSegment getLineSegmentFromCenterToRightOrBottomEdgeLine(LineSegment throughSegment) {
        Point rightIntersectionPoint = throughSegment.getLineLineIntersection(getRightEdgeLine());
        Point bottomIntersectionPoint = throughSegment.getLineLineIntersection(getBottomEdgeLine());

        Point intersection = rightIntersectionPoint == null ? bottomIntersectionPoint :
                contains(rightIntersectionPoint) ? rightIntersectionPoint : bottomIntersectionPoint;

        return new LineSegment(getCenter(), intersection);
    }

    private LineSegment getLineSegmentFromCenterToTopOrRightEdgeLine(LineSegment throughSegment) {
        Point topIntersectionPoint = throughSegment.getLineLineIntersection(getTopEdgeLine());
        Point rightIntersectionPoint = throughSegment.getLineLineIntersection(getRightEdgeLine());

        Point intersection = topIntersectionPoint == null ? rightIntersectionPoint :
                contains(topIntersectionPoint) ? topIntersectionPoint : rightIntersectionPoint;

        return new LineSegment(getCenter(), intersection);
    }
}
