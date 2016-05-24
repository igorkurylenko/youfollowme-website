package io.itdraft.youfollowme.website.application.domain;

import com.google.gwt.canvas.client.Canvas;
import com.google.gwt.dom.client.Style;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.MouseMoveEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import io.itdraft.youfollowme.website.application.BaseTrendsViewWithUiHandlers;
import io.itdraft.youfollowme.website.util.ui.MeasuredText;
import io.itdraft.youfollowme.website.dto.DomainTrend;
import io.itdraft.youfollowme.website.dto.Trend;
import io.itdraft.youfollowme.website.geometry.Point;
import io.itdraft.youfollowme.website.geometry.Rectangle;
import io.itdraft.youfollowme.website.geometry.Size;
import io.itdraft.youfollowme.website.resources.AppConstants;
import io.itdraft.youfollowme.website.util.tuple.Triplet;

import javax.inject.Inject;

public class DomainTrendsView extends BaseTrendsViewWithUiHandlers<DomainsUIHandlers> implements DomainTrendsPresenter.MyView {

    interface Binder extends UiBinder<Widget, DomainTrendsView> {
    }

    @UiField
    HTMLPanel rootPanel;

    @UiField(provided = true)
    Canvas canvas;

    @Inject
    DomainTrendsView(Binder binder, AppConstants appConstants) {
        super(appConstants);

        this.canvas = getCanvasIfSupported();

        initWidget(binder.createAndBindUi(this));
    }

    public Canvas getCanvasIfSupported() {
        if (canvas == null) canvas = Canvas.createIfSupported();

        return canvas;
    }

    @UiHandler("canvas")
    void handleCanvasClick(ClickEvent event) {
        int x = event.getRelativeX(canvas.getElement());
        int y = event.getRelativeY(canvas.getElement());

        handleCanvasClick(x, y);
    }

    private void handleCanvasClick(int x, int y) {
        DomainTrend trend = getTrendInCanvasPoint(x, y);

        if (trend != null) {
            getUiHandlers().onTrendSelected(trend);
        }
    }

    @UiHandler("canvas")
    void handleCanvasMouseMove(MouseMoveEvent event) {
        int x = event.getRelativeX(canvas.getElement());
        int y = event.getRelativeY(canvas.getElement());

        handleCanvasMouseMove(x, y);
    }

    private void handleCanvasMouseMove(int x, int y) {
        DomainTrend trend = getTrendInCanvasPoint(x, y);
        Style canvasStyle = canvas.getElement().getStyle();
        canvasStyle.setCursor(trend != null ? Style.Cursor.POINTER : Style.Cursor.DEFAULT);
    }


    private DomainTrend getTrendInCanvasPoint(int x, int y) {
        return getTrendInCanvasPoint(new Point(x, y));
    }

    private DomainTrend getTrendInCanvasPoint(Point point) {
        for (Triplet<Rectangle, MeasuredText, ? super Trend> displayedTrend : displayedTrends) {
            Rectangle placeRectangle = displayedTrend.getValue0();
            Size textSize = displayedTrend.getValue1().getSize();
            Rectangle textRectangle = new Rectangle(placeRectangle.getCenter(), textSize);

            if (textRectangle.contains(point)) return (DomainTrend) displayedTrend.getValue2();
        }

        return null;
    }
}
