package io.itdraft.youfollowme.website.geometry;

import java.util.ArrayList;
import java.util.List;

public class GeometryObjectsPacker<GeometryObject extends HasSize> {

    public static class PackedGeometryObject<GeometryObject> {
        private final GeometryObject value;
        private final Rectangle placeRectangle;

        public PackedGeometryObject(GeometryObject value, Rectangle placeRectangle) {
            this.value = value;
            this.placeRectangle = placeRectangle;
        }

        public GeometryObject getValue() {
            return value;
        }

        public Rectangle getPlaceRectangle() {
            return placeRectangle;
        }
    }

    public static double DEFAULT_CLOSENESS_STEP = 0.01;
    public static double DEFAULT_RADIAL_STEP = 5;
    public static double DEFAULT_MAX_DISTANCE_FACTOR = 3;

    private double closenessStep;
    private double radialStep;
    private double maxDistanceFactor;

    public GeometryObjectsPacker() {
        this(DEFAULT_CLOSENESS_STEP, DEFAULT_RADIAL_STEP, DEFAULT_MAX_DISTANCE_FACTOR);
    }

    public GeometryObjectsPacker(double closenessStep, double radialStep, double maxDistanceFactor) {
        this.closenessStep = closenessStep;
        this.radialStep = radialStep;
        this.maxDistanceFactor = maxDistanceFactor;
    }

    public List<PackedGeometryObject<GeometryObject>> pack(List<GeometryObject> geometryObjects, Rectangle boundaryRect) {
        ArrayList<PackedGeometryObject<GeometryObject>> result = new ArrayList<>();
        ArrayList<Rectangle> packedRects = new ArrayList<>();

        for (GeometryObject geometryObject : geometryObjects) {
            Rectangle rectToPack = new Rectangle(
                    boundaryRect.getCenter(), geometryObject.getSize());
            Rectangle placeRect = pack(rectToPack, packedRects, boundaryRect);

            if (placeRect == null) continue;

            packedRects.add(placeRect);
            result.add(new PackedGeometryObject<>(geometryObject, placeRect));
        }

        return result;
    }

    private Rectangle pack(Rectangle rectToPack, List<Rectangle> packedRects, Rectangle boundaryRect) {
        if (boundaryRect.includes(rectToPack) && !rectToPack.intersectsAny(packedRects)) {
            return rectToPack;
        }

        Rectangle searchRect = rectToPack;
        double size = searchRect.getDiagonalLength();
        double maxRadius = DEFAULT_MAX_DISTANCE_FACTOR * size;
        double rotationOffset = Math.random() * 360;

        for (double currentRadius = size * closenessStep;
             currentRadius < maxRadius;
             currentRadius += size * closenessStep) {
            for (float rotation = 0; rotation < 360; rotation += DEFAULT_RADIAL_STEP) {
                double dx = Math.cos(rotationOffset + rotation * (Math.PI / 180)) * currentRadius;
                double dy = Math.sin(rotationOffset + rotation * (Math.PI / 180)) * currentRadius;

                // todo: remove this class

                if (boundaryRect.includes(searchRect) && !searchRect.intersectsAny(packedRects)) {
                    return searchRect;
                }
            }
        }

        return null;
    }

    public double getClosenessStep() {
        return closenessStep;
    }

    public double getRadialStep() {
        return radialStep;
    }

    public double getMaxDistanceFactor() {
        return maxDistanceFactor;
    }

    public void setClosenessStep(double closenessStep) {
        this.closenessStep = closenessStep;
    }

    public void setRadialStep(double radialStep) {
        this.radialStep = radialStep;
    }

    public void setMaxDistanceFactor(double maxDistanceFactor) {
        this.maxDistanceFactor = maxDistanceFactor;
    }
}
