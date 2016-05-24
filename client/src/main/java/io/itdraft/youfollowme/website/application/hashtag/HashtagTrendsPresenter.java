package io.itdraft.youfollowme.website.application.hashtag;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import io.itdraft.youfollowme.website.api.HashtagTrendResource;
import io.itdraft.youfollowme.website.api.HashtagTrendResourceDelegateProxy;
import io.itdraft.youfollowme.website.application.ApplicationPresenter;
import io.itdraft.youfollowme.website.application.BaseTrendsPresenter;
import io.itdraft.youfollowme.website.dto.HashtagTrend;
import io.itdraft.youfollowme.website.dto.Trend;
import io.itdraft.youfollowme.website.place.PlaceNameTokens;
import io.itdraft.youfollowme.website.resources.AppMessages;
import io.itdraft.youfollowme.website.util.TwitterUtil;
import io.itdraft.youfollowme.website.util.async.promise.Deferred;
import io.itdraft.youfollowme.website.util.async.promise.Promise;
import io.itdraft.youfollowme.website.util.async.promise.impl.DeferredObject;
import io.itdraft.youfollowme.website.place.PlaceParams;
import io.itdraft.youfollowme.website.util.PlaceUtil;

import java.util.List;

public class HashtagTrendsPresenter extends BaseTrendsPresenter<HashtagTrendsPresenter.MyView, HashtagTrendsPresenter.MyProxy>
        implements HashtagTrendsUIHandlers {

    public interface MyView extends BaseTrendsPresenter.TrendsView, HasUiHandlers<HashtagTrendsUIHandlers> {
    }

    @ProxyCodeSplit
    @NameToken(PlaceNameTokens.hashtags)
    public interface MyProxy extends ProxyPlace<HashtagTrendsPresenter> {
    }

    private final ResourceDelegate<HashtagTrendResource> hashtagResourceDelegate;
    private PlaceManager placeManager;

    @Inject
    public HashtagTrendsPresenter(EventBus eventBus,
                                  MyView view,
                                  MyProxy proxy,
                                  HashtagTrendResourceDelegateProxy hashtagResourceDelegate,
                                  PlaceManager placeManager,
                                  AppMessages appMessages) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT_SLOT, appMessages);

        this.hashtagResourceDelegate = hashtagResourceDelegate;
        this.placeManager = placeManager;

        getView().setUiHandlers(this);
    }

    protected void onReset() {
        super.onReset();

        // todo: stop rendering and place searching
    }


    protected Promise<List<? extends Trend>, Throwable, Void> fetchTrends() {
        Deferred<List<? extends Trend>, Throwable, Void> deferred = new DeferredObject<>();

        fetchTrends(deferred);

        return deferred.promise();
    }

    private void fetchTrends(final Deferred<List<? extends Trend>, Throwable, Void> deferred) {
        PlaceParams.TimeRange timeRange = PlaceUtil.getCurTimeRange(placeManager);

        hashtagResourceDelegate.withCallback(new AsyncCallback<List<HashtagTrend>>() {
            public void onFailure(Throwable caught) {
                deferred.reject(caught);
            }

            public void onSuccess(final List<HashtagTrend> trends) {
                deferred.resolve(trends);
            }
        }).getHashtagTrends(timeRange.name());
    }

    public void onTrendSelected(HashtagTrend trend) {
        String url = TwitterUtil.buildTwitterSearchUrl("#" + trend.getDisplayName());

        Window.open(url, "_blank", null);
    }
}
