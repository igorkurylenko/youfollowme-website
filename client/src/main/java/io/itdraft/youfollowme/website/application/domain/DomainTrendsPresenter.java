package io.itdraft.youfollowme.website.application.domain;

import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.rpc.AsyncCallback;
import com.google.inject.Inject;
import com.google.web.bindery.event.shared.EventBus;
import com.gwtplatform.mvp.client.HasUiHandlers;
import com.gwtplatform.mvp.client.annotations.NameToken;
import com.gwtplatform.mvp.client.annotations.ProxyCodeSplit;
import com.gwtplatform.mvp.client.proxy.PlaceManager;
import com.gwtplatform.mvp.client.proxy.ProxyPlace;
import io.itdraft.youfollowme.website.api.DomainTrendResourceDelegateProxy;
import io.itdraft.youfollowme.website.application.ApplicationPresenter;
import io.itdraft.youfollowme.website.application.BaseTrendsPresenter;
import io.itdraft.youfollowme.website.dto.DomainTrend;
import io.itdraft.youfollowme.website.dto.Trend;
import io.itdraft.youfollowme.website.place.PlaceNameTokens;
import io.itdraft.youfollowme.website.resources.AppMessages;
import io.itdraft.youfollowme.website.util.async.promise.Deferred;
import io.itdraft.youfollowme.website.util.async.promise.Promise;
import io.itdraft.youfollowme.website.util.async.promise.impl.DeferredObject;
import io.itdraft.youfollowme.website.place.PlaceParams;
import io.itdraft.youfollowme.website.util.PlaceUtil;

import java.util.List;

public class DomainTrendsPresenter extends BaseTrendsPresenter<DomainTrendsPresenter.MyView, DomainTrendsPresenter.MyProxy>
        implements DomainsUIHandlers {

    public interface MyView extends BaseTrendsPresenter.TrendsView, HasUiHandlers<DomainsUIHandlers> {

    }

    @ProxyCodeSplit
    @NameToken(PlaceNameTokens.domains)
    public interface MyProxy extends ProxyPlace<DomainTrendsPresenter> {

    }

    private final DomainTrendResourceDelegateProxy domainResourceDelegate;

    private final PlaceManager placeManager;

    @Inject
    public DomainTrendsPresenter(EventBus eventBus,
                                 MyView view,
                                 MyProxy proxy,
                                 DomainTrendResourceDelegateProxy domainResourceDelegate,
                                 PlaceManager placeManager,
                                 AppMessages messages) {
        super(eventBus, view, proxy, ApplicationPresenter.MAIN_CONTENT_SLOT, messages);

        this.domainResourceDelegate = domainResourceDelegate;
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

        domainResourceDelegate.withCallback(new AsyncCallback<List<DomainTrend>>() {
            public void onFailure(Throwable caught) {
                deferred.reject(caught);
            }

            public void onSuccess(final List<DomainTrend> trends) {
                deferred.resolve(trends);
            }
        }).getDomainTrends(timeRange.name());
    }

    public void onTrendSelected(DomainTrend trend) {
        // todo: server have to provide full url
        String url = "http://" + trend.getDisplayName();

        Window.open(url, "_blank", null);
    }
}
