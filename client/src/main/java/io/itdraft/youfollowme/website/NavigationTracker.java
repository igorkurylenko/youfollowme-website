package io.itdraft.youfollowme.website;

import javax.inject.Inject;

import com.arcbees.analytics.shared.Analytics;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.proxy.NavigationEvent;
import com.gwtplatform.mvp.client.proxy.NavigationHandler;
import com.gwtplatform.mvp.shared.proxy.TokenFormatter;

public class NavigationTracker implements NavigationHandler {
    private final Analytics analytics;
    private final TokenFormatter tokenFormatter;

    @Inject
    NavigationTracker(TokenFormatter tokenFormatter, EventBus eventBus, Analytics analytics) {
        this.analytics = analytics;
        this.tokenFormatter = tokenFormatter;
        eventBus.addHandler(NavigationEvent.getType(), this);
    }

    public void onNavigation(final NavigationEvent navigationEvent) {
        analytics.sendPageView().documentPath(
                tokenFormatter.toPlaceToken(navigationEvent.getRequest())).go();
    }
}

