package io.itdraft.youfollowme.website.util;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.canvas.dom.client.Context2d;
import com.google.gwt.canvas.dom.client.TextMetrics;
import com.google.gwt.dom.client.Element;
import io.itdraft.youfollowme.website.geometry.Rectangle;
import io.itdraft.youfollowme.website.util.tuple.HasValue0;
import io.itdraft.youfollowme.website.util.tuple.Pair;
import io.itdraft.youfollowme.website.util.ui.Font;
import io.itdraft.youfollowme.website.util.ui.LineStyle;
import io.itdraft.youfollowme.website.util.ui.MeasuredText;

import java.util.List;

import static io.itdraft.youfollowme.website.util.CSSUnit.px;

public class CanvasUtil {

    private CanvasUtil() {
    }

    public static Rectangle getCanvasRectangle(Canvas canvas) {
        final double ratio = getCanvasHiDPIRatio(canvas.getCanvasElement());

        double width = canvas.getCoordinateSpaceWidth() / ratio;
        double height = canvas.getCoordinateSpaceHeight() / ratio;

        return new Rectangle(width, height);
    }

    public static void adjustCanvasSize(Canvas canvas, int width, int height) {
        double hiDPIRatio = getCanvasHiDPIRatio(canvas);

        adjustCanvasSize(canvas, width < 0 ? 0 : width, height < 0 ? 0 : height, hiDPIRatio);
    }

    private static void adjustCanvasSize(Canvas canvas, int width, int height, double hiDPIRatio) {
        canvas.setCoordinateSpaceWidth((int) (width * hiDPIRatio));
        canvas.setCoordinateSpaceHeight((int) (height * hiDPIRatio));

        canvas.setWidth(width + px.name());
        canvas.setHeight(height + px.name());

        canvas.getContext2d().scale(hiDPIRatio, hiDPIRatio);
    }

    private static double getCanvasHiDPIRatio(Canvas canvas) {
        return getCanvasHiDPIRatio(canvas.getCanvasElement());
    }

    private static native double getCanvasHiDPIRatio(Element canvas) /*-{
        var context = canvas.getContext('2d'),
            devicePixelRatio = $wnd.devicePixelRatio || 1,
            backingStoreRatio = context.webkitBackingStorePixelRatio ||
                context.mozBackingStorePixelRatio ||
                context.msBackingStorePixelRatio ||
                context.oBackingStorePixelRatio ||
                context.backingStorePixelRatio || 1;

        return devicePixelRatio / backingStoreRatio;
    }-*/;

    public static void clearCanvas(Canvas canvas) {
        Context2d context2d = canvas.getContext2d();
        context2d.clearRect(0, 0, canvas.getCoordinateSpaceWidth(),
                canvas.getCoordinateSpaceHeight());
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

    public static boolean intersectsAny(
            HasValue0<Rectangle> hasRectValue, List<HasValue0<Rectangle>> others) {
        for (HasValue0<Rectangle> other : others)
            if (hasRectValue.getValue0().intersects(other.getValue0()))
                return true;

        return false;
    }
}
