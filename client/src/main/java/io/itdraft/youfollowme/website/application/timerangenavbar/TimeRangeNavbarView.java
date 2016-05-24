package io.itdraft.youfollowme.website.application.timerangenavbar;

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

public class TimeRangeNavbarView extends ViewWithUiHandlers<TimeRangeNavbarUIHandlers>
        implements TimeRangeNavbarPresenter.MyView {

    private AppResources resources;

    interface Binder extends UiBinder<Widget, TimeRangeNavbarView> {}

    @UiField
    HTMLPanel categoryNavbarPanel;

    @UiField
    Button hourButton;

    @UiField
    Button dayButton;

    @UiField
    Button weekButton;

    @UiField
    Button monthButton;
    @UiField
    Button playButton;
    @UiField
    Button pauseButton;

    @Inject
    TimeRangeNavbarView(Binder binder, AppResources resources) {
        this.resources = resources;

        initWidget(binder.createAndBindUi(this));
    }

    @Override
    public void setHourButtonHighlighted(boolean highlighted) {
        setButtonHighlighted(hourButton, highlighted);
    }

    @Override
    public void setDayButtonHighlighted(boolean highlighted) {
        setButtonHighlighted(dayButton, highlighted);
    }

    @Override
    public void setWeekButtonHighlighted(boolean highlighted) {
        setButtonHighlighted(weekButton, highlighted);
    }

    @Override
    public void setMonthButtonHighlighted(boolean highlighted) {
        setButtonHighlighted(monthButton, highlighted);
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

    @UiHandler("hourButton")
    void handleHourButtonClick(ClickEvent event){
        getUiHandlers().onHourButtonClicked();
    }

    @UiHandler("dayButton")
    void handleDayButtonClick(ClickEvent event){
        getUiHandlers().onDayButtonClicked();
    }

    @UiHandler("weekButton")
    void handleWeekButtonClick(ClickEvent event){
        getUiHandlers().onWeekButtonClicked();
    }

    @UiHandler("monthButton")
    void handleMonthButtonClick(ClickEvent event){
        getUiHandlers().onMonthButtonClicked();
    }

    @UiHandler("playButton")
    void handlePlayButtonClick(ClickEvent event){
        getUiHandlers().onPlayButtonClicked();
    }

    @UiHandler("pauseButton")
    void handlePauseButtonClick(ClickEvent event){
        getUiHandlers().onPauseButtonClicked();
    }
}
