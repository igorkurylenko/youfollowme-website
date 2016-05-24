package io.itdraft.youfollowme.website.util.async.promise;

public interface FailFilter<F, F_OUT> {
    F_OUT filterFail(final F result);
}
