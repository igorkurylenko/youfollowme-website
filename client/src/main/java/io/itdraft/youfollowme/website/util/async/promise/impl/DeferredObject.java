package io.itdraft.youfollowme.website.util.async.promise.impl;

import io.itdraft.youfollowme.website.util.async.promise.Deferred;
import io.itdraft.youfollowme.website.util.async.promise.Promise;

import static io.itdraft.youfollowme.website.util.async.promise.Promise.State.REJECTED;
import static io.itdraft.youfollowme.website.util.async.promise.Promise.State.RESOLVED;

public class DeferredObject<D, F, P> extends AbstractPromise<D, F, P> implements Deferred<D, F, P> {

    public Deferred<D, F, P> resolve(D resolve) {
        if (!isPending()) {
            throw new IllegalStateException("Deferred object already finished, cannot resolve again");
        }

        this.state = RESOLVED;
        this.resolveResult = resolve;

        try {
            triggerDone(resolve);

        } finally {
            triggerAlways(state, resolve, null);
        }

        return this;
    }

    public Deferred<D, F, P> notify(P progress) {
        if (!isPending()) {
            throw new IllegalStateException("Deferred object already finished, cannot notify progress");
        }

        triggerProgress(progress);

        return this;
    }

    public Deferred<D, F, P> reject(F reject) {
        if (!isPending()) {
            throw new IllegalStateException("Deferred object already finished, cannot reject again");
        }

        this.state = REJECTED;
        this.rejectResult = reject;

        try {
            triggerFail(reject);

        } catch (Exception e) {
            triggerAlways(state, null, reject);
        }

        return this;
    }

    public Promise<D, F, P> promise() {
        return this;
    }
}
