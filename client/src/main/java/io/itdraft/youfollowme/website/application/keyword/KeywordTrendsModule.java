package io.itdraft.youfollowme.website.application.keyword;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class KeywordTrendsModule extends AbstractPresenterModule{
    @Override
    protected void configure() {
        bindPresenter(KeywordTrendsPresenter.class, KeywordTrendsPresenter.MyView.class, KeywordTrendsView.class, KeywordTrendsPresenter.MyProxy.class);
    }
}
