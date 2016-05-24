package io.itdraft.youfollowme.website.application.categorynavbar;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Button;
import com.google.gwt.user.client.ui.HTMLPanel;
import com.google.gwt.user.client.ui.Widget;
import com.gwtplatform.mvp.client.ViewWithUiHandlers;
import io.itdraft.youfollowme.website.resources.AppResources;

import javax.inject.Inject;

public class CategoryNavbarView extends ViewWithUiHandlers<CategoryNavbarUIHandlers>
        implements CategoryNavbarPresenter.MyView {

    interface Binder extends UiBinder<Widget, CategoryNavbarView> {}

    @UiField
    HTMLPanel typeMenu;
    @UiField
    Button hashtagsButton;
    @UiField
    Button domainsButton;
    @UiField
    Button keywordsButton;
    @UiField
    Button playButton;
    @UiField
    Button pauseButton;
    @UiField
    Button githubButton;

    private final AppResources resources;

    @Inject
    CategoryNavbarView(Binder binder, AppResources resources) {
        this.resources = resources;

        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setHashtagsButtonHighlighted(boolean highlighted) {
        setButtonHighlighted(hashtagsButton, highlighted);
    }

    @Override
    public void setDomainsButtonHighlighted(boolean highlighted) {
        setButtonHighlighted(domainsButton, highlighted);
    }

    @Override
    public void setKeywordsButtonHighlighted(boolean highlighted) {
        setButtonHighlighted(keywordsButton, highlighted);
    }

    @Override
    public void setPlayButtonVisible(boolean visible) {
        playButton.setVisible(visible);
    }

    @Override
    public void setPauseButtonVisible(boolean visible) {
        pauseButton.setVisible(visible);
    }

    private void setButtonHighlighted(Button button, boolean highlighted) {
        if (highlighted) {
            button.addStyleName(resources.style().highlighted());

        } else {
            button.removeStyleName(resources.style().highlighted());
        }
    }

    @UiHandler("hashtagsButton")
    public void handleHashtagsButtonClick(ClickEvent event) {
        getUiHandlers().onHashtagsButtonClicked();
    }

    @UiHandler("domainsButton")
    public void handleDomainsButtonClick(ClickEvent event) {
        getUiHandlers().onDomainsButtonClicked();
    }

    @UiHandler("keywordsButton")
    public void handleKeywordsButtonClick(ClickEvent event) {
        getUiHandlers().onKeywordsButtonClicked();
    }

    @UiHandler("playButton")
    public void handlePlayButtonClick(ClickEvent event) {
        getUiHandlers().onPlayButtonClicked();
    }

    @UiHandler("pauseButton")
    public void handlePauseButtonClick(ClickEvent event) {
        getUiHandlers().onPauseButtonClicked();
    }
}
