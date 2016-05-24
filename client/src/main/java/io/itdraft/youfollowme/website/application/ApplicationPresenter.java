package io.itdraft.youfollowme.website.application;

import com.google.gwt.user.client.Timer;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.Presenter;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.annotations.ProxyStandard;
import com.gwtplatform.mvp.client.presenter.slots.NestedSlot;
import com.gwtplatform.mvp.client.presenter.slots.SingleSlot;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.Proxy;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import io.itdraft.youfollowme.website.api.DomainTrendResourceDelegateProxy;
import io.itdraft.youfollowme.website.api.HashtagTrendResourceDelegateProxy;
import io.itdraft.youfollowme.website.application.event.*;
import io.itdraft.youfollowme.website.application.status.StatusPresenter;
import io.itdraft.youfollowme.website.application.categorynavbar.CategoryNavbarPresenter;
import io.itdraft.youfollowme.website.application.timerangenavbar.TimeRangeNavbarPresenter;
import io.itdraft.youfollowme.website.dto.DomainTrend;
import io.itdraft.youfollowme.website.dto.HashtagTrend;
import io.itdraft.youfollowme.website.place.PlaceNameTokens;
import io.itdraft.youfollowme.website.place.PlaceParams;
import io.itdraft.youfollowme.website.util.PlaceUtil;

import javax.inject.Inject;
import java.util.Arrays;
import java.util.List;

public class ApplicationPresenter extends Presenter<ApplicationPresenter.MyView, ApplicationPresenter.MyProxy>
        implements PlayCategoryPresentationEvent.PlayCategoryPresentationHandler,
        PauseCategoryPresentationEvent.PauseCategoryPresentationHandler,
        PlayTimeRangePresentationEvent.PlayTimeRangePresentationHandler,
        PauseTimeRangePresentationEvent.PauseTimeRangePresentationHandler {

    @ProxyStandard
    interface MyProxy extends Proxy<ApplicationPresenter> {
    }

    interface MyView extends View {
    }

    public static final SingleSlot<StatusPresenter> MESSAGE_SLOT = new SingleSlot();
    public static final SingleSlot<TimeRangeNavbarPresenter> TIME_RANGE_NAVBAR_SLOT = new SingleSlot<>();
    public static final SingleSlot<CategoryNavbarPresenter> CATEGORY_NAVBAR_SLOT = new SingleSlot<>();
    public static final NestedSlot MAIN_CONTENT_SLOT = new NestedSlot();
    public static final int PRESENTATION_TIMER_INTERVAL_MS = 1000 * 60; // 1 min

    private final CategoryNavbarPresenter categoryNavbarPresenter;
    private final TimeRangeNavbarPresenter timeRangeNavbarPresenter;
    private HashtagTrendResourceDelegateProxy hashtagResourceDelegate;
    private DomainTrendResourceDelegateProxy domainResourceDelegate;
    private StatusPresenter statusPresenter;
    private final PlaceManager placeManager;
    private TrendsPresentationState presentationState;
    private final Timer presentationTimer;

    @Inject
    ApplicationPresenter(EventBus eventBus, MyView view, MyProxy proxy,
                         CategoryNavbarPresenter categoryNavbarPresenter,
                         TimeRangeNavbarPresenter timeRangeNavbarPresenter,
                         StatusPresenter statusPresenter,
                         PlaceManager placeManager,
                         HashtagTrendResourceDelegateProxy hashtagResourceDelegate,
                         DomainTrendResourceDelegateProxy domainResourceDelegate) {
        super(eventBus, view, proxy, RevealType.Root);

        this.placeManager = placeManager;
        this.statusPresenter = statusPresenter;
        this.categoryNavbarPresenter = categoryNavbarPresenter;
        this.timeRangeNavbarPresenter = timeRangeNavbarPresenter;
        this.hashtagResourceDelegate = hashtagResourceDelegate;
        this.domainResourceDelegate = domainResourceDelegate;
        this.presentationState = new PausedState();
        this.presentationTimer = new Timer() {
            public void run() {
                presentationState.onTimer();
            }
        };
    }

    protected void onReset() {
        super.onReset();

        HideStatusEvent.fire(this);

        schedulePresentationTimer();
    }

    protected void onHide() {
        super.onHide();

        presentationTimer.cancel();
    }

    protected void onBind() {
        setInSlot(MESSAGE_SLOT, statusPresenter);
        setInSlot(CATEGORY_NAVBAR_SLOT, categoryNavbarPresenter);
        setInSlot(TIME_RANGE_NAVBAR_SLOT, timeRangeNavbarPresenter);

        addRegisteredHandler(PlayCategoryPresentationEvent.getType(), this);
        addRegisteredHandler(PlayTimeRangePresentationEvent.getType(), this);
        addRegisteredHandler(PauseCategoryPresentationEvent.getType(), this);
        addRegisteredHandler(PauseTimeRangePresentationEvent.getType(), this);
    }

    public void onPauseCategoryPresentationEvent(PauseCategoryPresentationEvent event) {
        presentationState.onPauseCategoryPresentation();
    }

    public void onPauseTimeRangePresentationEvent(PauseTimeRangePresentationEvent event) {
        presentationState.onPauseTimeRangePresentation();
    }

    public void onPlayCategoryPresentationEvent(PlayCategoryPresentationEvent event) {
        presentationState.onPlayCategoryPresentation();
    }

    public void onPlayTimeRangePresentationEvent(PlayTimeRangePresentationEvent event) {
        presentationState.onPlayTimeRangePresentation();
    }

    private void schedulePresentationTimer() {
        presentationTimer.schedule(PRESENTATION_TIMER_INTERVAL_MS);
    }

    private void revealPlace(final PlaceRequest placeRequest) {
        final String placeNameToken = placeRequest.getNameToken();
        PlaceParams.TimeRange timeRange = PlaceUtil.getTimeRangeParamValue(placeRequest);

        switch (placeNameToken) {
            case PlaceNameTokens.hashtags:
                hashtagResourceDelegate.withCallback(new AsyncCallback<List<HashtagTrend>>() {
                    public void onFailure(Throwable caught) {
                        placeManager.revealPlace(placeRequest);
                    }

                    public void onSuccess(List<HashtagTrend> result) {
                        placeManager.revealPlace(placeRequest);
                    }
                }).getHashtagTrends(timeRange.name());

                break;

            case PlaceNameTokens.domains:
                domainResourceDelegate.withCallback(new AsyncCallback<List<DomainTrend>>() {
                    public void onFailure(Throwable caught) {
                        placeManager.revealPlace(placeRequest);
                    }

                    public void onSuccess(List<DomainTrend> result) {
                        placeManager.revealPlace(placeRequest);
                    }
                }).getDomainTrends(timeRange.name());

                break;

            default:
                placeManager.revealPlace(placeRequest);
                break;
        }
    }

    abstract class TrendsPresentationState {
        List<String> categoryPresentationPlaceTokens = Arrays.asList(
                PlaceNameTokens.hashtags, PlaceNameTokens.domains, PlaceNameTokens.keywords);
        List<PlaceParams.TimeRange> timeRangePresentationParams = Arrays.asList(
                PlaceParams.TimeRange.hour, PlaceParams.TimeRange.day, PlaceParams.TimeRange.week, PlaceParams.TimeRange.month);

        public void onTimer() {
            final PlaceRequest placeRequest = getNextPlaceRequest();

            revealPlace(placeRequest);
        }

        protected abstract PlaceRequest getNextPlaceRequest();

        PlaceRequest getNextCategoryPlaceRequest() {
            PlaceRequest curPlaceRequest = placeManager.getCurrentPlaceRequest();
            String curCategory = curPlaceRequest.getNameToken();
            String nextCategoryPlaceToken = getNextCategoryPlaceToken(curCategory);

            return PlaceUtil.mutateNameToken(curPlaceRequest, nextCategoryPlaceToken);
        }

        PlaceRequest getNextTimeRangePlaceRequest() {
            PlaceRequest curPlaceRequest = placeManager.getCurrentPlaceRequest();
            PlaceParams.TimeRange curTimeRange = PlaceUtil.getTimeRangeParamValue(curPlaceRequest);
            PlaceParams.TimeRange nextTimeRangeParam = getNextTimeRangeParam(curTimeRange);

            return PlaceUtil.mutateTimeRangeParam(curPlaceRequest, nextTimeRangeParam);
        }

        abstract void onPlayCategoryPresentation();

        abstract void onPlayTimeRangePresentation();

        abstract void onPauseCategoryPresentation();

        abstract void onPauseTimeRangePresentation();

        private String getNextCategoryPlaceToken(String curCategory) {
            int curCategoryIndex = categoryPresentationPlaceTokens.indexOf(curCategory);
            int nextCategoryIndex = (curCategoryIndex + 1) % categoryPresentationPlaceTokens.size();

            return categoryPresentationPlaceTokens.get(nextCategoryIndex);
        }

        private PlaceParams.TimeRange getNextTimeRangeParam(PlaceParams.TimeRange curTimeRange) {
            int curTimeRangeIndex = timeRangePresentationParams.indexOf(curTimeRange);
            int nextTimeRangeIndex = (curTimeRangeIndex + 1) % timeRangePresentationParams.size();

            return timeRangePresentationParams.get(nextTimeRangeIndex);
        }
    }

    class PausedState extends TrendsPresentationState {

        @Override
        protected PlaceRequest getNextPlaceRequest() {
            return placeManager.getCurrentPlaceRequest();
        }

        public void onPlayCategoryPresentation() {
            presentationState = new PlayCategoryState();
        }

        public void onPlayTimeRangePresentation() {
            presentationState = new PlayTimeRangeState();
        }

        public void onPauseCategoryPresentation() {
        }

        public void onPauseTimeRangePresentation() {
        }
    }

    class PlayCategoryState extends TrendsPresentationState {

        protected PlaceRequest getNextPlaceRequest() {
            return getNextCategoryPlaceRequest();
        }

        public void onPlayCategoryPresentation() {
        }

        public void onPlayTimeRangePresentation() {
            presentationState = new PlayCategoryAndTimeRangeState();
        }

        public void onPauseCategoryPresentation() {
            presentationState = new PausedState();
        }

        public void onPauseTimeRangePresentation() {
        }
    }

    class PlayTimeRangeState extends TrendsPresentationState {

        protected PlaceRequest getNextPlaceRequest() {
            return getNextTimeRangePlaceRequest();
        }

        public void onPlayCategoryPresentation() {
            presentationState = new PlayCategoryAndTimeRangeState();
        }

        public void onPlayTimeRangePresentation() {
        }

        public void onPauseCategoryPresentation() {
        }

        public void onPauseTimeRangePresentation() {
            presentationState = new PausedState();
        }
    }

    class PlayCategoryAndTimeRangeState extends TrendsPresentationState {

        public void onPlayCategoryPresentation() {
        }

        public void onPlayTimeRangePresentation() {
        }

        public void onPauseCategoryPresentation() {
            presentationState = new PlayTimeRangeState();
        }

        public void onPauseTimeRangePresentation() {
            presentationState = new PlayCategoryState();
        }

        protected PlaceRequest getNextPlaceRequest() {
            PlaceRequest nextTimeRangePlaceRequest = getNextTimeRangePlaceRequest();
            PlaceParams.TimeRange nextTimeRange = PlaceUtil.getTimeRangeParamValue(nextTimeRangePlaceRequest);
            int index = timeRangePresentationParams.indexOf(nextTimeRange);

            return index != 0 ? nextTimeRangePlaceRequest :
                    PlaceUtil.mutateTimeRangeParam(getNextCategoryPlaceRequest(), nextTimeRange);
        }
    }
}
