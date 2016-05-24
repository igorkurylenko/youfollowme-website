package io.itdraft.youfollowme.website.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.client.DelegatingDispatchRequest;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.dispatch.rest.shared.RestAction;
import io.itdraft.youfollowme.website.dto.DomainTrend;
import io.itdraft.youfollowme.website.util.Cache;

import javax.inject.Inject;
import java.util.List;

public class DomainTrendResourceDelegateProxy implements ResourceDelegate<DomainTrendResource> {
    private final Cache<String, List<DomainTrend>> domainsCache = Cache.create();
    private final ResourceDelegate<DomainTrendResource> delegate;
    private final DomainTrendResourceProxy domainResourceProxyWithoutCallback =
            new DomainTrendResourceProxy(null);

    @Inject
    public DomainTrendResourceDelegateProxy(ResourceDelegate<DomainTrendResource> delegate) {
        this.delegate = delegate;
    }

    public ResourceDelegate<DomainTrendResource> withDelegatingDispatchRequest(
            DelegatingDispatchRequest delegatingDispatchRequest) {
        delegate.withDelegatingDispatchRequest(delegatingDispatchRequest);

        return this;
    }

    public DomainTrendResource withCallback(final AsyncCallback<?> callback) {
        return new DomainTrendResourceProxy(callback);
    }

    public DomainTrendResource withoutCallback() {
        return domainResourceProxyWithoutCallback;
    }

    public DomainTrendResource getResource() {
        return withoutCallback();
    }

    private class DomainTrendResourceProxy implements DomainTrendResource {
        final AsyncCallback<?> callback;

        public DomainTrendResourceProxy(AsyncCallback<?> callback) {
            this.callback = callback;
        }

        public RestAction<List<DomainTrend>> getDomainTrends(final String timeRange) {
            RestAction<List<DomainTrend>> result = null;
            List<DomainTrend> domainsFromCache = domainsCache.get(timeRange);

            if (domainsFromCache == null) {
                result = delegate.withCallback(new AsyncCallback<List<DomainTrend>>() {
                    public void onFailure(Throwable caught) {
                        if (callback != null) callback.onFailure(caught);
                    }

                    public void onSuccess(List<DomainTrend> domainTrends) {
                        domainsCache.put(timeRange, domainTrends);

                        if (callback != null) {
                            ((AsyncCallback<List<DomainTrend>>) callback).onSuccess(domainTrends);
                        }
                    }
                }).getDomainTrends(timeRange);

            } else {
                if (callback != null) {
                    ((AsyncCallback<List<DomainTrend>>) callback).onSuccess(domainsFromCache);
                }
            }

            return result;
        }
    }
}
