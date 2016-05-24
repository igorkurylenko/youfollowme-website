package io.itdraft.youfollowme.website.application.domain;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class DomainTrendsModule extends AbstractPresenterModule{
    @Override
    protected void configure() {
        bindPresenter(DomainTrendsPresenter.class, DomainTrendsPresenter.MyView.class, DomainTrendsView.class, DomainTrendsPresenter.MyProxy.class);
    }
}
