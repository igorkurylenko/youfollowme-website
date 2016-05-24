package io.itdraft.youfollowme.website.util.async.promise;

public interface FailPipe<F, D_OUT, F_OUT, P_OUT> {
    Promise<D_OUT, F_OUT, P_OUT> pipeFail(final F result);
}
