package io.itdraft.youfollowme.website.util.async.promise.impl;

import io.itdraft.youfollowme.website.util.async.promise.*;

public class PipedPromise<D, F, P, D_OUT, F_OUT, P_OUT> extends DeferredObject<D_OUT, F_OUT, P_OUT> {
    public PipedPromise(final Promise<D, F, P> promise,
                        final DonePipe<D, D_OUT, F_OUT, P_OUT> donePipe,
                        final FailPipe<F, D_OUT, F_OUT, P_OUT> failPipe,
                        final ProgressPipe<P, D_OUT, F_OUT, P_OUT> progressPipe) {

        promise.done(new DoneCallback<D>() {
            public void onDone(D result) {
                if (donePipe != null) pipe(donePipe.pipeDone(result));
                else PipedPromise.this.resolve((D_OUT) result);
            }
        }).fail(new FailCallback<F>() {
            public void onFail(F result) {
                if (failPipe != null) pipe(failPipe.pipeFail(result));
                else PipedPromise.this.reject((F_OUT) result);
            }
        }).progress(new ProgressCallback<P>() {
            public void onProgress(P progress) {
                if (progressPipe != null) pipe(progressPipe.pipeProgress(progress));
                else PipedPromise.this.notify((P_OUT) progress);
            }
        });
    }

    protected Promise<D_OUT, F_OUT, P_OUT> pipe(Promise<D_OUT, F_OUT, P_OUT> promise) {
        promise.done(new DoneCallback<D_OUT>() {
            public void onDone(D_OUT result) {
                PipedPromise.this.resolve(result);
            }
        }).fail(new FailCallback<F_OUT>() {
            public void onFail(F_OUT result) {
                PipedPromise.this.reject(result);
            }
        }).progress(new ProgressCallback<P_OUT>() {
            public void onProgress(P_OUT progress) {
                PipedPromise.this.notify(progress);
            }
        });

        return promise;
    }
}
