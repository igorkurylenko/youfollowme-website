package io.itdraft.youfollowme.website.application.categorynavbar;

import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.PresenterWidget;
import com.gwtplatform.mvp.client.View;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.shared.proxy.PlaceRequest;
import io.itdraft.youfollowme.website.application.event.PauseCategoryPresentationEvent;
import io.itdraft.youfollowme.website.application.event.PauseTimeRangePresentationEvent;
import io.itdraft.youfollowme.website.application.event.PlayCategoryPresentationEvent;
import io.itdraft.youfollowme.website.place.PlaceNameTokens;
import io.itdraft.youfollowme.website.util.PlaceUtil;

public class CategoryNavbarPresenter extends PresenterWidget<CategoryNavbarPresenter.MyView>
        implements CategoryNavbarUIHandlers,
        PlayCategoryPresentationEvent.PlayCategoryPresentationHandler,
        PauseCategoryPresentationEvent.PauseCategoryPresentationHandler {

    private final PlaceManager placeManager;

    public interface MyView extends View, HasUiHandlers<CategoryNavbarUIHandlers> {
        void setDomainsButtonHighlighted(boolean highlighted);

        void setHashtagsButtonHighlighted(boolean highlighted);

        void setKeywordsButtonHighlighted(boolean highlighted);

        void setPlayButtonVisible(boolean visible);

        void setPauseButtonVisible(boolean visible);
    }

    @Inject
    public CategoryNavbarPresenter(EventBus eventBus, MyView view, PlaceManager placeManager) {
        super(eventBus, view);

        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    @Override
    protected void onBind() {
        super.onBind();

        registerHandler(getEventBus().addHandler(PlayCategoryPresentationEvent.getType(), this));
        registerHandler(getEventBus().addHandler(PauseCategoryPresentationEvent.getType(), this));
    }

    @Override
    protected void onReset() {
        super.onReset();

        adjustHighlight();
    }

    @Override
    public void onHashtagsButtonClicked() {
        revealPlace(PlaceNameTokens.getHashtags());

        pauseEveryPresentation();
    }

    @Override
    public void onDomainsButtonClicked() {
        revealPlace(PlaceNameTokens.getDomains());

        pauseEveryPresentation();
    }

    @Override
    public void onKeywordsButtonClicked() {
        revealPlace(PlaceNameTokens.getKeywords());

        pauseEveryPresentation();
    }

    @Override
    public void onPlayButtonClicked() {
        PlayCategoryPresentationEvent.fire(this);
    }

    @Override
    public void onPauseButtonClicked() {
        PauseCategoryPresentationEvent.fire(this);
    }

    @Override
    public void onPlayCategoryPresentationEvent(PlayCategoryPresentationEvent event) {
        getView().setPlayButtonVisible(false);
        getView().setPauseButtonVisible(true);
    }

    @Override
    public void onPauseCategoryPresentationEvent(PauseCategoryPresentationEvent event) {
        getView().setPlayButtonVisible(true);
        getView().setPauseButtonVisible(false);
    }

    private void pauseEveryPresentation() {
        PauseCategoryPresentationEvent.fire(this);
        PauseTimeRangePresentationEvent.fire(this);
    }

    private void revealPlace(String placeName) {
        PlaceRequest curPlaceRequest = placeManager.getCurrentPlaceRequest();
        PlaceRequest placeRequest = PlaceUtil.mutateNameToken(curPlaceRequest, placeName);

        placeManager.revealPlace(placeRequest);
    }

    private void adjustHighlight() {
        switch (placeManager.getCurrentPlaceRequest().getNameToken()) {
            case PlaceNameTokens.hashtags:
                highlightHashtagsButton();
                break;

            case PlaceNameTokens.domains:
                highlightDomainsButton();
                break;

            case PlaceNameTokens.keywords:
                highlightKeywordsButton();
                break;

            default:
                break;
        }
    }

    private void highlightHashtagsButton() {
        getView().setHashtagsButtonHighlighted(true);
        getView().setDomainsButtonHighlighted(false);
        getView().setKeywordsButtonHighlighted(false);
    }

    private void highlightDomainsButton() {
        getView().setHashtagsButtonHighlighted(false);
        getView().setDomainsButtonHighlighted(true);
        getView().setKeywordsButtonHighlighted(false);
    }

    private void highlightKeywordsButton() {
        getView().setHashtagsButtonHighlighted(false);
        getView().setDomainsButtonHighlighted(false);
        getView().setKeywordsButtonHighlighted(true);
    }
}
