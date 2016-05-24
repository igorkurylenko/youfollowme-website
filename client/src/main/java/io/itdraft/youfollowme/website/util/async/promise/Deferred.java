package io.itdraft.youfollowme.website.util.async.promise;

public interface Deferred<D, F, P> extends Promise<D, F, P> {
    Deferred<D, F, P> resolve(final D resolve);

    Deferred<D, F, P> reject(final F reject);

    Deferred<D, F, P> notify(final P progress);

    Promise<D, F, P> promise();
}
