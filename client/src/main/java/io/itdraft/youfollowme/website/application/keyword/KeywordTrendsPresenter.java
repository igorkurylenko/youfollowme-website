package io.itdraft.youfollowme.website.application.keyword;

import com.google.gwt.core.client.GWT;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import io.itdraft.youfollowme.website.api.HashtagTrendResource;
import io.itdraft.youfollowme.website.application.ApplicationPresenter;
import io.itdraft.youfollowme.website.application.BaseTrendsPresenter;
import io.itdraft.youfollowme.website.application.TrendsDisplayMode;
import io.itdraft.youfollowme.website.dto.KeywordTrend;
import io.itdraft.youfollowme.website.dto.Trend;
import io.itdraft.youfollowme.website.place.PlaceNameTokens;
import io.itdraft.youfollowme.website.resources.AppMessages;
import io.itdraft.youfollowme.website.util.async.promise.Promise;
import io.itdraft.youfollowme.website.util.async.promise.impl.DeferredObject;

import java.util.Collections;
import java.util.List;

public class KeywordTrendsPresenter extends BaseTrendsPresenter<KeywordTrendsPresenter.MyView, KeywordTrendsPresenter.MyProxy> {
    private final ResourceDelegate<HashtagTrendResource> delegate;
    private PlaceManager placeManager;

    public interface MyView extends BaseTrendsPresenter.TrendsView, HasUiHandlers<KeywordTrendsUiHandlers> {

    }

    @ProxyCodeSplit
    @NameToken(PlaceNameTokens.keywords)
    public interface MyProxy extends ProxyPlace<KeywordTrendsPresenter> {

    }

    @Inject
    public KeywordTrendsPresenter(EventBus eventBus,
                                  MyView view,
                                  MyProxy proxy,
                                  ResourceDelegate<HashtagTrendResource> delegate,
                                  PlaceManager placeManager,
                                  AppMessages appMessages) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT_SLOT, appMessages);

        this.delegate = delegate;
        this.placeManager = placeManager;

        getView().setDisplayMode(TrendsDisplayMode.NoDisplay);
    }

    protected Promise<List<? extends Trend>, Throwable, Void> fetchTrends() {
        GWT.log("Keyword trends page is not implemented yet.");

        List<KeywordTrend> trends = Collections.emptyList();

        return new DeferredObject<List<? extends Trend>, Throwable, Void>().resolve(trends);
    }
}
