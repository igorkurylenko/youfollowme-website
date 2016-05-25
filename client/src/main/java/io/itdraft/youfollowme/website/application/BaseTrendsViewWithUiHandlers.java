package io.itdraft.youfollowme.website.application;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.user.client.ui.RootPanel;
import com.gwtplatform.mvp.client.UiHandlers;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import io.itdraft.youfollowme.website.dto.Trend;
import io.itdraft.youfollowme.website.geometry.LineSegment;
import io.itdraft.youfollowme.website.geometry.Point;
import io.itdraft.youfollowme.website.geometry.Rectangle;
import io.itdraft.youfollowme.website.resources.AppConstants;
import io.itdraft.youfollowme.website.util.CanvasUtil;
import io.itdraft.youfollowme.website.util.ColorUtil;
import io.itdraft.youfollowme.website.util.async.IteratorCallback;
import io.itdraft.youfollowme.website.util.tuple.HasValue0;
import io.itdraft.youfollowme.website.util.tuple.Pair;
import io.itdraft.youfollowme.website.util.tuple.Triplet;
import io.itdraft.youfollowme.website.util.ui.Font;
import io.itdraft.youfollowme.website.util.ui.LineStyle;
import io.itdraft.youfollowme.website.util.ui.MeasuredText;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static io.itdraft.youfollowme.website.util.CanvasUtil.*;
import static io.itdraft.youfollowme.website.util.async.AsyncUtil.foreachIncremental;
import static java.lang.Math.*;

public abstract class BaseTrendsViewWithUiHandlers<C extends UiHandlers> extends ViewWithUiHandlers<C> implements BaseTrendsPresenter.TrendsView {

    private final AppConstants appConstants;
    protected List<Triplet<Rectangle, MeasuredText, ? super Trend>> displayedTrends = new ArrayList<>();
    private DisplayMode displayMode = new NoDisplayMode();

    public BaseTrendsViewWithUiHandlers(AppConstants appConstants) {
        this.appConstants = appConstants;
    }

    public void setDisplayMode(TrendsDisplayMode displayMode) {
        switch (displayMode) {
            case Heap:
                if (!isCanvasSupported()) {
                    throw new UnsupportedOperationException(
                            "Your browser does not meet the minimum requirements for Canvas.");
                }

                this.displayMode = new HeapOnCanvasDisplayMode();
                break;

            case List:
                this.displayMode = new ListDisplayMode();
                break;

            case NoDisplay:
                this.displayMode = new NoDisplayMode();
                break;

            default:
                throw new UnsupportedOperationException(
                        displayMode.name() + " trends display mode is not implemented yet.");
        }
    }

    public boolean isCanvasSupported() {
        return getCanvasIfSupported() != null;
    }

    public void displayTrends(List<? extends Trend> trends) {
        displayMode.displayTrends(trends);
    }

    protected abstract Canvas getCanvasIfSupported();

    public void adjustDisplaySize() {
        displayMode.adjustDisplaySize();
    }

    public void clearDisplay() {
        displayMode.clearDisplay();
    }

    private interface DisplayMode {
        void displayTrends(List<? extends Trend> trends);

        void adjustDisplaySize();

        void clearDisplay();
    }

    private class HeapOnCanvasDisplayMode implements DisplayMode {
        static final int MAX_FONT_SIZE = 72;
        static final int MIN_FONT_SIZE = 19;
        static final double MIN_FONT_COLOR_ALPHA = 0.45;
        static final int DISPLAY_OFFSET_TOP = 100;
        static final int DISPLAY_OFFSET_BOTTOM = 50;
        static final double CANVAS_PLACE_RECT_SCALE_FACTOR = 1.03;

        private final Canvas canvas;
        private Rectangle canvasRect;
        private double trendsMinWeight;
        private double trendsMaxWeight;
        private Set<? super Trend> trendsAreBeingDisplayed = new HashSet<>();

        public HeapOnCanvasDisplayMode() {
            canvas = getCanvasIfSupported();

            assert canvas != null;

            resetCanvasRect();
        }

        private void resetCanvasRect() {
            canvasRect = getCanvasRectangle(canvas);
        }

        public void displayTrends(List<? extends Trend> trends) {
            clearDisplay();

            resetTrendsMinMaxWeights(trends);

            triggerDisplayTrends(trends);
        }

        public void adjustDisplaySize() {
            int rootPanelWidth = RootPanel.get().getElement().getClientWidth();
            int rootPanelHeight = RootPanel.get().getElement().getClientHeight();
            int top = getDisplayOffsetTop();
            int bottom = getDisplayOffsetBottom();

            CanvasUtil.adjustCanvasSize(canvas, rootPanelWidth, rootPanelHeight - top - bottom);

            resetCanvasRect();
        }

        public void clearDisplay() {
            if (trendsAreBeingDisplayed.isEmpty() && displayedTrends.isEmpty()) return;

            trendsAreBeingDisplayed.clear();

            displayedTrends.clear();

            clearCanvas(canvas);
        }

        private void resetTrendsMinMaxWeights(List<? extends Trend> trends) {
            trendsMinWeight = trends.get(0).getWeight();
            trendsMaxWeight = trendsMinWeight;

            for (Trend trend : trends) {
                trendsMinWeight = Math.min(trendsMinWeight, trend.getWeight());
                trendsMaxWeight = Math.max(trendsMaxWeight, trend.getWeight());
            }
        }

        private void triggerDisplayTrends(List<? extends Trend> trends) {
            trendsAreBeingDisplayed.addAll(trends);

            foreachIncremental(trends, new IteratorCallback<Trend>() {
                public boolean f(Trend trend) {
                    displayTrend(trend);
                    return true;
                }
            });
        }

        private void displayTrend(Trend trend) {
            Triplet<Rectangle, MeasuredText, Trend> trendDisplayInfo = findDisplayPlace(trend);

            trendsAreBeingDisplayed.remove(trend);

            if (trendDisplayInfo != null) {
                displayTrend(trendDisplayInfo);

                displayedTrends.add(trendDisplayInfo);
            }
        }

        private Triplet<Rectangle, MeasuredText, Trend> findDisplayPlace(Trend trend) {
            final double normTrendWeight = getNormalizedTrendWeight(trend);

            for (Point point : canvasRect) {
                if (!trendsAreBeingDisplayed.contains(trend)) return null;

                Triplet<Rectangle, MeasuredText, Trend> trendDisplayInfo =
                        calcTrendDisplayInfo(trend, point, normTrendWeight);
                //noinspection unchecked
                boolean displayPlaceIsFound = !intersectsAny(
                        trendDisplayInfo, (List<HasValue0<Rectangle>>) (List<?>) displayedTrends) &&
                        canvasRect.includes(trendDisplayInfo.getValue0());

                if (displayPlaceIsFound) return trendDisplayInfo;
            }

            return null;
        }

        private void displayTrend(Triplet<Rectangle, MeasuredText, Trend> trendDisplayInfo) {
            Rectangle placeRect = trendDisplayInfo.getValue0();
            MeasuredText measuredText = trendDisplayInfo.getValue1();

            displayText(Pair.with(placeRect, measuredText));
        }

        private void displayText(Pair<Rectangle, MeasuredText> textDisplayInfo) {
            CanvasUtil.displayText(canvas, textDisplayInfo);
            CanvasUtil.underlineText(canvas, textDisplayInfo,
                    getUnderlineLineStyle(textDisplayInfo));
        }

        private LineStyle getUnderlineLineStyle(Pair<Rectangle, MeasuredText> textDisplayInfo) {
            MeasuredText measuredText = textDisplayInfo.getValue1();
            String color = measuredText.getFont().getColor();

            return getUnderlineLineStyle(color);
        }

        private Triplet<Rectangle, MeasuredText, Trend> calcTrendDisplayInfo(
                Trend trend, Point displayPoint, double normTrendWeight) {
            double placeWeight = calcPlaceWeight(displayPoint);
            MeasuredText measuredTrendText = measureText(
                    trend.toString().replaceFirst("www\\.", ""), normTrendWeight, placeWeight);
            Rectangle placeRect = new Rectangle(displayPoint,
                    measuredTrendText.getSize().scale(CANVAS_PLACE_RECT_SCALE_FACTOR));

            return Triplet.with(placeRect, measuredTrendText, trend);
        }

        private MeasuredText measureText(String text, double normTrendWeight, double placeWeight) {
            Font font = calcFont(placeWeight, normTrendWeight);

            return CanvasUtil.measureText(canvas, text, font);
        }

        private Font calcFont(double placeWeight, double normTrendWeight) {
            int fontSize = calcFontSize(placeWeight, normTrendWeight);
            String fontColor = calcFontColor(fontSize);

            return new Font(fontSize, getFontFamily(), fontColor);
        }

        private String calcFontColor(int fontSize) {
            return ColorUtil.rgbaWhite(calcFontColorAlpha(fontSize));
        }

        private int calcFontSize(double placeWeight, double normTrendWeight) {
            return calcFontSizeByPlaceWeight(placeWeight) +
                    calcFontSizeByNormalizedTrendWeight(normTrendWeight);
        }

        private int calcFontSizeByPlaceWeight(double placeWeight) {
            return (int) max(round(placeWeight * getMaxFontSize() / 2), getMinFontSize());
        }

        private int calcFontSizeByNormalizedTrendWeight(double normTrendWeight) {
            return (int) round(normTrendWeight * getMaxFontSize() / 2);
        }

        private double calcPlaceWeight(Point placePoint) {
            if (placePoint.equals(canvasRect.getCenter())) return 1;

            LineSegment fromCenterToPlace = canvasRect.getLineSegmentFromCenterToPoint(placePoint);
            LineSegment fromCenterToEdge = canvasRect.getLineSegmentFromCenterToEdge(placePoint);

            return 1 - fromCenterToPlace.length() / fromCenterToEdge.length();
        }

        private double getNormalizedTrendWeight(Trend trend) {
            double trendWeight = trend.getWeight();

            return getNormalizedTrendWeight(trendWeight);
        }

        private double getNormalizedTrendWeight(double trendWeight) {
            return trendsMaxWeight == trendsMinWeight ? 1 :
                    (trendWeight - trendsMinWeight) / (trendsMaxWeight - trendsMinWeight);
        }

        private double calcFontColorAlpha(double fontSize) {
            return sqrt(fontSize / getMaxFontSize() + getMinFontColorAlpha());
        }

        private String getFontFamily() {
            return appConstants.fontFamily();
        }

        private int getMaxFontSize() {
            return MAX_FONT_SIZE;
        }

        private int getMinFontSize() {
            return MIN_FONT_SIZE;
        }

        private LineStyle getUnderlineLineStyle(String color) {
            return new LineStyle(color, 1);
        }

        private int getDisplayOffsetTop() {
            return DISPLAY_OFFSET_TOP;
        }

        private int getDisplayOffsetBottom() {
            return DISPLAY_OFFSET_BOTTOM;
        }

        private double getMinFontColorAlpha() {
            return MIN_FONT_COLOR_ALPHA;
        }
    }

    private class ListDisplayMode implements DisplayMode {
        public void displayTrends(List<? extends Trend> trends) {
            throw new UnsupportedOperationException("Not implemented yet.");
        }

        public void adjustDisplaySize() {
            throw new UnsupportedOperationException("Not implemented yet.");
        }

        public void clearDisplay() {
            throw new UnsupportedOperationException("Not implemented yet.");
        }
    }

    private class NoDisplayMode implements DisplayMode {
        public void displayTrends(List<? extends Trend> trends) {
        }

        public void adjustDisplaySize() {
        }

        public void clearDisplay() {
        }
    }
}
