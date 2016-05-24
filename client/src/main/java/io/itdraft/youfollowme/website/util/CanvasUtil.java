package io.itdraft.youfollowme.website.util;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.TextMetrics;
import io.itdraft.youfollowme.website.util.ui.Font;
import io.itdraft.youfollowme.website.util.ui.LineStyle;
import io.itdraft.youfollowme.website.util.ui.MeasuredText;
import io.itdraft.youfollowme.website.geometry.Point;
import io.itdraft.youfollowme.website.geometry.Rectangle;
import io.itdraft.youfollowme.website.geometry.Size;
import io.itdraft.youfollowme.website.util.tuple.HasValue0;
import io.itdraft.youfollowme.website.util.tuple.Pair;

import java.util.List;

public class CanvasUtil {
    public static Rectangle getCanvasRectangle(Canvas canvas) {
        final Size size = new Size(canvas.getCoordinateSpaceWidth(),
                canvas.getCoordinateSpaceHeight());

        return new Rectangle(size);
    }

    public static void clearCanvas(Canvas canvas) {
        Context2d context2d = canvas.getContext2d();
        context2d.clearRect(0, 0, canvas.getCoordinateSpaceWidth(),
                canvas.getCoordinateSpaceHeight());
    }

    public static void adjustCanvasSize(Canvas canvas, int width, int height) {
        canvas.setWidth(String.valueOf(width) + CSSUnit.px.name());
        canvas.setHeight(String.valueOf(height) + CSSUnit.px.name());

        canvas.setCoordinateSpaceWidth(width);
        canvas.setCoordinateSpaceHeight(height);
    }

    public static void displayText(Canvas canvas, Pair<Rectangle, MeasuredText> textDisplayInfo) {
        displayText(canvas.getContext2d(), textDisplayInfo);
    }

    public static void displayText(Context2d ctx, Pair<Rectangle, MeasuredText> textDisplayInfo) {
        Rectangle placeRect = textDisplayInfo.getValue0();
        MeasuredText measuredText = textDisplayInfo.getValue1();
        Font font = measuredText.getFont();
        String color = font.getColor();

        ctx.setFillStyle(color);
        ctx.setFont(measuredText.getFont().toStringForCanvasFontProperty());
        ctx.setTextBaseline(Context2d.TextBaseline.MIDDLE);
        ctx.setTextAlign(Context2d.TextAlign.CENTER);
        ctx.fillText(measuredText.getText(), placeRect.getCenterX(), placeRect.getCenterY());
    }

    public static void underlineText(Canvas canvas, Pair<Rectangle, MeasuredText> textDisplayInfo,
                                     LineStyle underlineLineStyle) {
        underlineText(canvas.getContext2d(), textDisplayInfo, underlineLineStyle);
    }

    public static void underlineText(Context2d ctx, Pair<Rectangle, MeasuredText> textDisplayInfo,
                                     LineStyle underlineLineStyle) {
        Rectangle textPlaceRect = textDisplayInfo.getValue0();
        MeasuredText measuredText = textDisplayInfo.getValue1();
        Rectangle textRect = new Rectangle(textPlaceRect.getCenter(), measuredText.getSize());

        double x = textRect.getLeft();
        double y = textRect.getBottom() - 2;

        ctx.beginPath();
        ctx.setStrokeStyle(underlineLineStyle.getColor());
        ctx.setLineWidth(underlineLineStyle.getWidth());
        ctx.moveTo(x, y);
        ctx.lineTo(x + measuredText.getWidth(), y);
        ctx.stroke();
    }

    public static MeasuredText measureText(Canvas canvas, String text, Font font) {
        return measureText(canvas.getContext2d(), text, font);
    }

    public static MeasuredText measureText(Context2d context2d, String text, Font font) {
        context2d.setFont(font.toStringForCanvasFontProperty());
        TextMetrics textMetrics = context2d.measureText(text);

        return new MeasuredText(text, font, textMetrics);
    }

    private CanvasUtil() {
    }

    public static void drawPoint(Canvas canvas, Point point) {
        drawPoint(canvas.getContext2d(), point);
    }

    public static void drawPoint(Context2d ctx, Point point) {
        ctx.setFillStyle("#f2f2f2");
        ctx.fillRect(point.getX(), point.getY(), 1, 1);
    }

    public static boolean intersectsAny(
            HasValue0<Rectangle> hasRectValue, List<HasValue0<Rectangle>> others) {
        for (HasValue0<Rectangle> other : others)
            if (hasRectValue.getValue0().intersects(other.getValue0()))
                return true;

        return false;
    }
}
