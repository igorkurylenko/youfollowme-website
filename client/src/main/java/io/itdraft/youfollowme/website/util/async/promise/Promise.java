package io.itdraft.youfollowme.website.util.async.promise;

public interface Promise<D, F, P> {
    enum State {
        PENDING, REJECTED, RESOLVED
    }

    State state();

    boolean isPending();

    boolean isResolved();

    boolean isRejected();

    Promise<D, F, P> done(DoneCallback<D> callback);

    Promise<D, F, P> fail(FailCallback<F> callback);

    Promise<D, F, P> always(AlwaysCallback<D, F> callback);

    Promise<D, F, P> progress(ProgressCallback<P> callback);

    Promise<D, F, P> then(DoneCallback<D> doneCallback);

    Promise<D, F, P> then(DoneCallback<D> doneCallback, FailCallback<F> failCallback);

    Promise<D, F, P> then(DoneCallback<D> doneCallback, FailCallback<F> failCallback,
                          ProgressCallback<P> progressCallback);

    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(DoneFilter<D, D_OUT> doneFilter);

    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DoneFilter<D, D_OUT> doneFilter, FailFilter<F, F_OUT> failFilter);

    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DoneFilter<D, D_OUT> doneFilter, FailFilter<F, F_OUT> failFilter,
            ProgressFilter<P, P_OUT> progressFilter);

    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DonePipe<D, D_OUT, F_OUT, P_OUT> donePipe);

    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DonePipe<D, D_OUT, F_OUT, P_OUT> donePipe, FailPipe<F, D_OUT, F_OUT, P_OUT> failPipe);

    <D_OUT, F_OUT, P_OUT> Promise<D_OUT, F_OUT, P_OUT> then(
            DonePipe<D, D_OUT, F_OUT, P_OUT> donePipe, FailPipe<F, D_OUT, F_OUT, P_OUT> failPipe,
            ProgressPipe<P, D_OUT, F_OUT, P_OUT> progressPipe);
}
