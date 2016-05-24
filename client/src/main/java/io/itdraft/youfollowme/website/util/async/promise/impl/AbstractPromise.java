package io.itdraft.youfollowme.website.util.async.promise.impl;

import com.google.gwt.core.client.GWT;
import io.itdraft.youfollowme.website.util.async.promise.*;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractPromise<D, F, P> implements Promise<D, F, P> {
    protected State state = State.PENDING;

    protected List<DoneCallback<D>> doneCallbacks = new ArrayList<>();
    protected List<FailCallback<F>> failCallbacks = new ArrayList<>();
    protected List<ProgressCallback<P>> progressCallbacks = new ArrayList<>();
    protected List<AlwaysCallback<D, F>> alwaysCallbacks = new ArrayList<>();

    protected D resolveResult;
    protected F rejectResult;

    public State state() {
        return state;
    }

    public Promise<D, F, P> done(DoneCallback<D> callback) {
        if (isResolved()) {
            callback.onDone(resolveResult);

        } else {
            doneCallbacks.add(callback);
        }

        return this;
    }

    protected void triggerDone(D resolved) {
        for (DoneCallback<D> callback : doneCallbacks) {
            try {
                callback.onDone(resolved);

            } catch (Exception e) {
                GWT.log("an uncaught exception occurred in a DoneCallback", e);
            }
        }

        doneCallbacks.clear();
    }

    public Promise<D, F, P> fail(FailCallback<F> callback) {
        if (isRejected()) {
            callback.onFail(rejectResult);

        } else {
            failCallbacks.add(callback);
        }

        return this;
    }

    protected void triggerFail(F rejected) {
        for (FailCallback<F> callback : failCallbacks) {
            try {
                callback.onFail(rejected);

            } catch (Exception e) {
                GWT.log("an uncaught exception occurred in a FailCallback", e);
            }

            failCallbacks.clear();
        }
    }

    public Promise<D, F, P> always(AlwaysCallback<D, F> callback) {
        if (isPending()) {
            alwaysCallbacks.add(callback);

        } else {
            callback.onAlways(state, resolveResult, rejectResult);
        }

        return this;
    }

    protected void triggerAlways(State state, D resolve, F reject) {
        for (AlwaysCallback<D, F> callback : alwaysCallbacks) {
            try {
                callback.onAlways(state, resolve, reject);

            } catch (Exception e) {
                GWT.log("an uncaught exception occurred in a AlwaysCallback", e);
            }
        }

        alwaysCallbacks.clear();
    }

    public Promise<D, F, P> progress(ProgressCallback<P> callback) {
        progressCallbacks.add(callback);

        return this;
    }

    protected void triggerProgress(P progress) {
        for (ProgressCallback<P> callback : progressCallbacks) {
            try {
                callback.onProgress(progress);

            } catch (Exception e) {
                GWT.log("an uncaught exception occurred in a ProgressCallback", e);
            }
        }

        progressCallbacks.clear();
    }

    public Promise<D, F, P> then(DoneCallback<D> callback) {
        return done(callback);
    }

    public Promise<D, F, P> then(DoneCallback<D> doneCallback, FailCallback<F> failCallback) {
        done(doneCallback);
        fail(failCallback);

        return this;
    }

    public Promise<D, F, P> then(DoneCallback<D> doneCallback, FailCallback<F> failCallback,
                                 ProgressCallback<P> progressCallback) {
        done(doneCallback);
        fail(failCallback);
        progress(progressCallback);

        return this;
    }

    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DoneFilter<D, D_OUT> doneFilter) {
        return new FilteredPromise<>(this, doneFilter, null, null);
    }

    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DoneFilter<D, D_OUT> doneFilter, FailFilter<F, F_OUT> failFilter) {

        return new FilteredPromise<>(this, doneFilter, failFilter, null);
    }

    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DoneFilter<D, D_OUT> doneFilter, FailFilter<F, F_OUT> failFilter,
            ProgressFilter<P, P_OUT> progressFilter) {

        return new FilteredPromise<>(this, doneFilter, failFilter, progressFilter);
    }

    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DonePipe<D, D_OUT, F_OUT, P_OUT> donePipe) {

        return new PipedPromise<>(this, donePipe, null, null);
    }

    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DonePipe<D, D_OUT, F_OUT, P_OUT> donePipe, FailPipe<F, D_OUT, F_OUT, P_OUT> failPipe) {

        return new PipedPromise<>(this, donePipe, failPipe, null);
    }

    public <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DonePipe<D, D_OUT, F_OUT, P_OUT> donePipe, FailPipe<F, D_OUT, F_OUT, P_OUT> failPipe,
            ProgressPipe<P, D_OUT, F_OUT, P_OUT> progressPipe) {

        return new PipedPromise<>(this, donePipe, failPipe, progressPipe);
    }

    public boolean isPending() {
        return state == State.PENDING;
    }

    public boolean isResolved() {
        return state == State.RESOLVED;
    }

    @Override
    public boolean isRejected() {
        return state == State.REJECTED;
    }
}
