package io.itdraft.youfollowme.website;

import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.Style;
import com.google.gwt.user.client.DOM;
import com.gwtplatform.mvp.client.Bootstrapper;
import com.gwtplatform.mvp.client.proxy.PlaceManager;

import javax.inject.Inject;

public class AppBootstrapper implements Bootstrapper{
    public static final String LOADING_INDICATOR_ID = "loading";

    private final PlaceManager placeManager;

    @Inject
    public AppBootstrapper(PlaceManager placeManager) {
        this.placeManager = placeManager;
    }

    @Override
    public void onBootstrap() {
        hideLoadingIndicator();
        placeManager.revealCurrentPlace();
    }

    private void hideLoadingIndicator() {
        Element loadingIndicator = DOM.getElementById(LOADING_INDICATOR_ID);

        if (loadingIndicator != null) {
            loadingIndicator.getStyle().setDisplay(Style.Display.NONE);
        }
    }
}
