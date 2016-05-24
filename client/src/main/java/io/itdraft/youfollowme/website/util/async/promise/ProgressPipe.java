package io.itdraft.youfollowme.website.util.async.promise;

public interface ProgressPipe<P, D_OUT, F_OUT, P_OUT> {
    Promise<D_OUT, F_OUT, P_OUT> pipeProgress(final P result);
}
