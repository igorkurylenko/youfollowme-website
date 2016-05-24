package io.itdraft.youfollowme.website.application.timerangenavbar;

import com.gwtplatform.mvp.client.UiHandlers;

public interface TimeRangeNavbarUIHandlers extends UiHandlers {
    void onHourButtonClicked();

    void onDayButtonClicked();

    void onWeekButtonClicked();

    void onMonthButtonClicked();

    void onPlayButtonClicked();

    void onPauseButtonClicked();
}