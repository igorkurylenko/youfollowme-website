package io.itdraft.youfollowme.website.util.async.promise;

import io.itdraft.youfollowme.website.util.async.promise.Promise.State;

public interface AlwaysCallback<D, R> {
    void onAlways(final State state, final D resolved, final R result);
}
