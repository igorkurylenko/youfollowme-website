package io.itdraft.youfollowme.website.api;

import com.google.gwt.user.client.rpc.AsyncCallback;
import com.gwtplatform.dispatch.client.DelegatingDispatchRequest;
import com.gwtplatform.dispatch.rest.delegates.client.ResourceDelegate;
import com.gwtplatform.dispatch.rest.shared.RestAction;
import io.itdraft.youfollowme.website.dto.HashtagTrend;
import io.itdraft.youfollowme.website.util.Cache;

import javax.inject.Inject;
import java.util.List;


public class HashtagTrendResourceDelegateProxy implements ResourceDelegate<HashtagTrendResource> {
    private final Cache<String, List<HashtagTrend>> hashtagsCache = Cache.create();
    private final ResourceDelegate<HashtagTrendResource> delegate;
    private final HashtagTrendResourceProxy hashtagResourceProxyWithoutCallback =
            new HashtagTrendResourceProxy(null);

    @Inject
    public HashtagTrendResourceDelegateProxy(ResourceDelegate<HashtagTrendResource> delegate) {
        this.delegate = delegate;
    }

    public ResourceDelegate<HashtagTrendResource> withDelegatingDispatchRequest(
            DelegatingDispatchRequest delegatingDispatchRequest) {
        delegate.withDelegatingDispatchRequest(delegatingDispatchRequest);

        return this;
    }

    public HashtagTrendResource withCallback(final AsyncCallback<?> callback) {
        return new HashtagTrendResourceProxy(callback);
    }

    public HashtagTrendResource withoutCallback() {
        return hashtagResourceProxyWithoutCallback;
    }

    public HashtagTrendResource getResource() {
        return withoutCallback();
    }

    private class HashtagTrendResourceProxy implements HashtagTrendResource {
        final AsyncCallback<?> callback;

        public HashtagTrendResourceProxy(AsyncCallback<?> callback) {
            this.callback = callback;
        }

        public RestAction<List<HashtagTrend>> getHashtagTrends(final String timeRange) {
            RestAction<List<HashtagTrend>> result = null;
            List<HashtagTrend> hashtagsFromCache = hashtagsCache.get(timeRange);

            if (hashtagsFromCache == null) {
                result = delegate.withCallback(new AsyncCallback<List<HashtagTrend>>() {
                    public void onFailure(Throwable caught) {
                        if (callback != null) callback.onFailure(caught);
                    }

                    public void onSuccess(List<HashtagTrend> domains) {
                        hashtagsCache.put(timeRange, domains);

                        if (callback != null) {
                            ((AsyncCallback<List<HashtagTrend>>) callback).onSuccess(domains);
                        }
                    }
                }).getHashtagTrends(timeRange);

            } else {
                if (callback != null) {
                    ((AsyncCallback<List<HashtagTrend>>) callback).onSuccess(hashtagsFromCache);
                }
            }

            return result;
        }
    }
}
