package io.itdraft.youfollowme.website.application.categorynavbar;

import com.gwtplatform.mvp.client.gin.AbstractPresenterModule;

public class CategoryNavbarModule extends AbstractPresenterModule {
    @Override
    protected void configure() {
        bindPresenterWidget(CategoryNavbarPresenter.class, CategoryNavbarPresenter.MyView.class, CategoryNavbarView.class);
    }
}
