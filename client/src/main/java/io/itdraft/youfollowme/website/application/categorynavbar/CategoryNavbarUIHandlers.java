package io.itdraft.youfollowme.website.application.categorynavbar;

import com.gwtplatform.mvp.client.UiHandlers;

public interface CategoryNavbarUIHandlers extends UiHandlers {
    void onHashtagsButtonClicked();

    void onDomainsButtonClicked();

    void onKeywordsButtonClicked();

    void onPlayButtonClicked();

    void onPauseButtonClicked();
}