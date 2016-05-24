package io.itdraft.youfollowme.website.application.status;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class StatusModule extends AbstractPresenterModule {
    protected void configure() {
        bindSingletonPresenterWidget(StatusPresenter.class, StatusPresenter.MyView.class, StatusView.class);
    }
}
