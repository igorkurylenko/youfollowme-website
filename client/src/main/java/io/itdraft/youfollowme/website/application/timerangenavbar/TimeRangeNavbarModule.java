package io.itdraft.youfollowme.website.application.timerangenavbar;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class TimeRangeNavbarModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(TimeRangeNavbarPresenter.class, TimeRangeNavbarPresenter.MyView.class, TimeRangeNavbarView.class);
    }
}
