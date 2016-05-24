package io.itdraft.youfollowme.website.util.async.promise;

public interface FailCallback<F> {
    void onFail(final F result);
}
