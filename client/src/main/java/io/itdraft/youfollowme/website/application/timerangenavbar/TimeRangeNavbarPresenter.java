package io.itdraft.youfollowme.website.application.timerangenavbar;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import io.itdraft.youfollowme.website.application.event.PauseCategoryPresentationEvent;
import io.itdraft.youfollowme.website.application.event.PauseTimeRangePresentationEvent;
import io.itdraft.youfollowme.website.application.event.PlayTimeRangePresentationEvent;
import io.itdraft.youfollowme.website.place.PlaceParams;
import io.itdraft.youfollowme.website.util.PlaceUtil;

import static io.itdraft.youfollowme.website.util.PlaceUtil.getTimeRangeParamValue;

public class TimeRangeNavbarPresenter extends PresenterWidget<TimeRangeNavbarPresenter.MyView>
        implements TimeRangeNavbarUIHandlers,
        PlayTimeRangePresentationEvent.PlayTimeRangePresentationHandler,
        PauseTimeRangePresentationEvent.PauseTimeRangePresentationHandler {

    private final PlaceManager placeManager;

    public interface MyView extends View, HasUiHandlers<TimeRangeNavbarUIHandlers> {
        void setHourButtonHighlighted(boolean highlighted);

        void setDayButtonHighlighted(boolean highlighted);

        void setWeekButtonHighlighted(boolean highlighted);

        void setMonthButtonHighlighted(boolean highlighted);

        void setPlayButtonVisible(boolean visible);

        void setPauseButtonVisible(boolean visible);
    }

    @Inject
    public TimeRangeNavbarPresenter(EventBus eventBus, MyView view, PlaceManager placeManager) {
        super(eventBus, view);

        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getEventBus().addHandler(PlayTimeRangePresentationEvent.getType(), this));
        registerHandler(getEventBus().addHandler(PauseTimeRangePresentationEvent.getType(), this));
    }

    @Override
    protected void onReset() {
        super.onReset();

        adjustHighlight();
    }

    @Override
    public void onHourButtonClicked() {
        revealCurrentPlaceWithTimeRange(PlaceParams.TimeRange.hour);

        pauseEveryPresentation();
    }

    @Override
    public void onDayButtonClicked() {
        revealCurrentPlaceWithTimeRange(PlaceParams.TimeRange.day);

        pauseEveryPresentation();
    }

    @Override
    public void onWeekButtonClicked() {
        revealCurrentPlaceWithTimeRange(PlaceParams.TimeRange.week);

        pauseEveryPresentation();
    }

    @Override
    public void onMonthButtonClicked() {
        revealCurrentPlaceWithTimeRange(PlaceParams.TimeRange.month);

        pauseEveryPresentation();
    }

    @Override
    public void onPlayButtonClicked() {
        PlayTimeRangePresentationEvent.fire(this);
    }

    @Override
    public void onPauseButtonClicked() {
        PauseTimeRangePresentationEvent.fire(this);
    }

    @Override
    public void onPlayTimeRangePresentationEvent(PlayTimeRangePresentationEvent event) {
        getView().setPlayButtonVisible(false);
        getView().setPauseButtonVisible(true);
    }

    @Override
    public void onPauseTimeRangePresentationEvent(PauseTimeRangePresentationEvent event) {
        getView().setPlayButtonVisible(true);
        getView().setPauseButtonVisible(false);
    }

    private void pauseEveryPresentation() {
        PauseCategoryPresentationEvent.fire(this);
        PauseTimeRangePresentationEvent.fire(this);
    }

    private void revealCurrentPlaceWithTimeRange(PlaceParams.TimeRange timeRange) {
        String currentNameToken = placeManager.getCurrentPlaceRequest().getNameToken();
        PlaceRequest responsePlaceRequest = new PlaceRequest.Builder()
                .nameToken(currentNameToken)
                .with(PlaceParams.TimeRange.PARAM_NAME, timeRange.name())
                .build();

        placeManager.revealPlace(responsePlaceRequest);
    }

    private void adjustHighlight() {
        PlaceRequest curPlaceRequest = placeManager.getCurrentPlaceRequest();
        PlaceParams.TimeRange timeRange = PlaceUtil.getTimeRangeParamValue(curPlaceRequest);

        switch (timeRange) {
            case hour:
                highlightHourButton();
                break;

            case day:
                highlightDayButton();
                break;

            case week:
                highlightWeekButton();
                break;

            case month:
                highlightMonthButton();
                break;

            default:
                break;
        }
    }

    private void highlightHourButton() {
        getView().setHourButtonHighlighted(true);
        getView().setDayButtonHighlighted(false);
        getView().setWeekButtonHighlighted(false);
        getView().setMonthButtonHighlighted(false);
    }

    private void highlightDayButton() {
        getView().setHourButtonHighlighted(false);
        getView().setDayButtonHighlighted(true);
        getView().setWeekButtonHighlighted(false);
        getView().setMonthButtonHighlighted(false);
    }

    private void highlightWeekButton() {
        getView().setHourButtonHighlighted(false);
        getView().setDayButtonHighlighted(false);
        getView().setWeekButtonHighlighted(true);
        getView().setMonthButtonHighlighted(false);
    }

    private void highlightMonthButton() {
        getView().setHourButtonHighlighted(false);
        getView().setDayButtonHighlighted(false);
        getView().setWeekButtonHighlighted(false);
        getView().setMonthButtonHighlighted(true);
    }
}
