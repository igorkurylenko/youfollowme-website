package io.itdraft.youfollowme.website.application.hashtag;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class HashtagTrendsModule extends AbstractPresenterModule{
    @Override
    protected void configure() {
        bindPresenter(HashtagTrendsPresenter.class, HashtagTrendsPresenter.MyView.class, HashtagTrendsView.class, HashtagTrendsPresenter.MyProxy.class);
    }
}
