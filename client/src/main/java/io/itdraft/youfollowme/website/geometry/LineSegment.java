package io.itdraft.youfollowme.website.geometry;

public class LineSegment {

    private final Point start;
    private final Point end;

    public LineSegment(final Point start, final Point end) {
        assert start != null;
        assert end != null;

        this.start = start;
        this.end = end;
    }

    public Point getStart() {
        return start;
    }

    public Point getEnd() {
        return end;
    }

    public double getX1() {
        return start.getX();
    }

    public double getX2() {
        return end.getX();
    }

    public double getY1() {
        return start.getY();
    }

    public double getY2() {
        return end.getY();
    }

    public Point getLineLineIntersection(final LineSegment other) {
        return getLineLineIntersection(getX1(), getY1(), getX2(), getY2(),
                other.getX1(), other.getY1(), other.getX2(), other.getY2());
    }

    public double length() {
        return Math.sqrt((getX2() - getX1()) * (getX2() - getX1()) +
                (getY2() - getY1()) * (getY2() - getY1()));
    }

    private Point getLineLineIntersection(double x1, double y1, double x2, double y2,
                                          double x3, double y3, double x4, double y4) {
        double d = (x1-x2)*(y3-y4) - (y1-y2)*(x3-x4);

        if (d == 0) return null;

        double xi = ((x3-x4)*(x1*y2-y1*x2)-(x1-x2)*(x3*y4-y3*x4))/d;
        double yi = ((y3-y4)*(x1*y2-y1*x2)-(y1-y2)*(x3*y4-y3*x4))/d;

        return new Point(xi,yi);
    }

}
