package io.itdraft.youfollowme.website.util.async.promise;

public interface ProgressFilter<P, P_OUT> {
    P_OUT filterProgress(final P progress);
}
