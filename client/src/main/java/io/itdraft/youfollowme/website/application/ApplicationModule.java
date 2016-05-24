package io.itdraft.youfollowme.website.application;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;
import io.itdraft.youfollowme.website.application.domain.DomainTrendsModule;
import io.itdraft.youfollowme.website.application.hashtag.HashtagTrendsModule;
import io.itdraft.youfollowme.website.application.keyword.KeywordTrendsModule;
import io.itdraft.youfollowme.website.application.status.StatusModule;
import io.itdraft.youfollowme.website.application.timerangenavbar.TimeRangeNavbarModule;
import io.itdraft.youfollowme.website.application.categorynavbar.CategoryNavbarModule;

public class ApplicationModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        install(new StatusModule());
        install(new HashtagTrendsModule());
        install(new DomainTrendsModule());
        install(new KeywordTrendsModule());
        install(new TimeRangeNavbarModule());
        install(new CategoryNavbarModule());

        bindPresenter(ApplicationPresenter.class, ApplicationPresenter.MyView.class, ApplicationView.class,
                ApplicationPresenter.MyProxy.class);
    }
}
